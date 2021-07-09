package it.eng.snam.summer.dmsmisuraservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.controller.FolderController;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FolderControllerTest {

    @Autowired
    FolderController controller;

    @Test
    public void contextLoad() {
        assertTrue(true);
    }

    @Test
    public void controllerLoad() {
        assertNotNull(controller);
    }

    @Test
    public void getFolders(){
        List<Folder> list = controller.list(new FolderSearch());
        assertNotNull(list);
        //assertTrue(list.size() > 0 );
    }

    @Test
    public void getFoldersWithParams(){
        FolderSearch params = new FolderSearch()
            ;
        List<Folder> list = controller.list(params);
        assertNotNull(list);
    }


    @Test
    public void getFailFolders(){
        FolderSearch params = new FolderSearch()
             ;
        try {
            controller.list(params);
        } catch (ResponseStatusException e) {
            assertEquals( HttpStatus.NOT_FOUND , e.getStatus());
            return;
        }
        fail("Exception not thrown");
    }

    @Test
    public void getfolderId(){
        Folder f = controller.get("COMM");
        assertNotNull(f);
    }

    @Test
    public void getFaliFolderId(){
        try {
            controller.get("-1");
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.NOT_FOUND , e.getStatus());
            return;
        }
        fail("Exception not thrown");
    }




    @Test
    public void getSubfolders(){
        SubfolderSearch params = new SubfolderSearch();
        List<Subfolder> list = controller.listSubfolders("COMM", params);
        assertNotNull(list);
    }


    @Test
    public void getSubfoldersWithParams(){
        SubfolderSearch params = new SubfolderSearch()
         .withStatus("active")
        ;
        List<Subfolder> list = controller.listSubfolders("COMM", params);
        assertNotNull(list);
    }



    @Test
    public void getFailSubfolders(){
        //TODO iterate over params
        SubfolderSearch params = new SubfolderSearch()
         .withStatus("attivo")
        ;
        try {
            controller.listSubfolders("COMM", params);
        } catch (ResponseStatusException e) {
            assertEquals( HttpStatus.BAD_REQUEST , e.getStatus() );
            return;
        }
        fail("Exception not thrown");
    }


    @Test
    public void getSubfolderId(){
        Subfolder sf = controller.getSubfolder("COMM", "COMM");
        assertNotNull(sf);
    }

    @Test
    public void getFailSubfolderId(){
        try {
            controller.getSubfolder("COOOA", "AAA");
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.NOT_FOUND , e.getStatus());
            return;
        }
        fail("Exception not thrown");
    }


}
