package it.eng.snam.summer.dmsmisuraservice;


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

@SpringBootApplication
public class DmsMisuraServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsMisuraServiceApplication.class, args);
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
