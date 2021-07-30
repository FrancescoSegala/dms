package it.eng.snam.summer.dmsmisuraservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuditValues {



    @Value("${external.audit.rest_client_request}")
    public boolean debugSQL = true;

    @Value("${external.audit.sql_client_response}")
    public boolean debugResponse;

    @Value("${external.audit.rest_client_headers}")
    public boolean rest_client_headers ;

    @Value("${external.audit.rest_client_response}")
    public boolean rest_client_response ;

    @Value("${external.audit.rest_client_request}")
    public boolean rest_client_request ;


}
