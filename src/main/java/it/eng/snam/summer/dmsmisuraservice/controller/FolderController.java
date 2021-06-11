package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.service.DDSImpl;

@RestController
public class FolderController {

    @Autowired
    DDSImpl dds;

    @GetMapping("/folders/{id}")
    public Folder get(@PathVariable String id) {
        return dds.getFolder(id);
    }

    @GetMapping("/folders")
    public List<Folder> list(@RequestParam MultiValueMap<String, Object> parameters,
            @RequestParam(defaultValue = "0") Long offset, @RequestParam(defaultValue = "10") Long limit,
            @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String direction) {
        return dds.listFolders();
    }

    @GetMapping("/folders/{folder_id}/subfolders")
    public List<Subfolder> listSubfolders(@PathVariable String folder_id,
            @RequestParam MultiValueMap<String, Object> parameters, @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Long limit, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        return dds.listSubfolders(folder_id);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}")
    public Subfolder getSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id) {
        return dds.getSubfolder(folder_id, subfolder_id);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents")
    public List<Document> listDocumentsInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @RequestParam MultiValueMap<String, Object> parameters, @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Long limit, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        return dds.listDocumentsInSubfolder(folder_id, subfolder_id);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents/{document_id}")
    public Document getDocumentInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @PathVariable String document_id) {
        return dds.getDocument(document_id, folder_id, subfolder_id);
    }


    //download pdf?


}
