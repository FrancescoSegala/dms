package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import it.eng.snam.summer.common.exception.NotAlreadyExistsException;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Service
public class ConoDatiService {
	
	@Autowired
	Summer summer ;
	
    @Autowired
    SummerSqlProvider summerSqlProvider;
	
	public List<String> checkConoDatiTDoc(@NotEmpty String userID) throws Exception {
		
		//chiamo il ms contatti api: getAreaTecnicaByUserId per ottenere l'area tecnica dell'utente
		//poi chiamo il ms: anagrafica-remi-service getRemiByAreaTecnica per ottenere i remi.
		//Da qui recupero i documenti che hanno quei remi e quindi ottenere i TDoc. QUest'ultima operazione via JDBC
		
		
		
		
		Entity areaTecnicaEntity = summer.getAreaTecnicaByUserId(userID);
		String areaTecnica = (String) areaTecnicaEntity.get("areaTecnicaCodice");
		if (areaTecnica==null)
			throw new NotAlreadyExistsException("Area tecnica non trovata per user:"+userID);
		
		Entity[] remiEntityList = summer.getRemiByAreaTecnica(areaTecnica);
		List<String> listaRemi = new ArrayList<String>();
		for(Entity remiEntity : remiEntityList) {
			String remiAss = (String) remiEntity.get("remiAss");
			listaRemi.add(remiAss);
		}
		
		if (listaRemi.size()==0)
			throw new NotAlreadyExistsException("Remi non trovati per user:"+userID);
		
		List<String> listDocuments = summerSqlProvider.getDocumentiByRemi(listaRemi);
		
		return listDocuments;
	}
	
}
