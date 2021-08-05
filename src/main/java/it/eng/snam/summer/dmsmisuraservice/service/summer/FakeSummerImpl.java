package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.Map;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class FakeSummerImpl implements Summer {

    @Override
    public Remi get(String id) {
        return new Remi().withId("id").withDescription("desc");
    }

    @Override
    public Entity getProfile(String user_id) {
        return Entity.build("name", "user1").with("surname", "surname1").with("id", "1");
    }

    @Override
    public Long getDocumentCount(String folder_id, String subfolder_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Long> getDocumentCount(String folder_id) {
        // TODO Auto-generated method stub
        return null;
    }

}
