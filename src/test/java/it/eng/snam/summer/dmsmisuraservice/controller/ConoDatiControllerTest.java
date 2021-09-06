package it.eng.snam.summer.dmsmisuraservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import it.eng.snam.summer.dmsmisuraservice.DmsMisuraServiceApplication;
import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.service.summer.ConoDatiService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = DmsMisuraServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ConoDatiControllerTest {


    @Autowired
    ConoDatiController controller ;
    
    @MockBean
    ConoDatiService conoDatiService;
    
    @MockBean
    Authentication authentication;
    
    @MockBean
    SecurityContextHolder securityContextHolder;
    

    //SecurityContextHolder.getContext().getAuthentication()

    @Test
    public void testConoDatiTDoc() throws Exception{

    	List<Object> listaTDoc = new ArrayList<>();
    	listaTDoc.add("INTE");
    	listaTDoc.add("MAOP");
    	when(conoDatiService.checkConoDatiTDoc(any())).thenReturn(listaTDoc);
    	when(authentication.getPrincipal()).thenReturn("SEDE_MISURA");
    	SecurityContext securityContext = mock(SecurityContext.class);
    	SecurityContextHolder.setContext(securityContext);
    	when(securityContext.getAuthentication()).thenReturn(authentication);
    	
    	
    	ResponseEntity<?> response = controller.checkConoDatiTDoc("RI01670");

    	assertThat(response.getStatusCode()==HttpStatus.OK);
    	List<String> lista = (List<String>) response.getBody();
    	
    	assertThat(lista.size()==2);
    	assertThat(lista.get(0).equalsIgnoreCase("INTE"));
    	assertThat(lista.get(1).equalsIgnoreCase("MAOP"));

    }
}
