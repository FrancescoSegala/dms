package it.eng.snam.summer.dmsmisuraservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.eng.snam.summer.dmsmisuraservice.controller.FolderController;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;

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
    public void getfolderId(){
        Folder f = controller.get("COMM");
        assertNotNull(f);
    }


}
