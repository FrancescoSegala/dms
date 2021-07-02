package it.eng.snam.summer.dmsmisuraservice.util;

import it.eng.snam.summer.dmsmisuraservice.data.AnagRemi;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;

public class EntityMapper {


    public static Folder toFolder(Entity e) {
        return new Folder()
        .withId(e.getAsEntity("systemAttributes").getAsString("name"))
        .withDescription(e.getAsEntity("systemAttributes").getAsString("annotations"));
    }


    public static Subfolder toSubfolder(Entity e){
        return new Subfolder()
                    .withId(e.id())//TODO COMPLETE
                    .withDescription(e.getAsString("description"))
                    .withStatus(e.getAsString("status"));
    }



    public static Document toDocument(Entity e) {
        return new Document()
            .withId(e.id())
            .withFolder(e.getAsString("folder"))
            .withName(e.getAsString("name"))
            .withNotes(e.getAsString("notes"))
            .withPublishedAt(e.getAsString("published_at"))
            .withStatus(e.getAsString("status"))
            .withSubfolder(e.getAsString("subfolder"))
            .withUpdatedAt(e.getAsString("updated_at"))
            .withUpdatedBy(e.getAsString("updated_by"))
            .withLink(e.getAsString("link"))
            .withCreatedAt(e.getAsString("created_at"))
            .withCreatedBy(e.getAsString("created_by"))
            .withInfo( e.getAsList("info"))
        ;
    }


    public static Remi toRemi(Entity e ){
        return new Remi()
            .withId(e.getAsString("remiAss"))
            .withDescription(e.getAsString("ragSociale"));
    }

    public static Remi toRemi(AnagRemi e){
        return new Remi()
            .withId(e.getC_remi_ass())
            .withDescription( e.getS_remi() );
    }



}
