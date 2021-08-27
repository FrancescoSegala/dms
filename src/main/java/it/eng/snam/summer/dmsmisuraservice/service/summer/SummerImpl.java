package it.eng.snam.summer.dmsmisuraservice.service.summer;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toRemi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class SummerImpl implements Summer  {

    @Autowired
    SummerRemi remi;

    @Autowired
    SummerUser users;

    @Autowired
    SummerSqlProvider sql ;


    @Override
    public Remi get(String id) {
        return toRemi(remi.get(id));
    }

    @Override
    public Entity getProfile(String user_id){
        return users.getUserProfile(user_id);
    }

    @Override
    public Long getDocumentCount(String folder_id, String subfolder_id) {
        return sql.getDocumentCount(folder_id, subfolder_id);
    }

    @Override
    public Map<String, Long> getDocumentCount(String folder_id) {
         return sql.getDocumentCount(folder_id);
    }

    
	@Override
	public Entity getAreaTecnicaByUserId(String userId) {
		return remi.getAreaTecnicaByUserId(userId);
	}

	@Override
	public Entity[] getRemiByAreaTecnica(String areaTecnica) {
		return remi.getRemiByAreaTecnica(areaTecnica);
	}
}
