package it.eng.snam.summer.dmsmisuraservice.service.dds;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.validation.Valid;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import io.micrometer.core.instrument.util.IOUtils;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.SubfolderPermission;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.FolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;


public class FakeDDSImpl   {




    // public List<Entity> tree() {
    //     //@formatter:off
    //     return  loadFolders()
    //     .map(e ->
    //         new Entity()
    //         .with("id",e.id())
    //         .with("description", e.getAsString("description"))
    //         .with("subfolders",

    //             loadSubfolders()
    //             .filter(x -> e.id().equals( x.getAsString("parent")) )
    //             .map(x -> new Entity().with("id", x.id()).with("description", x.getAsString("description")))
    //             .collect(Collectors.toList())
    //         )
    //     )
    //     .collect(Collectors.toList());
    //     //@formatter:on
    // }


    // public Folder getFolder(String folder_id) {
    //     //@formatter:off
    //     return loadFolders()
    //         .filter(e -> e.get("id").equals("/"+folder_id))
    //         .findFirst()
    //         .map(e -> new Folder("/"+folder_id, e.get("description").toString()))
    //         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    //         //@formatter:on
    // }



    // public List<Subfolder> listSubfolders(String folder_id, @Valid SubfolderSearch params ) {
    //     return loadSubfolders()
    //             .filter(e -> ("/"+folder_id).equals( e.getAsString("parent") ) )
    //             .map(e -> toSubfolder(e))
    //             .collect(Collectors.toList());
    // }



    // public Subfolder getSubfolder(String folder_id, String subfolder_id) {
    //     return listSubfolders(folder_id, new SubfolderSearch()).stream().filter(e -> e.id.equals(subfolder_id)).findFirst()
    //             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    // }


    // @Override
    // public List<Folder> listFolders(FolderSearch params) {
    //     return loadFolders().map(e -> new Folder(e.get("id").toString(), e.get("description").toString() ))
    //             .filter(distinctByKey(e -> e.id)).collect(Collectors.toList());
    // }


    // @Override
    // public Folder createFolder(FolderCreate params) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public void deleteSubfolder(String id) {
    //     // TODO Auto-generated method stub

    // }

    // @Override
    // public Subfolder createSubfolder(String folder_id, SubfolderCreate params) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public Subfolder updateSubfolder(String folder_id, SubfolderUpdate params) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }






    // public Document getDocument(String doc_id) {
    //     if (Integer.parseInt(doc_id) <= 0 ){
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity "+ doc_id + " not exist");
    //     }
    //     return randomDocument(doc_id);
    // }

    // public Document getDocument(String doc_id, String folder_id, String subfolder_id) {
    //     return randomDocument(doc_id, folder_id, subfolder_id);
    // }


    // public List<Document> listDocuments() {
    //     return IntStream.range(0, 100).mapToObj(e -> randomDocument("" + e)).collect(Collectors.toList());
    // }

    // public List<Document> listDocumentsInSubfolder(String folder_id, String subfolder_id) {
    //     return IntStream.range(0, (int) (Math.random() * 20))
    //             .mapToObj(e -> randomDocument("" + e, folder_id, subfolder_id)).collect(Collectors.toList());
    // }

    // private Document randomDocument(String id) {
    //     List<Folder> folders = listFolders(new FolderSearch() );
    //     int random_folder = (int) Math.floor(Math.random() * folders.size());
    //     String folder_id = folders.get(random_folder).id;
    //     List<Subfolder> list = listSubfolders(folder_id, new SubfolderSearch());
    //     String subfolder_id = list
    //             .get((int) Math.floor(Math.random() * list.size())).id;
    //     return randomDocument(id, folder_id, subfolder_id);

    // }

    // private Document randomDocument(String id, String folder_id, String subfolder_id) {
    //     Subfolder subfolder = getSubfolder(folder_id, subfolder_id);
    //     //@formatter:off
    //     return new Document()
    //                 .withId(id)
    //                 .withCreatedAt(Instant.now().toString())
    //                 .withCreatedBy("System")
    //                 .withFolder( folder_id  )
    //                 .withUpdatedBy("System")
    //                 .withUpdatedAt(Instant.now().toString())
    //                 .withSubfolder( subfolder_id )
    //                 .withPublishedAt(Instant.now().toString())
    //                 .withLink("/link")
    //                 .withName("name")
    //                 .withNotes("notes")
    //                 .withStatus("status");
    //     //@formatter:on
    // }



    // @Override
    // public List<Document> listDocuments( @Valid DocumentSearch params) {
    //     return listDocuments();
    // }

    // @Override
    // public Document createDocument( @Valid DocumentCreate params, MultipartFile file) {
    //     return new Document()
    //                 .withName(params.getName())
    //                 .withFolder(params.getFolder())
    //                 .withSubfolder(params.getSubfolder())
    //                 .withInfo(params.getInfo().get(0));

    // }

    // @Override
    // public Document updateDocument(String document_id, @Valid DocumentUpdate params) {
    //     return new Document()
    //                 .withName(params.name)
    //                 .withNotes(params.title)
    //                 .withStatus(params.status)
    //                 .withInfo(params.info.get(0));
    // }

    // @Override
    // public void deleteDocument(String document_id) {
    //     //nop
    // }

    // @Override
    // public byte[] getContent(String document_id) {
    //     throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);

    // }



    // public void getContent(String document_id, String content_name) {
    //     // TODO Auto-generated method stub

    // }

}
