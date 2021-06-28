package it.eng.snam.summer.dmsmisuraservice.service.summer;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;

public class FakeSummerImpl implements Summer {

    @Override
    public Remi get(String id) {
        return new Remi().withId("id").withDescription("desc");
    }

}
