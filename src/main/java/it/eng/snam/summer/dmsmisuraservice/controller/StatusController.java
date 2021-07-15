package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.mapOf;
import it.eng.snam.summer.dmsmisuraservice.util.SummerLog;

@RestController
@RequestMapping("/api")
public class StatusController {



    @GetMapping("/status")
    @SummerLog( logInputParameter =  true, debugLevelLog = true , logExecutionTime = true)
    public Map<String, Object> status(){
    	return mapOf("hello", "world");
    }

}
