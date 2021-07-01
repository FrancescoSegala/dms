package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;
import it.eng.snam.summer.dmsmisuraservice.util.Utility;

@RestController
public class DocumentController {

    @Autowired
    DDS dds;

    private List<Remi> getRemiFromProvince(String province) {
        return listOf(new Remi().withId("id1").withDescription("desc1"),
                new Remi().withId("id2").withDescription("desc2"));
    }

    @GetMapping("/documents")
    public List<Document> list(@Valid DocumentSearch params) {
        //TODO validazione semantica params getProvincia

        //@formatter:off
        if (!Utility.isEmpty(params.getProvince())
            && Utility.isEmpty(params.getRemi())
            && Utility.isEmpty(params.getRemi_in())) {
            params.setRemi_in(this.getRemiFromProvince(params.getProvince())
            .stream()
            .map(e -> e.getId())
            .collect(Collectors.toList())
            .toArray(new String[0]));
        //@formatter:on
        }
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

    @PostMapping("/documents")
    public Document post( @RequestBody @Valid DocumentCreate params) {
        return dds.createDocument(params);
    }

}
