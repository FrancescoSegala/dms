package it.eng.snam.summer.dmsmisuraservice.util;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Info;
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
            .withSubfolder(e.getAsString("subfolder"))
            .withPublishedAt(""+e.get("data_entrata_esercizio"))
            .withName(e.getAsString("name"))
            .withNotes(e.getAsString("notes"))
            .withStatus(e.getAsString("status"))
            .withUpdatedAt(e.getAsString("updated_at"))
            .withUpdatedBy(e.getAsString("updated_by"))
            .withLink(e.getAsString("link"))
            .withCreatedAt(e.getAsString("created_at"))
            .withCreatedBy(e.getAsString("created_by"))
            .withInfo(
                Info.build()
                    .with("remi", e.getAsString("remi"))
                    .with("linea", e.getAsString("linea"))
                    .with("codice_centro", e.getAsString("codice_centro"))
                    .with("codice_distretto", e.getAsString("codice_distretto"))
                    .with("codice_area_tecnica", e.getAsString("codice_area_tecnica"))
                    .with("codice_polo", e.getAsString("codice_polo"))
                    .with("ragione_sociale", e.getAsString("ragione_sociale"))
                    .with("ubicazione", e.getAsString("ubicazione"))
                    .with("provincia", e.getAsString("provincia"))
                    .with("comune", e.getAsString("comune"))
                    .with("regione", e.getAsString("regione"))
                    .with("codice_aop", (Long) e.get("codice_aop"))
                    .with("codice_ateco", e.getAsString("codice_ateco"))
                    .with("remi_terzo", e.getAsString("remi_terzo"))
                    .with("reti_trasporto", e.getAsString("reti_trasporto"))
            )

        ;
    }


    public static Remi toRemi(Entity e ){
        return new Remi()
            .withId(e.getAsString("remiAss"))
            .withDescription(e.getAsString("ragSociale"));
    }




}
