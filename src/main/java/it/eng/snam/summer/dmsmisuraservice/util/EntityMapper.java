package it.eng.snam.summer.dmsmisuraservice.util;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.DocumentSQL;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.SubfolderPermission;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.security.JwtConstants;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDSSubfolder;

public class EntityMapper {


    public static Folder toFolder(Entity e) {
        return new Folder()
        .withId(e.getAsEntity("systemAttributes").getAsString("name").substring(1))
        .withDescription(e.getAsEntity("systemAttributes").getAsString("annotations"));
    }


    public static Subfolder toSubfolder(Entity e){
        boolean create = true;
		boolean modify = true;

        /* Prendo le authorities definite nel security context */
		String mdcAuth = MDC.get(JwtConstants.MDC_CONSTANTS_SECURITY_AUTHORITY);

		/* Se ci sono parametri nell'MDC, allora eseguo il controllo controllo */
		if (!Utility.isEmpty(mdcAuth)) {
			List<String> authorities = Arrays.asList(mdcAuth.split(","));

			if(!authorities.contains("create"))
				create = false;
			if(!authorities.contains("modify"))
				modify = false;
		}

        return new Subfolder()
                    .withId(e.getAsEntity("systemAttributes").getAsString("name"))
                    .withFolder(e.getAsEntity("systemAttributes").getAsString("name").split("/")[1] )
                    .withDescription(e.getAsEntity("systemAttributes").getAsString("annotations"))
                    .withStatus(e.getAsEntity("systemAttributes").getAsBoolean("isLogicalDeleted") ? "inactive" : "active" )
                    .withSource("P8")
                    .withPermission(new SubfolderPermission(true, modify, create));
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
            .withInfo( Entity.build("remi", e.getAsString("remi")) )
            .withInfo( Entity.build("linea", e.getAsString("linea")) )
            .withInfo( Entity.build("codice_centro", e.getAsString("codice_centro")))
            .withInfo( Entity.build("codice_distretto", e.getAsString("codice_distretto")))
            .withInfo( Entity.build("codice_area_tecnica", e.getAsString("codice_area_tecnica")))
            .withInfo( Entity.build("codice_polo", e.getAsString("codice_polo")))
            .withInfo( Entity.build("ragione_sociale", e.getAsString("ragione_sociale")))
            .withInfo( Entity.build("ubicazione", e.getAsString("ubicazione")))
            .withInfo( Entity.build("provincia", e.getAsString("provincia")))
            .withInfo( Entity.build("comune", e.getAsString("comune")))
            .withInfo( Entity.build("regione", e.getAsString("regione")))
            .withInfo( Entity.build("codice_aop", (Long) e.get(")codice_aop")))
            .withInfo( Entity.build("codice_ateco", e.getAsString("codice_ateco")))
            .withInfo( Entity.build("remi_terzo", e.getAsString("remi_terzo")))
            .withInfo( Entity.build("reti_trasporto", e.getAsString("reti_trasporto")))
        ;
    }


    public static Remi toRemi(Entity e ){
        return new Remi()
            .withId(e.getAsString("remiAss"))
            .withDescription(e.getAsString("ragSociale"))
            .withLinea(e.getAsString("linea"));
    }



    public static Entity toSQLpayload( String id, String folder, String subfolder,  String remi, String linea ) {
        //@formatter:off
        Entity res =  Entity.build("id", id).with("data", Instant.now().toString())
                .with("c_remi_ass",remi)
                .with("linea", linea)
                .with("folder", folder)
                .with("subfolder", subfolder);
        //@formatter:on
        return res ;
    }

    public static Entity toSQLpayload( DocumentSQL doc ) {
        //@formatter:off
        Entity res =  Entity.build("id", doc.getId()).with("data", Instant.now().toString())
                .with("c_remi_ass",doc.getC_remi_ass())
                .with("linea", doc.getLinea())
                .with("folder", doc.getFolder())
                .with("subfolder", doc.getSubfolder());
        //@formatter:on
        return res ;
    }




    public static Entity toUpdateDocPayload(DocumentUpdate params, String OS , Entity ddsPreSysAttr){
        //@formatter:off
        //il parametro isLogicalDeleted non cambia valore anche si imposta il flag
        // basandosi sullo status -_-
        //TODO si puo usare la delete per settare il valore isLogicalDeleted a true ma come fare a fare l'opposto?
        return Entity.build("OS", OS)
            .with("name",params.getName() != null ? params.getName() : ddsPreSysAttr.getAsString("name"))
            .with("documentTitle", params.getTitle() != null ? params.getTitle() : ddsPreSysAttr.getAsString("documentTitle"))
            .with("isLogicalDeleted", ddsPreSysAttr.getAsBoolean("isLogicalDeleted"))
            .with("nameCheckOut", ddsPreSysAttr.getAsString("nameCheckOut"))
            .with("creator", ddsPreSysAttr.getAsString("creator"))
            .with("lastModifier", ddsPreSysAttr.getAsString("lastModifier"))
            .with("foldersParents", ddsPreSysAttr.getAsListString("foldersParents"))
            .with("documentClass", ddsPreSysAttr.getAsString("documentClass"))
            .with("dateCreated", ddsPreSysAttr.getAsEntity("dateCreated").get("$date"))
            .with("dateLastModified", ddsPreSysAttr.getAsEntity("dateLastModified").get("$date"))
            .with("owner", ddsPreSysAttr.getAsString("owner"))
            .with("annotations", ddsPreSysAttr.getAsString("annotations"))
            .with("isCheckedOut", ddsPreSysAttr.getAsBoolean("isCheckedOut"))
            .with("dateContentsLastAccessed", ddsPreSysAttr.getAsEntity("dateContentsLastAccessed").get("$date"))
            .with("versionSeriesId", ddsPreSysAttr.getAsString("versionSeriesId"))
            .with("lockTimeout", ddsPreSysAttr.getAsEntity("lockTimeout").getAsString("$date"))
            .with("lockOwner", ddsPreSysAttr.getAsString("lockOwner"))
            .with("reservationId", ddsPreSysAttr.getAsString("reservationId"))
            .with("isReserved", ddsPreSysAttr.getAsBoolean("isReserved"));
        //@formatter:on
    }




    public static Entity toDDSPayload(DocumentCreate doc, String OS, String path ) {
        String id = "DMSMIS_" + UUID.randomUUID().toString();
        List<String> folder = listOf(path);
         //@formatter:off
        return Entity.build("OS", OS)
            .with("documentalClass", "ALTRO")
            .with("id", id)
            .with("name", doc.getName())
            .with("documentTitle", doc.getTitle()).with("customAttributes", listOf())
            .with("customPermissions", listOf())
            .with("folders", folder )
            .with("folderClass", "dms_DMSFolder").with("createFolderIfNotExist", false);
        //@formatter:on
    }

}
