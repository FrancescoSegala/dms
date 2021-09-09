package it.eng.snam.summer.dmsmisuraservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
