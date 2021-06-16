package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.service.DDSImpl;

@RestController
public class DocumentController {

    @Autowired
    DDSImpl dds;

    @GetMapping("/documents")
    public List<Document> getDocuments( @Valid DocumentSearch search) {
        return dds.listDocuments();
    }

    @GetMapping("/documents/{document_id}")
    public Document getDocument(@PathVariable String document_id) {
        return dds.getDocument(document_id);
    }

}
