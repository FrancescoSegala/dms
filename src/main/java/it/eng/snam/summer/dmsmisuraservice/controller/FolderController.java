package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.create.FolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@RestController
public class FolderController {

    @Autowired
    DDS dds;


    @Autowired
    Summer summer;

    @GetMapping("/tree")
    public List<Entity> tree(){
        return dds.tree();
    }

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

    //TODO da rimuovere
    @DeleteMapping("/folders/{id}")
    public void delete(@PathVariable String id){
        dds.deleteSubfolder(id);
    }

    // ----------- subfolders -----------

    @GetMapping("/folders/{folder_id}/subfolders")
    public List<Subfolder> listSubfolders(@PathVariable String folder_id, @Valid SubfolderSearch params) {
        //@formatter:off
        Map<String, Long> countMap = summer.getDocumentCount(folder_id);

         return dds.listSubfolders(folder_id, params)
            .stream()
            //.peek( e -> System.out.println(e))
            .map(e -> e.withDocumentCount(countMap.getOrDefault(e.id.split("/")[e.id.split("/").length-1], 0L))) //TODO 2? o e.id.split("/").length-1
            .collect(Collectors.toList());
        //@formatter:on
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}")
    public Subfolder getSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id) {
        //@formatter:off
        return dds.getSubfolder(folder_id, subfolder_id)
            .withDocumentCount(
                summer.getDocumentCount(folder_id, subfolder_id)
            );
        //@formatter:on
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


    //------------------------- documents and subfolder ---------------------------

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents")
    public List<Document> listDocumentsInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id, @Valid DocumentSearch params) {
        params.setFolder(folder_id);
        params.setSubfolder(subfolder_id);
        return dds.listDocuments(params);
    }


    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents/{document_id}")
    public Document getDocumentInSubfolder(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @PathVariable String document_id) {
        return dds.getDocument(document_id, folder_id, subfolder_id);
    }

    @GetMapping("/folders/{folder_id}/subfolders/{subfolder_id}/documents/{document_id}/content")
    public void getContent(@PathVariable String folder_id, @PathVariable String subfolder_id,
            @PathVariable String document_id ) {
        dds.getContent(document_id );
    }

}
