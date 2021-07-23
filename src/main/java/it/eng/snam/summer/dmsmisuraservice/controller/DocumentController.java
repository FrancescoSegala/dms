package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;

@RestController
public class DocumentController {

    @Autowired
    DDS dds;

    @GetMapping("/documents")
    public List<Document> list(@Valid DocumentSearch params) {
        return dds.listDocuments(params);
    }

    @GetMapping("/documents/{document_id}")
    public Document get(@PathVariable String document_id) {
        return dds.getDocument(document_id);
    }

    @GetMapping("/documents/{document_id}/content")
    public void getContent(@PathVariable String document_id) {
        dds.getContent(document_id);
    }

    @PostMapping(path = "/documents",  consumes = {"multipart/form-data"})
    public Document post( @RequestPart("document") @Valid DocumentCreate params, @RequestPart("file") MultipartFile file) {
        return dds.createDocument(params, file );
    }

    @PutMapping("/documents/{document_id}")
    public Document put(@PathVariable String document_id, @RequestBody @Valid DocumentUpdate params) {
        return dds.updateDocument(document_id, params);
    }


    @DeleteMapping("/documents/{document_id}")
    public void delete(@PathVariable String document_id){
        dds.deleteDocument(document_id);
    }

}
