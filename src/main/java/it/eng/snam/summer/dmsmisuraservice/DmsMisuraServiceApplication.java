package it.eng.snam.summer.dmsmisuraservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import it.eng.snam.summer.dmsmisuraservice.service.DDS;
import it.eng.snam.summer.dmsmisuraservice.service.DDSImpl;
import it.eng.snam.summer.dmsmisuraservice.service.FakeDDSImpl;

@SpringBootApplication
public class DmsMisuraServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsMisuraServiceApplication.class, args);
	}

    @Bean
    public DDS dds(@Value("${external.dds.fake:false}") boolean fake ){
        return fake ? new FakeDDSImpl() : new DDSImpl();
    }


}
