package it.eng.snam.summer.dmsmisuraservice.util.fake;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import it.eng.snam.summer.dmsmisuraservice.model.Subfolder;
import it.eng.snam.summer.dmsmisuraservice.model.SubfolderPermission;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import io.micrometer.core.instrument.util.IOUtils;


public class FakeDataLoader {

    public List<Entity> subfolders(){
        return this.loadSubfolders().collect(Collectors.toList());
    }

    public List<Entity> folders(){
        return this.loadFolders().collect(Collectors.toList());
    }


    public Stream<Entity> loadSubfolders() {
        return loadFile("subfolders.csv").map(this::toEntitySubfolder);
    }

    public Stream<Entity> loadFolders(){
        return loadFile("folders.csv").map(this::toEntityFolder);
    }


    private Stream<String> loadFile(String filename){
        return Arrays
                .asList(IOUtils.toString(this.getClass().getResourceAsStream("/fake_data/"+ filename)).split("\n"))
                .stream();
    }

    private Entity toEntityFolder(String s ){
        String[] values = s.split(";");
        return Entity.build("parent", "/").with("id", "/"+ values[0]).with("description", values[1]);
    }

    private Entity toEntitySubfolder(String s) {
        String[] values = s.split(";");
        return Entity.build("parent", "/"+values[0]).with("id","/"+ values[0] + "/" + values[1]).with("description", values[2]);
    }

    private Subfolder toSubfolder(Entity e){
        return new Subfolder()
            .withId(e.id())
            .withDescription(e.getAsString("description"))
            .withFolder(e.getAsString("parent"))
            .withDest("dest")
            .withPermission(new SubfolderPermission(true, true, true) )
            .withSource("source")
            .withStatus(  Math.random() % 2 == 1 ?   "active" : "inactive" );
    }


}
