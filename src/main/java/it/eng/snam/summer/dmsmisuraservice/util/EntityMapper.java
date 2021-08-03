package it.eng.snam.summer.dmsmisuraservice.util;
import java.util.Arrays;
import java.util.List;

import org.slf4j.MDC;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.time.Instant;
import java.util.UUID;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Info;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.SubfolderPermission;
import it.eng.snam.summer.dmsmisuraservice.security.JwtConstants;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;

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
            .withInfo( Info.build().with("remi", e.getAsString("remi")) )
            .withInfo( Info.build().with("linea", e.getAsString("linea")) )
            .withInfo( Info.build().with("codice_centro", e.getAsString("codice_centro")))
            .withInfo( Info.build().with("codice_distretto", e.getAsString("codice_distretto")))
            .withInfo( Info.build().with("codice_area_tecnica", e.getAsString("codice_area_tecnica")))
            .withInfo( Info.build().with("codice_polo", e.getAsString("codice_polo")))
            .withInfo( Info.build().with("ragione_sociale", e.getAsString("ragione_sociale")))
            .withInfo( Info.build().with("ubicazione", e.getAsString("ubicazione")))
            .withInfo( Info.build().with("provincia", e.getAsString("provincia")))
            .withInfo( Info.build().with("comune", e.getAsString("comune")))
            .withInfo( Info.build().with("regione", e.getAsString("regione")))
            .withInfo( Info.build().with("codice_aop", (Long) e.get(")codice_aop")))
            .withInfo( Info.build().with("codice_ateco", e.getAsString("codice_ateco")))
            .withInfo( Info.build().with("remi_terzo", e.getAsString("remi_terzo")))
            .withInfo( Info.build().with("reti_trasporto", e.getAsString("reti_trasporto")))
        ;
    }


    public static Remi toRemi(Entity e ){
        return new Remi()
            .withId(e.getAsString("remiAss"))
            .withDescription(e.getAsString("ragSociale"));
    }



    public static Entity toSQLpayload(DocumentCreate params, String id) {
        //@formatter:off
        Entity res =  Entity.build("id", id).with("data", Instant.now().toString())
                .with("c_remi_ass",
                        params.getInfo().stream().filter(e -> e.containsKey("remi")).findFirst()
                                .orElse((Info) Info.build().with("remi", null)).getAsString("remi"))
                .with("folder", params.getFolder()).with("subfolder", params.getSubfolder());
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




    public static Entity toDDSpayload(DocumentCreate doc, String OS) {
        String id = "DMSMIS_" + UUID.randomUUID().toString();
        //@formatter:off
        return Entity.build("OS", OS)
            .with("documentalClass", "ALTRO")
            .with("id", id)
            .with("name", doc.getName())
            .with("documentTitle", doc.getTitle()).with("customAttributes", listOf())
            .with("customPermissions", listOf())
            .with("folders", listOf("/" + doc.getFolder() + "/" + doc.getSubfolder()))
            .with("folderClass", "dms_DMSFolder").with("createFolderIfNotExist", false);
        //@formatter:on
    }

}
