package it.eng.snam.summer.dmsmisuraservice.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;


//@Component
public class DDSImpl implements DDS {


    @Autowired
    DDSFolder ddsFolder ;

    @Autowired
    DDSDocument ddsDocument ;

    @Autowired
    DDSSubfolder  ddsSubfolder;


    @Override
    public List<Folder> listFolders(FolderSearch params) {
        return  ddsFolder
        .list(params)
        .stream()
        .map( this::toFolder )
        .collect( toList() );

    }



    private Folder toFolder(Entity e) {
        return new Folder()
        .withId(e.id())
        .withDescription(e.getAsString("description"));
    }


    private Subfolder toSubfolder(Entity e){
        return new Subfolder()
                    .withId(e.id())//TODO
                    .withDescription(e.getAsString("description"))
                    .withStatus(e.getAsString("status"));
    }

    @Override
    public Folder getFolder(String id) {
        return toFolder( ddsFolder.get(id));
    }



    @Override
    public List<Subfolder> listSubfolders(String folder_id, SubfolderSearch params) {
        return ddsSubfolder.list(folder_id, params)
                .stream()
                .map(this::toSubfolder)
                .collect( toList() );
    }



    @Override
    public Subfolder getSubfolder(String folder_id, String subfolder_id) {
        // TODO Auto-generated method stub
        return null;
    }




    @Override
    public List<Document> listDocuments(DocumentSearch params) {
        return ddsDocument.list()
        .stream()
        .map(this::toDocument)
        .collect(toList())
        ;
    }



    private Document toDocument(Entity e) {
        return new Document()
        ;
    }




    @Override
    public void deleteSubfolder(String folder_id, String subfolder_id) {
        // TODO Auto-generated method stub

    }



    @Override
    public Document getDocument(String document_id) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public Document createDocument(DocumentCreate params) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public Document updateDocument(String document_id, DocumentUpdate params) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void deleteDocument(String document_id) {
        // TODO Auto-generated method stub

    }



    @Override
    public void getContent(String document_id) {
        // TODO Auto-generated method stub

    }





    @Override
    public Subfolder createSubfolder(String folder_id, SubfolderCreate params) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public Subfolder updateSubfolder(String folder_id, SubfolderUpdate params) {
        // TODO Auto-generated method stub
        return null;
    }

}
