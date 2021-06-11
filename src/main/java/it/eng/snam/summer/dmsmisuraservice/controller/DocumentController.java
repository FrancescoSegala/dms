package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.service.DDSImpl;

@RestController
public class DocumentController {

    @Autowired
    DDSImpl dds;

    @GetMapping("/documents")
    public List<Document> getDocuments(
            @RequestParam MultiValueMap<String, Object> parameters,
            @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Long limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "") String folder,
            @RequestParam(defaultValue = "") String subfolder,
            @RequestParam(defaultValue = "") String published_at,
            @RequestParam(defaultValue = "") String remi ,
            @RequestParam(defaultValue = "") String linea_in ) {
                System.out.println(parameters);
        return dds.listDocuments();
    }

    @GetMapping("/documents/{document_id}")
    public Document getDocument(@PathVariable String document_id) {
        return dds.getDocument(document_id);
    }

}
