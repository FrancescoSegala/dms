package it.eng.snam.summer.dmsmisuraservice.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.micrometer.core.instrument.util.IOUtils;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Info;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;


public class FakeDDSImpl implements DDS {


    private Stream<Map<String, Object>> loadFolders() {
        return Arrays
                .asList(IOUtils.toString(this.getClass().getResourceAsStream("/fake_data/folders.csv")).split("\n"))
                .stream().map(e -> toRecord(e));
    }

    private Map<String, Object> toRecord(String s) {
        Map<String, Object> m = new HashMap<>();
        String[] values = s.split(";");
        m.put("folder", values[0]);
        m.put("subfolder", values[1]);
        m.put("description", values[2]);

        List<Map<String, String>> attributes = Arrays.asList(values[3].split(",")).stream()
                .map(e ->
               {
                   Map<String, String> res= mapOf("name", e);
                   res.put("type", "text");
                   return res;
               }
                ).collect(Collectors.toList());
        m.put("attributes", attributes);
        return m;
    }

    public Folder getFolder(String folder_id) {
        //@formatter:off
        return loadFolders()
            .filter(e -> e.get("folder").equals(folder_id))
            .findFirst()
            .map(e -> new Folder(folder_id, e.get("description").toString()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            //@formatter:on
    }

    public Document getDocument(String doc_id) {
        return randomDocument(doc_id);
    }

    public Document getDocument(String doc_id, String folder_id, String subfolder_id) {
        return randomDocument(doc_id, folder_id, subfolder_id);
    }

    public List<Folder> listFolders() {
        return loadFolders().map(e -> new Folder(e.get("folder").toString(), e.get("description").toString() ))
                .filter(distinctByKey(e -> e.id)).collect(Collectors.toList());
    }

    public List<Subfolder> listSubfolders(String folder_id, SubfolderSearch params ) {
        return loadFolders()
                .map(e ->
                    new Subfolder( e.get("subfolder").toString(),
                    "active",
                    e.get("description").toString(),
                    "source",
                    listOf("dest1", "dest2"),
                    e.get("folder").toString(),
                    listOf(new Info()))
                    )
                .filter(e -> e.folder.equals(folder_id)).collect(Collectors.toList());
    }

    public Subfolder getSubfolder(String folder_id, String subfolder_id) {
        return listSubfolders(folder_id, new SubfolderSearch()).stream().filter(e -> e.id.equals(subfolder_id)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Document> listDocuments() {
        return IntStream.range(0, 100).mapToObj(e -> randomDocument("" + e)).collect(Collectors.toList());
    }

    public List<Document> listDocumentsInSubfolder(String folder_id, String subfolder_id) {
        return IntStream.range(0, (int) (Math.random() * 20))
                .mapToObj(e -> randomDocument("" + e, folder_id, subfolder_id)).collect(Collectors.toList());
    }

    private Document randomDocument(String id) {
        List<Folder> folders = listFolders();
        int random_folder = (int) Math.floor(Math.random() * folders.size());
        String folder_id = folders.get(random_folder).id;
        List<Subfolder> list = listSubfolders(folder_id, new SubfolderSearch());
        String subfolder_id = list
                .get((int) Math.floor(Math.random() * list.size())).id;
        return randomDocument(id, folder_id, subfolder_id);

    }

    private Document randomDocument(String id, String folder_id, String subfolder_id) {
        Subfolder subfolder = getSubfolder(folder_id, subfolder_id);
        //@formatter:off
        return new Document()
                    .withId(id)
                    .withCreatedAt(Instant.now().toString())
                    .withCreatedBy("System")
                    .withFolder( folder_id  )
                    .withUpdatedBy("System")
                    .withUpdatedAt(Instant.now().toString())
                    .withSubfolder( subfolder_id )
                    .withPublishedAt(Instant.now().toString())
                    .withLink("/link")
                    .withName("name")
                    .withNotes("notes")
                    .withStatus("status")
                    .withInfo( subfolder.attributes );
        //@formatter:on
    }

    @Override
    public List<Folder> listFolders(FolderSearch params) {
        return listFolders();
    }

    @Override
    public Subfolder createSubfolder(String folder_id, SubfolderCreate params) {
        return getSubfolder(folder_id, params.getSubfolder_id() );
    }

    @Override
    public Subfolder updateSubfolder(String folder_id, SubfolderUpdate params) {
        return getSubfolder(folder_id, params.getSubfolder_id() );
    }

    @Override
    public void deleteSubfolder(String folder_id, String subfolder_id) {
        //nop
    }

    @Override
    public List<Document> listDocuments(DocumentSearch params) {
        return listDocuments();
    }

    @Override
    public Document createDocument(DocumentCreate params) {
        return new Document()
                    .withName(params.name)
                    .withNotes(params.notes)
                    .withFolder(params.folder)
                    .withStatus(params.status)
                    .withSubfolder(params.subfolder)
                    .withInfo(params.info);

    }

    @Override
    public Document updateDocument(String document_id, DocumentUpdate params) {
        return new Document()
                    .withName(params.name)
                    .withNotes(params.notes)
                    .withFolder(params.folder)
                    .withStatus(params.status)
                    .withSubfolder(params.subfolder)
                    .withInfo(params.info);
    }

    @Override
    public void deleteDocument(String document_id) {
        //nop
    }

    @Override
    public void getContent(String document_id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);

    }

}
