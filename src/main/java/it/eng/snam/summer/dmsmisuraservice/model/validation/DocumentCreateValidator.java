package it.eng.snam.summer.dmsmisuraservice.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;

public class DocumentCreateValidator implements ConstraintValidator<ValidDocumentCreate, DocumentCreate>  {

    @Override
    public boolean isValid(DocumentCreate value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }









}
