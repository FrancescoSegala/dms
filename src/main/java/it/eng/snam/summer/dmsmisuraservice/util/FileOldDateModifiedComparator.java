package it.eng.snam.summer.dmsmisuraservice.util;

import java.io.File;
import java.util.Comparator;

public class FileOldDateModifiedComparator implements Comparator<File> {
    /**
     * Compara 2 oggetti File in base alla data di ultima modifica piu vecchia (lastModified crescente)
     */
    public int compare(File p1, File p2) {
    	if(p1.lastModified() > p2.lastModified()) {
    		return 1;
    	} else if(p1.lastModified() < p2.lastModified()) {
    		return -1;	
    	} else {
    		return 0;
    	}
    }
    
}
