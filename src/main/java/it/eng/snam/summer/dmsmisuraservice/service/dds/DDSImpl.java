package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toDocument;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toFolder;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toSubfolder;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

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
import it.eng.snam.summer.dmsmisuraservice.util.EntityMapper;

public class DDSImpl implements DDS {

    @Autowired
    DDSFolder ddsFolder;

    @Autowired
    DDSDocument ddsDocument;

    @Autowired
    DDSSubfolder ddsSubfolder;

    // -------------------------- folder --------------------------

    @Override
    public List<Folder> listFolders(FolderSearch params) {
        //@formatter:off
        return ddsFolder
            .list(params)
            .stream()
            .map(EntityMapper::toFolder)
            .collect(toList());
        //@formatter:on
    }

    @Override
    public Folder getFolder(String id) {
        return toFolder(ddsFolder.get(id));
    }

    @Override
    public Folder createFolder(FolderCreate params) {
        return toFolder(ddsFolder.post(params));
    }

    // -------------------------- subfolder --------------------------

    @Override
    public List<Subfolder> listSubfolders(String folder_id, SubfolderSearch params) {
        System.out.println("list subfolders dds impl ");
        //@formatter:off
        return ddsSubfolder
            .list(folder_id, params)
            .stream()
            .map(EntityMapper::toSubfolder)
            .collect(toList());
        //@formatter:on
    }

    @Override
    public Subfolder getSubfolder(String folder_id, String subfolder_id) {
        return toSubfolder(ddsSubfolder.get(folder_id, subfolder_id));
    }

    @Override
    public Subfolder createSubfolder(String folder_id, SubfolderCreate params) {
        return toSubfolder(ddsSubfolder.post(folder_id, params));
    }

    @Override
    public Subfolder updateSubfolder(String folder_id, SubfolderUpdate params) {
        return toSubfolder(ddsSubfolder.put(folder_id, params));
    }

    @Override
    public void deleteSubfolder(String id) {
        ddsSubfolder.delete(id);
    }

    // -------------------------- Documents --------------------------

    @Override
    public List<Document> listDocuments(DocumentSearch params) {
        //@formatter:off
        return ddsDocument
            .list(params)
            .stream()
            .map(EntityMapper::toDocument)
            .collect(toList());
        //@formatter:on
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
    public List<Entity> tree() {
        List<Entity> tree = ddsFolder.tree();
        //@formatter:off
        return tree.stream()
            .filter(e -> "/".equals(e.getAsEntity("systemAttributes").getAsListString("foldersParents").get(0)) )
            .map(e -> new Entity()
                    .with("id", e.getAsEntity("systemAttributes").getAsString("name"))
                    .with("subfolders",
                            tree
                            .stream()
                            .filter( x ->
                                 x.getAsEntity("systemAttributes").getAsListString("foldersParents").get(0).equals(e.getAsEntity("systemAttributes").getAsString("name"))
                            )
                            .map(x -> new Entity()
                                .with("id",x.getAsEntity("systemAttributes").getAsString("name") )
                                .with("description", x.getAsEntity("systemAttributes").getAsString("annotations"))
                            )
                            .collect(Collectors.toList())
                        )
                    )
            .collect(Collectors.toList());
        //@formatter:on
    }

}
