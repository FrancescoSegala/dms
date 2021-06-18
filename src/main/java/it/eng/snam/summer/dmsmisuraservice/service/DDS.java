package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;

public interface DDS {

    public List<Folder> listFolders();

    public Folder getFolder(String id);

    public List<Subfolder> listSubfolders(String folder_id);

    public Subfolder getSubfolder(String folder_id, String subfolder_id);

    public List<Document> listDocumentsInSubfolder(String folder_id, String subfolder_id);

    public Document getDocument(String document_id, String folder_id, String subfolder_id);

    public List<Document> listDocuments();


    //GET folders ->
    //implementazione metodi che sono in fakeddsImpl

}
