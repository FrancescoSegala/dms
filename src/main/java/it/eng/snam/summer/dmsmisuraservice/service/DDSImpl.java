package it.eng.snam.summer.dmsmisuraservice.service;

import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toDocument;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toFolder;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toSubfolder;
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
import it.eng.snam.summer.dmsmisuraservice.util.EntityMapper;

//@Component
public class DDSImpl implements DDS {

    @Autowired
    DDSFolder ddsFolder;

    @Autowired
    DDSDocument ddsDocument;

    @Autowired
    DDSSubfolder ddsSubfolder;

    @Override
    public List<Folder> listFolders(FolderSearch params) {
        return ddsFolder.list(params).stream().map(EntityMapper::toFolder).collect(toList());

    }

    @Override
    public Folder getFolder(String id) {
        return toFolder(ddsFolder.get(id));
    }

    @Override
    public List<Subfolder> listSubfolders(String folder_id, SubfolderSearch params) {
        return ddsSubfolder.list(folder_id, params).stream().map(EntityMapper::toSubfolder).collect(toList());
    }

    @Override
    public Subfolder getSubfolder(String folder_id, String subfolder_id) {
        return toSubfolder(ddsSubfolder.get(folder_id, subfolder_id));
    }

    @Override
    public List<Document> listDocuments(DocumentSearch params) {
        return ddsDocument.list(params).stream().map(EntityMapper::toDocument).collect(toList());
    }

    @Override
    public void deleteSubfolder(String folder_id, String subfolder_id) {
        ddsSubfolder.delete(folder_id, subfolder_id);

    }

    @Override
    public Document getDocument(String document_id) {
        return toDocument(ddsDocument.get(document_id));
    }

    @Override
    public Document createDocument(DocumentCreate params) {
        return toDocument(ddsDocument.post(params));
    }

    @Override
    public Document updateDocument(String document_id, DocumentUpdate params) {
        return toDocument(ddsDocument.put(params));
    }

    @Override
    public void deleteDocument(String document_id) {
        ddsDocument.delete(document_id);
    }

    @Override
    public void getContent(String document_id) {
        ddsDocument.getContent(document_id);
    }

    @Override
    public Subfolder createSubfolder(String folder_id, SubfolderCreate params) {
        return toSubfolder(ddsSubfolder.post(folder_id, params));
    }

    @Override
    public Subfolder updateSubfolder(String folder_id, SubfolderUpdate params) {
        return toSubfolder(ddsSubfolder.put(folder_id, params));
    }

}
