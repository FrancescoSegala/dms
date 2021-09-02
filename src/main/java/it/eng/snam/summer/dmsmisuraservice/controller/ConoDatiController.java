package it.eng.snam.summer.dmsmisuraservice.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.eng.snam.summer.common.annotation.RequestAuthority;
import it.eng.snam.summer.common.annotation.SummerLog;
import it.eng.snam.summer.common.combo.AuthorityEnum;
import it.eng.snam.summer.common.exception.NotAlreadyExistsException;
import it.eng.snam.summer.dmsmisuraservice.service.summer.ConoDatiService;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;


@CrossOrigin
@RestController
public class ConoDatiController {
	@Autowired
	Summer summer ;

	@Autowired
	ConoDatiService conoDatiService;





	@GetMapping("/check-cono-dati-tdoc")
	@SummerLog(logExecutionTime = true)
	@RequestAuthority(AuthorityEnum.SUPER_DMS)
	@ApiOperation(value = "", response = Void.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Dati trovati", response = Void.class),
			@ApiResponse(code = 400, message = "Dati input errati", response = Void.class)
	})
	public ResponseEntity<?> checkConoDatiTDoc(
			@ApiParam(value = "User ID dell'utente", example = "RID00777", required = true)
			@RequestParam(required = true, name = "userID") @NotEmpty String userID
			) {


		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			//da authentication è possibile recujperare il Profilo (tramite la proprietà Principale)
			//e la lista delle funzionalità cui l'utente loggato è abilitato
			

			List<Object> listTDoc = conoDatiService.checkConoDatiTDoc(userID);

			return new ResponseEntity<>(listTDoc, HttpStatus.OK);

		}  catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}




}
