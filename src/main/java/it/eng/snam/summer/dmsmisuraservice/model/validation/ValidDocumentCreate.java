package it.eng.snam.summer.dmsmisuraservice.model.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = DocumentCreateValidator.class)
public @interface ValidDocumentCreate {

    String message() default "subfolder not valid";
}
