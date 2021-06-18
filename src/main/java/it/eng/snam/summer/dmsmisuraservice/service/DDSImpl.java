package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSImpl implements DDS {


    @Autowired
    DDSFolder ddsFolder ;

    @Autowired
    DDSDocument ddsDocument ;



    public List<Folder> listFolders() {
        return  ddsFolder.list()
        .stream()
        .map(e -> toFolder(e))
        .collect(Collectors.toList());

    }



    private Folder toFolder(Entity e) {
        return new Folder( );
    }



    @Override
    public Folder getFolder(String id) {
             ddsFolder.get(id);
        return null ;
    }



    @Override
    public List<Subfolder> listSubfolders(String folder_id) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public Subfolder getSubfolder(String folder_id, String subfolder_id) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public List<Document> listDocumentsInSubfolder(String folder_id, String subfolder_id) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public Document getDocument(String document_id, String folder_id, String subfolder_id) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public List<Document> listDocuments() {
        return ddsDocument.list()
        .stream()
        .map(e -> toDocument(e))
        .collect(Collectors.toList())
        ;
    }



    private Document toDocument(Entity e) {
        return new Document();
    }

}
