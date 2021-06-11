package it.eng.snam.summer.dmsmisuraservice.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Gestisce la schedulazione dei task del microservizio
 */
@Component
public class DmsScheduler {

	private static final Logger logger = LoggerFactory.getLogger(DmsScheduler.class);
	
	@Value("${logging.file.dirPath}")
	private String loggingFileDirPath;
	
	@Value("${logging.file.historyMaxDays}")
	private String loggingFileHistoryMaxDays;
	
	@Value("${logging.file.historyMaxSizeMB}")
	private String loggingFileHistoryMaxSizeMB;
	
    //Esegue allo startup dopo 1 min 30 sec e poi ogni 20 minuti
    @Scheduled(initialDelay = 90000, fixedDelay = 1200000)
    public void checkAndDeleteOlderLogFiles() {
    	
    	try {
	    	if(loggingFileDirPath == null || loggingFileHistoryMaxDays == null || loggingFileHistoryMaxSizeMB == null) {
	    		logger.warn("Impossibile eseguire il controllo dello svecchiamento dei file di log di storico. "
	    				+ "Almeno uno dei parametri obbligatori non presente: Dir path ["+loggingFileDirPath+"] History max days ["
	    				+loggingFileHistoryMaxDays+"] History max size MB ["+loggingFileHistoryMaxSizeMB+"]");
	    	} else {
		    	logger.info("Eseguo il controllo dello svecchiamento dei file di log di storico. Dir path ["+loggingFileDirPath
		    			+"] History max days ["+loggingFileHistoryMaxDays+"] History max size MB ["+loggingFileHistoryMaxSizeMB+"]");
		    	UtilityFile.deleteOlderLogFiles(loggingFileDirPath, Integer.parseInt(loggingFileHistoryMaxDays), 
		    			Integer.parseInt(loggingFileHistoryMaxSizeMB));
		    	logger.info("Completato il controllo dello svecchiamento dei file di log di storico.");
	    	}
	    	
    	} catch(IOException e) {
    		logger.error("Errore nel controllo dello svecchiamento dei file di log di storico: "+e.getMessage(), e);
    	}
    }
}
