package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.create.FolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentCount;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;
import it.eng.snam.summer.dmsmisuraservice.util.SnamSQLClient;

@RestController
public class FolderController {

    @Autowired
    DDS dds;

    @Autowired
    private NamedParameterJdbcOperations template;

    // ----------- folders ----------

    @GetMapping("/folders/{id}")
    public Folder get(@PathVariable String id) {
        return dds.getFolder(id);
    }

    @PostMapping("/folders")
    public Folder postFolder(@Valid @RequestBody FolderCreate params) {
        return dds.createFolder(params);
    }

    @GetMapping("/folders")
    public List<Folder> list(@Valid FolderSearch params) {
        return dds.listFolders(params);
    }

    // ----------- subfolders -----------

    @GetMapping("/folders/{folder_id}/subfolders")
    public List<Subfolder> listSubfolders(@PathVariable String folder_id, @Valid SubfolderSearch params) {
        Map<String, Long> countMap = new SnamSQLClient(template).withTable("documenti")
                .withParams(new DocumentCount(folder_id, null)).countByField("subfolder");
        return dds.listSubfolders(folder_id, params).stream()
                .map(e -> e.withDocumentCount(countMap.getOrDefault(e.id.split("/")[2], 0L)))
                .collect(Collectors.toList());
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}")
    public Subfolder getSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id) {
        return dds.getSubfolder(folder_id, subfolder_id).withDocumentCount(new SnamSQLClient(template)
                .withTable("documenti").withParams(new DocumentCount(folder_id, subfolder_id)).count());
    }

    @PostMapping("/folders/{folder_id}/subfolders")
    public Subfolder postSubfolder(@PathVariable String folder_id, @Valid @RequestBody SubfolderCreate params) {
        return dds.createSubfolder(folder_id, params);
    }

    @PutMapping("/folders/{folder_id}/subfolders/{subfolder_id}")
    public Subfolder putSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @Valid @RequestBody SubfolderUpdate params) {
        return dds.updateSubfolder(folder_id + "/" + subfolder_id, params);
    }

    @DeleteMapping("/folders/{folder_id}/subfolders/{subfolder_id}")
    public void deleteSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id) {
        dds.deleteSubfolder(folder_id + "/" + subfolder_id);
    }


//TODO qui

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents")
    public List<Document> listDocumentsInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @RequestParam @Valid DocumentSearch params) {
        return dds.listDocuments(params);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents/{document_id}")
    public Document getDocumentInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @PathVariable String document_id) {
        return dds.getDocument(document_id);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents/{document_id}/content")
    public Document getContent(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @PathVariable String document_id) {
        return dds.getDocument(document_id);
    }

}
