package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.service.DDS;

@RestController
public class DocumentController {

    @Autowired
    DDS dds;

    @GetMapping("/documents")
    public List<Document> list( @Valid DocumentSearch search) {
        return dds.listDocuments();
    }

    @GetMapping("/documents/{document_id}")
    public Document get(@PathVariable String document_id) {
        return dds.getDocument(document_id, document_id, document_id);
    }

    @PostMapping("/documents")
    public Document post( @Valid DocumentCreate dc) {
        return new Document().withId("pippo");
    }

}
