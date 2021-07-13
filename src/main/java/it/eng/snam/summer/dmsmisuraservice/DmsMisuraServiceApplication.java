package it.eng.snam.summer.dmsmisuraservice;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDSImpl;
import it.eng.snam.summer.dmsmisuraservice.service.dds.FakeDDSImpl;
import it.eng.snam.summer.dmsmisuraservice.service.summer.FakeSummerImpl;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerImpl;
import it.eng.snam.summer.dmsmisuraservice.util.UtilityControlM;

@SpringBootApplication
public class DmsMisuraServiceApplication {
	
	@Value("${controlm-jks.password:default}")
    private String password;
    
    @Value("${controlm-jks.truststore:default}")
    private String trustStorePath;

	public static void main(String[] args) {
		SpringApplication.run(DmsMisuraServiceApplication.class, args);
	}
	
	@PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Rome"));
        UtilityControlM.setSslProperties(password, trustStorePath);
    }

    @Bean
    public DDS dds(@Value("${external.dds.fake:false}") boolean fake ){
        return fake ? new FakeDDSImpl() : new DDSImpl();
    }

    @Bean
    public Summer summer(@Value("${external.summer.fake:false}") boolean fake){
        return fake ? new FakeSummerImpl() : new SummerImpl();
    }


}
