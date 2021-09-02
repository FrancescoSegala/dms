package it.eng.snam.summer.dmsmisuraservice.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import it.eng.snam.summer.dmsmisuraservice.DmsMisuraServiceApplication;
import it.eng.snam.summer.dmsmisuraservice.service.summer.ConoDatiService;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerSqlProvider;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = DmsMisuraServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ConoDatiServiceTest {

	@Autowired
	private ConoDatiService conoDatiService;
	
	@MockBean
	private SummerSqlProvider summerSqlProvider;
	
	@Test
	public void checkConoDatiTDoc() {
		
		when(summerSqlProvider.getTDocByProfilo(any())).thenReturn("VAPM,METRO,LABO");
		List<Object> listTDoc = conoDatiService.checkConoDatiTDoc("SEDE_MISURA");
		
		assertThat(listTDoc.size()==3);
		assertThat(listTDoc.contains("VAPM"));
		assertThat(listTDoc.contains("METRO"));
		assertThat(listTDoc.contains("LABO"));
		
	}
	
}
