package it.eng.snam.summer.dmsmisuraservice.util;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.SubfolderPermission;

public class EntityMapper {


    public static Folder toFolder(Entity e) {
        return new Folder()
        .withId(e.getAsEntity("systemAttributes").getAsString("name").substring(1))
        .withDescription(e.getAsEntity("systemAttributes").getAsString("annotations"));
    }


    public static Subfolder toSubfolder(Entity e){
        System.out.println( "Entity mapper "+ e);
        return new Subfolder()
                    .withId(e.getAsEntity("systemAttributes").getAsString("name"))
                    .withFolder(e.getAsEntity("systemAttributes").getAsString("name").split("/")[1] )
                    .withDescription(e.getAsEntity("systemAttributes").getAsString("annotations"))
                    .withStatus(e.getAsEntity("systemAttributes").getAsBoolean("isLogicalDeleted") ? "inactive" : "active" )
                    .withSource("P8")
                    .withPermission(new SubfolderPermission(true, true, true));
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




}
