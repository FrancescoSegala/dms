package it.eng.snam.summer.dmsmisuraservice.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.DmsMisuraServiceApplication;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;


@SpringBootTest(classes = DmsMisuraServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
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
            .withRemi("remi!!//")
        ;
        try {
            List<Document> l =  controller.list(params);
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST , e.getStatus());
            return ;
            //assertEquals(e.getMessage(), actual);
        }
        fail("Exception not thrown");

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
            assertEquals(HttpStatus.NOT_FOUND , e.getStatus());
            return ;
        }
        fail("Exception not thrown");
    }


    @Test
    public void testGetDocumentContent(){
        try {
            controller.getContent("1");
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.NOT_IMPLEMENTED , e.getStatus());
            return ;
        }
        fail("Exception not thrown");
    }

}
