package it.eng.snam.summer.dmsmisuraservice;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import it.eng.snam.summer.dmsmisuraservice.service.dds.DDS;
import it.eng.snam.summer.dmsmisuraservice.service.dds.DDSImpl;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerImpl;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerSqlProvider;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerSqlProviderImpl;
import it.eng.snam.summer.dmsmisuraservice.util.fake.FakeSummerSqlProvider;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
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
        //UtilityControlM.setSslProperties(password, trustStorePath);
    }

    @Bean
    public DDS dds(){
        return new DDSImpl();
    }

    @Bean
    public Summer summer(){
        return   new SummerImpl();
    }

    @Bean
    public SummerSqlProvider sqlProvider(@Value("${external.summer.fake:false}") boolean fake){
        return fake ? new FakeSummerSqlProvider() : new SummerSqlProviderImpl();
    }

}
