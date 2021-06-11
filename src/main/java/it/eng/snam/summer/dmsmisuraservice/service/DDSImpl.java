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

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.micrometer.core.instrument.util.IOUtils;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;

@Service
public class DDSImpl implements DDS {


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
               //Map.of("name", e, "type", "text")
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
            .map(e -> new Folder(folder_id, "description" + folder_id))
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
        return loadFolders().map(e -> new Folder(e.get("folder").toString(), "description" + e.get("folder")))
                .filter(distinctByKey(e -> e.id)).collect(Collectors.toList());
    }

    public List<Subfolder> listSubfolders(String folder_id) {
        return loadFolders()
                .map(e -> new Subfolder(e.get("subfolder").toString(), e.get("description").toString(),
                        e.get("folder").toString(), (List<Map<String, String>>) e.get("attributes")))
                .filter(e -> e.folder.equals(folder_id)).collect(Collectors.toList());
    }

    public Subfolder getSubfolder(String folder_id, String subfolder_id) {
        return listSubfolders(folder_id).stream().filter(e -> e.id.equals(subfolder_id)).findFirst()
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
        String subfolder_id = listSubfolders(folder_id)
                .get((int) Math.floor(Math.random() * listSubfolders(folder_id).size())).id;
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
                    .withInfo(  subfolder.attributes);
        //@formatter:on
    }

}
