package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;

@RestController
public class FolderController {

    @Autowired
    DDS dds;

    private final Logger logger = LoggerFactory.getLogger(FolderController.class);

    @GetMapping("/folders/{id}")
    public Folder get(@PathVariable String id) {
        return dds.getFolder(id);
    }

    @GetMapping("/folders")
    public List<Folder> list( @Valid FolderSearch params) {
        return dds.listFolders(params);
    }

    @GetMapping("/folders/{folder_id}/subfolders")
    public List<Subfolder> listSubfolders(@PathVariable String folder_id, @Valid SubfolderSearch params) {
        return dds.listSubfolders(folder_id, params);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}")
    public Subfolder getSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id) {
        return dds.getSubfolder(folder_id, subfolder_id);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents")
    public List<Document> listDocumentsInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @RequestParam @Valid DocumentSearch params) {
        return dds.listDocuments( params );
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
