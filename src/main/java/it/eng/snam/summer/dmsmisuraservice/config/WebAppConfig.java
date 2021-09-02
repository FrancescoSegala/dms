package it.eng.snam.summer.dmsmisuraservice.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import it.eng.snam.summer.common.SummerCommonLib;

@Configuration
public class WebAppConfig {

    // @Bean(name = "appDataSource")
    // @Primary
    // @ConfigurationProperties(prefix = "spring.datasource")
    // public DataSource dataSource() {
    //     return DataSourceBuilder.create().build();
    // }

    // @Bean(name = "applicationJdbcTemplate")
    // public JdbcTemplate applicationDataConnection(){
    //     return new JdbcTemplate(dataSource());
    // }
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
}
