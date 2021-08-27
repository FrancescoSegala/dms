package it.eng.snam.summer.dmsmisuraservice.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import it.eng.snam.summer.dmsmisuraservice.util.Utility;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final Logger loggerSummer = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Value("${jwt.enabled:false}")
	private Boolean enabled;

	@Value("${jwt.profiler-url:default}")
	private String profilerUrl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		loggerSummer.debug("Uri richiesto: {}", request.getRequestURI());

		/* Bypass della security per l'health-check */
		if (request.getRequestURI().endsWith("/actuator/health")) {
			chain.doFilter(request, response);
			return;
		}

		/* Controllo parametro forzato per abilitare security */
		if (Utility.isEmpty(request.getHeader("enableSecurity")) || !request.getHeader("enableSecurity").equals("true") ) {

			/* Controllo abilitazione security */
			if (!enabled) {

				/* Se la security è disabilitata, setto il il context con superUser (abilitato a tutto) */
				List<SimpleGrantedAuthority> singletonList = new ArrayList<>();
				singletonList.add(new SimpleGrantedAuthority("search"));
				singletonList.add(new SimpleGrantedAuthority("download"));
				singletonList.add(new SimpleGrantedAuthority("view"));
				singletonList.add(new SimpleGrantedAuthority("upload"));
				singletonList.add(new SimpleGrantedAuthority("update"));
				singletonList.add(new SimpleGrantedAuthority("delete"));
				singletonList.add(new SimpleGrantedAuthority("modify"));
				singletonList.add(new SimpleGrantedAuthority("create"));

				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("superUser", null, singletonList));

				chain.doFilter(request, response);
				return;
			}
		}

		final String jwtToken = request.getHeader( JwtConstants.HEADER_AUTHORIZATION);
		String userID = null;
		String profile = null;
		List<Funzionalita> listaFunzionalita = new ArrayList<>();
		String authorities = null;

		/* Controllo token */
		if (!Utility.isEmpty(jwtToken)) {

			/* Prendo codice rie dal token */
			try {

				/* Prendo l'userID dal token */
				userID = jwtTokenUtil.getUserFromToken(jwtToken);

			/* Quando parserizza i claims, controlla in automatico la scadenza */
			} catch (ExpiredJwtException e) {
				loggerSummer.warn("Jwt scaduto.");
				chain.doFilter(request, response);
				return;

			/* Se il token non è formattato nel modo corretto */
			} catch (MalformedJwtException e ) {
				loggerSummer.warn("Jwt in formato illegibile.");
				chain.doFilter(request, response);
				return;
			}

			/* Controllo validità rie */
			if (Utility.isEmpty(userID)) {
				loggerSummer.warn("Autenticazione Jwt fallita: \"rie\" non trovato.");
				chain.doFilter(request, response);
				return;
			}

			/* Se non è già autenticato, autentichiamolo */
			if (SecurityContextHolder.getContext().getAuthentication() == null) {

				/* Chiamata microservizio profilazione */
				final String url = profilerUrl + userID+"/DMS_MISURA";
				RestTemplate restTemplate = new RestTemplate();
				ProfiliResponse result = null;

				try {
					result = restTemplate.getForObject(url, ProfiliResponse.class);

				} catch (ResourceAccessException e) {
					loggerSummer.info("SecurityLog: Chiamata non effettuata. Url errato o servizio non raggiungibile.");
					chain.doFilter(request, response);
					return;

				} catch (IllegalArgumentException e) {
					loggerSummer.info("SecurityLog: Url non completo.");
					chain.doFilter(request, response);
					return;

				} catch (HttpClientErrorException e) {
					loggerSummer.info("SecurityLog: Risposta negativa dal server. StatusCode: " + e.getRawStatusCode());
					chain.doFilter(request, response);
					return;
				}

				/* Recupero profilo dalla response */
				profile = result.getProfilo();

				listaFunzionalita = result.getListFunzionalita();

				List<Map<String, List<String>>> permessiPerCampi = listaFunzionalita
						.stream()
						.filter(f -> (JwtConstants.DMS_GEST_DOCUMENTI.equals(f.getLabel()) ||
								JwtConstants.DMS_GEST_TDOC.equals(f.getLabel())))
						.map(f -> f.getPermessiPerCampi())
						.collect(Collectors.toList());

				List<SimpleGrantedAuthority> java8Auth = new ArrayList<>();
				for(Map<String, List<String>> map : permessiPerCampi) {
					java8Auth.addAll(map.entrySet()
							.stream()
							.map(Map.Entry::getKey)
							.map(key -> new SimpleGrantedAuthority(key))
							.collect(Collectors.toList()));
				}
				
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(profile, null, java8Auth));

				authorities = java8Auth.stream()
						.map(SimpleGrantedAuthority::getAuthority)
						.collect(Collectors.joining(","));

				MDC.put(JwtConstants.MDC_CONSTANTS_SECURITY_AUTHORITY, authorities);
				MDC.put(JwtConstants.MDC_CONSTANTS_SECURITY_PROFILE, profile);
				MDC.put(JwtConstants.MDC_CONSTANTS_SECURITY_USERID, userID);
			}
		} else {
			loggerSummer.warn("Token Jwt vuoto o nullo.");
		}

		chain.doFilter(request, response);
	}

	/** Classe di response alla chiamata verso editable-fields-serivce */
	public static class ProfiliResponse {
		private String profilo;
		private List<Funzionalita> listFunzionalita;

		public ProfiliResponse() {
			this.listFunzionalita = new ArrayList<>();
		}

		public ProfiliResponse(String profilo, List<Funzionalita> listFunzionalita) {
			this.profilo = profilo;
			this.listFunzionalita = listFunzionalita;
		}

		public String getProfilo() {
			return profilo;
		}

		public void setProfilo(String profilo) {
			this.profilo = profilo;
		}

		public List<Funzionalita> getListFunzionalita() {
			return listFunzionalita;
		}

		public void setListFunzionalita(List<Funzionalita> listFunzionalita) {
			this.listFunzionalita = listFunzionalita;
		}

	}

	/** Classe di response alla chiamata verso editable-fields-serivce */
	public static class Funzionalita {
		private String label;
		private Map<String, List<String>> permessiPerCampi;

		public Funzionalita() {
		}

		public Funzionalita(String label, Map<String, List<String>> permessiPerCampi) {
			this.label = label;
			this.permessiPerCampi = permessiPerCampi;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public Map<String, List<String>> getPermessiPerCampi() {
			return permessiPerCampi;
		}

		public void setPermessiPerCampi(Map<String, List<String>> permessiPerCampi) {
			this.permessiPerCampi = permessiPerCampi;
		}

	}

}
