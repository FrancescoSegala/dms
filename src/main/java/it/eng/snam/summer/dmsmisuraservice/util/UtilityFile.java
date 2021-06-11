package it.eng.snam.summer.dmsmisuraservice.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityFile {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilityFile.class);

	public static void deleteOlderLogFiles(String dirPath, int historyMaxDays, int historyMaxTotalSizeMB) throws IOException {
		//Verifica il contenuto della directory dirPath contenente file di log e cancella i vecchi file (NON ".log", che sono file correnti) che:
		//1. siano piu vecchi di un numero di giorni pari a historyMaxDays
		//2. OPPURE siano i più vecchi tra quelli per cui la dimensione totale dei file di storico supera historyMaxTotalSizeMB (in MB)
		
		try {
			File filedir = new File(dirPath);  
			//History file total size (bytes) 
			long totalSize = 0;
			long FILE_TMP_MAX_DURATION_MINUTES = 10;
			long currentTimeMillis = System.currentTimeMillis();
			List<File> historyFileList = new ArrayList<File>(); 
			//Key: file name. Value: lastModiified
			HashMap<String, Long> historyFileLastModifiedMap = new HashMap<String, Long>();
			
			if(filedir.isDirectory()) {	
				for(File f : filedir.listFiles()) {
					if(f.isFile() && !f.getName().toLowerCase().endsWith(".log")  
						&& (!f.getName().toLowerCase().endsWith(".tmp")
						|| ( f.getName().toLowerCase().endsWith(".tmp") 
						&& (currentTimeMillis - f.lastModified()) > (FILE_TMP_MAX_DURATION_MINUTES*60*1000) ) )) {
						
						historyFileList.add(f);
						historyFileLastModifiedMap.put(f.getName(), f.lastModified());
						totalSize = totalSize + Files.size(Paths.get(f.getAbsolutePath()));
					}
					
					if(f.isFile() && f.getName().toLowerCase().endsWith(".tmp")
						&& !historyFileLastModifiedMap.containsKey(f.getName())) {
							//Controllo file .tmp se hanno superato la dimensione massima consentita per TUTTI i file di archivio
							long fileSize = Files.size(Paths.get(f.getAbsolutePath()));
							if(fileSize > (historyMaxTotalSizeMB*1000*1000)) {
								//File .tmp da eliminare perche' da solo sta superando tutta la dimensione massima consentita
								boolean result = f.delete();
								logger.info("File "+f.getName() +" con dimensione "+(fileSize/1000) + " KB. Controllo eliminazione: "+result);
							}
					}
				}
				
				if(historyFileList.size() > 0) {
					//Ordina la lista di file in base a lastModified piu vecchio
					Collections.sort(historyFileList, new FileOldDateModifiedComparator());
					long oldestLastModified = historyFileList.get(0).lastModified();
					long historyMaxMillis = Long.parseLong(""+historyMaxDays)*24*3600*1000;
					long historyMaxBytes = Long.parseLong(""+historyMaxTotalSizeMB)*1000*1000;
					boolean timeExceeded = ((currentTimeMillis - oldestLastModified) > historyMaxMillis);
					boolean sizeExceeded = (totalSize > (historyMaxTotalSizeMB*1000*1000));
					HashMap<String, Long> deletedFileMap = new HashMap<String, Long>();
					
					if(sizeExceeded || timeExceeded) {
						if(sizeExceeded) {
							//La diimensione totale dei file di history supera la dimensione massima configurata historyMaxTotalSizeMB (MB) 
							for(File f : historyFileList) {
								if(totalSize > historyMaxBytes) {
									long fileSize = Files.size(Paths.get(f.getAbsolutePath()));
									//Cancellazione file
									boolean result = f.delete();
									logger.info("File "+f.getName() +" con dimensione "+(fileSize/1000) + " KB. Controllo eliminazione: "+result);
									if(result) {
										totalSize = totalSize - fileSize;
										deletedFileMap.put(f.getName(), fileSize);
									}
								}
							}
						} 
						
						if(timeExceeded) {
							//Cancellazione dei file piu vecchi del numero massimo di giorni historyMaxDays
							for(File f : historyFileList) {
								//Il valore di lastModified deve essere quello originario salvato nella map (in f c'e' un valore alterato dai controlli precedenti)
								long lastModified = historyFileLastModifiedMap.get(f.getName());
								
								if( !deletedFileMap.containsKey(f.getName())
									&& ((currentTimeMillis - lastModified) > historyMaxMillis) ) {
										//Cancellazione file
										boolean result = f.delete();
										logger.info("File "+f.getName() +" con ultima scrittura "+((currentTimeMillis - lastModified)/(1000*3600)) + " h. Controllo eliminazione: "+result);
								}
							}
						}
					}
				} 	
			}
			
		} catch(Exception t) {
			throw new IOException(t);
		}
	}
	
}
