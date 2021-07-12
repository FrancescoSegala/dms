package it.eng.snam.summer.dmsmisuraservice.service.dds;

import java.util.List;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.Folder;
import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.FolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public interface DDS {


    //general

    public List<Entity> tree();

    //folder

    public List<Folder> listFolders(FolderSearch params);

    public Folder getFolder(String id);

    public Folder createFolder( FolderCreate params );

    //subfolder

    public List<Subfolder> listSubfolders(String folder_id, SubfolderSearch params );

    public Subfolder getSubfolder(String folder_id, String subfolder_id);

    public Subfolder createSubfolder( String folder_id , SubfolderCreate params);

    public Subfolder updateSubfolder( String folder_id , SubfolderUpdate params);

    public void deleteSubfolder(String id );

    //document

    public Document getDocument(String document_id);

    public List<Document> listDocuments(DocumentSearch params);

    public Document createDocument(DocumentCreate params);

    public Document updateDocument(String document_id , DocumentUpdate params);

    public void deleteDocument(String document_id );

    public void getContent( String document_id );



}
