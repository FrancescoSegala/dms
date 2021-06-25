package it.eng.snam.summer.dmsmisuraservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.controller.DocumentController;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentControllerTest  {

    @Autowired
    DocumentController controller ;


    @Test
    public void contextLoad() {
        assertTrue(true);
    }


    @Test
    public void controllerLoad() {
        assertNotNull(controller);
    }

    @Test
    public void testGetDocuments(){
        List<Document> list = controller.list(new DocumentSearch());
        assertNotNull(list);
    }

    @Test
    public void testGetDocumentsWithParams(){
        //todo iterate over single param
        DocumentSearch params = new DocumentSearch()
            .withFolder("COMM")
            .withSubfolder("COMM")
            .withLinea(Arrays.array("1", "2", "3"))
            .withPublishedAt("2021-01-02T22:32:00Z")
            .withRemi("remi")
            ;
        List<Document> list = controller.list(params);
        assertNotNull(list);
    }


    @Test
    public void testFailGetDocuments(){
        //TODO iterate over param
        DocumentSearch params = new DocumentSearch()
            .withFolder("COMMA")
            .withSubfolder("COMMA")
            .withLinea(Arrays.array("1", "2", "3"))
            .withPublishedAt("21-01-02T22:32Z")
            .withRemi("remi!!//")
        ;
        try {
            List<Document> list = controller.list(params);
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus().value(), 400);
            //assertEquals(e.getMessage(), actual);
        }

    }


    @Test
    public void testGetDocumentId(){
        Document doc = controller.get("1");
        assertNotNull(doc);
    }


    @Test
    public void testFailGetDocumentId(){
        try {
            controller.get("-1");
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus().value(), 404);
        }
    }


    @Test
    public void testGetDocumentContent(){
        try {
            controller.getContent("1");
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus().value(), 501);
        }
    }

}
