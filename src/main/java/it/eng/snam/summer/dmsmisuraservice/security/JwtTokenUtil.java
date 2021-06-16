package it.eng.snam.summer.dmsmisuraservice.security;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${jwt.public-key:default}")
	private String publicKey;

	@Value("${jwt.secret:default}")
	private String secret;

	/** Restituisce il parametro "sub" dal token */
	public String getSubjectFromToken(String token) {
		return getClaimFromToken(clearTokenFromBearer(token), Claims::getSubject);
	}

	/** Restituisce il parametro "exp" dal token */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(clearTokenFromBearer(token), Claims::getExpiration);
	}

	/** Restituisce l'utente dal token */
	@SuppressWarnings("deprecation")
	public String getUserFromToken(String token) {
		String utente = null;

		/* Token da SSO */
		try {
			utente = getClaimFromToken(clearTokenFromBearer(token), JwtConstants.USER_ID, String.class);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (utente == null) {
			/* Se non funziona il token dell'SSO, provare con quello generato da BE */
			try {
				utente = getClaimFromToken(clearTokenFromBearer(token), JwtConstants.RIE, String.class);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		/* Se con entrambi non funziona, allora uscire con errore */
		if (utente == null)
			logger.error("Token non valido");

		return utente;
	}

	/**
	 * Restituisce il valore di Claim in base alla chiave in input.<br>
	 * Es. di utilizzo:
	 * <pre>
	 * String name = jwTokenUtil.getClaimFromToken(token, "name", String.class);
	 * </pre>
	 */
	public <T> T getClaimFromToken(String token, String key, Class<T> classType) {
		Claims claims = null;

		try {
			claims = getAllClaimsFromToken(clearTokenFromBearer(token));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (claims == null) {
			/* Se non riesce, usare l'altra decodifica */
			try {
				claims = getAllClaimsFromTokenOld(clearTokenFromBearer(token));

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (claims == null)
			throw new IllegalArgumentException("Impossibile leggere i claims dal token");

		return claims.get(key, classType);
	}

	/**
	 * Per l'utilizzo di funzioni standard di get di {@link Claims}.<br>
	 * Consigliato il <i>method reference</i>.<br>
	 * Es.
	 * <pre>
	 * String subject = jwtTokenUtility.getClaimFromToken(token, Claims::getSubject);
	 * </pre>
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = null;

		try {
			claims = getAllClaimsFromToken(clearTokenFromBearer(token));
		} catch (Exception e) {
			/* Se non riesce, usare l'altra decodifica */
			claims = getAllClaimsFromTokenOld(clearTokenFromBearer(token));
		}

		return claimsResolver.apply(claims);
	}

	/**
	 * Restituisce la lista dei claims dal token in input.
	 * @exception MalformedJwtException Formato del token non valido
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				/* Con chiave */
				.setSigningKey(getDecodedRSAPublicKey())
				.parseClaimsJws(token)
				.getBody();
	}

	/**
	 * Restituisce la lista dei claims dal token in input.
	 * @exception MalformedJwtException Formato del token non valido
	 */
	private Claims getAllClaimsFromTokenOld(String token) {
		return Jwts.parser()
				/* Con chiave */
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
				/* Senza chiave */
//				.parseClaimsJwt(token)
//				.getBody();
	}


	/**
	 * Restituisce il token Jwt assegnando il codice Rie in ingresso come claim con chiave "rie"
	 */
	public String generateToken(String userId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(JwtConstants.USER_ID, userId);
		claims.put(JwtConstants.NOME, "");
		claims.put(JwtConstants.COGNOME, "");
		claims.put(JwtConstants.COMPANY_ID, "");

		return doGenerateToken(claims, userId);
	}

	/** Metodo che genera il token Jwt */
	private String doGenerateToken(Map<String, Object> claims, String rie) {
		Date now = Date.from(Instant.now());
		Date exp = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

		return Jwts.builder()
				.setClaims(claims)									// Body del Jwt
				.setSubject(rie)									// Optional: elemento "sub"
				.setIssuedAt(now)									// Optional: elemento "iat"
				.setExpiration(exp)									// Optional: elemento "exp"
				.signWith(SignatureAlgorithm.HS256, secret)			// Algoritmo di crypt e chiave
				.compact();
	}

	/** Restituisce la chiave pubblica per RSA decodificata */
	private RSAPublicKey getDecodedRSAPublicKey() {
		RSAPublicKey pubKey = null;
		KeyFactory keyFactory = null;

		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
            //nop
		}

		// NOSONAR
		if (keyFactory != null) {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));

			try {
				pubKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
			} catch (InvalidKeySpecException e) {
                //nop
			}
		}

		return pubKey;
	}

	/**
	 * Restituisce il token senza il "Bearer" davanti
	 * @param token - JWT token
	 */
	private String clearTokenFromBearer(String token) {
		String newToken = token;

		if (token.contains("Bearer "))
			newToken = token.replace("Bearer ", "");

		return newToken;
	}

}
