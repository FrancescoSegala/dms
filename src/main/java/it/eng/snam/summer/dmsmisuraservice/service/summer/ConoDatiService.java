package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import it.eng.snam.summer.common.exception.NotAlreadyExistsException;
import it.eng.snam.summer.dmsmisuraservice.security.JwtRequestFilter.ProfiliResponse;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Service
public class ConoDatiService {

	@Autowired
	Summer summer ;

    @Autowired
    SummerSqlProvider summerSqlProvider;
    
    @Autowired
    RestTemplate restTemplate;
    
	@Value("${jwt.profiler-url:default}")
	private String profilerUrl;

	public List<Object> checkConoDatiTDoc(@NotEmpty String userId) throws Exception  {
		
		//recuperiamo il profilo dallo userId chiamando il ms editable-service getProfiloUtenteEsterno
		final String url = profilerUrl + userId+"/DMS_MISURA";
		ProfiliResponse result = null;
		
		try {
			result = restTemplate.getForObject(url, ProfiliResponse.class);

		} catch (Exception e) {
			throw new Exception("Impossibile recuperare il profilo dallo userId");
		}
		
		String profilo = result.getProfilo();
		
		String tDocPrimoLivello = summerSqlProvider.getTDocByProfilo(profilo);
		
		//recuperiamo la lista dei TDoc di primo livello
		String[] arrTDoc = tDocPrimoLivello.split(",");
		return Arrays.asList(arrTDoc);

		//chiamo il ms contatti api: getAreaTecnicaByUserId per ottenere l'area tecnica dell'utente
		//poi chiamo il ms: anagrafica-remi-service getRemiByAreaTecnica per ottenere i remi.
		//Da qui recupero i documenti che hanno quei remi e quindi ottenere i TDoc. QUest'ultima operazione via JDBC

//		Entity areaTecnicaEntity = summer.getAreaTecnicaByUserId(userID);
//		String areaTecnica = (String) areaTecnicaEntity.get("areaTecnicaCodice");
//		if (areaTecnica==null)
//			throw new NotAlreadyExistsException("Area tecnica non trovata per user:"+userID);
//
//		Entity[] remiEntityList = summer.getRemiByAreaTecnica(areaTecnica);
//		List<String> listaRemi = new ArrayList<String>();
//		for(Entity remiEntity : remiEntityList) {
//			String remiAss = (String) remiEntity.get("remiAss");
//			listaRemi.add(remiAss);
//		}
//
//		if (listaRemi.size()==0)
//			throw new NotAlreadyExistsException("Remi non trovati per user:"+userID);
//
//		return summerSqlProvider.getDocumentiByRemi(listaRemi);
	}

}
