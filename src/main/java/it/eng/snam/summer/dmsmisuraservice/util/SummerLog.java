package it.eng.snam.summer.dmsmisuraservice.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Da impostare sui metodi che si vuole loggare.<br>
 * Se si vuole stampare anche il tempo di esecuzione, valorizzare a <i>true</i> il parametro <i>"logExecutionTime"</i>
 * Se si vuole stampare anche l'input, valorizzare a <i>true</i> il parametro <i>"logInputParameter"</i>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SummerLog {

	/**
	 * Settare a <i>true</i> per loggare anche i parametri di input.<br>
	 * Default impostato a <i>false</i>.<br>
	 * <b>NON USARE CON OGGETTI COMPLESSI</b>
	 */
	public boolean logInputParameter() default false;

	/**
	 * Settare a <i>true</i> per loggare anche il tempo di esecuzione.<br>
	 * Default impostato a <i>false</i>.
	 */
    public boolean logExecutionTime() default false;

    /**
     * Setta il livello di log dell'annotation.<br>
     * Impostare a <i>false</i> per avere log a livello <i>info</i>, impostare a <i>true</i> per avere log a livello <i>debug</i>.<br>
	 * Default impostato a <i>false</i>.
	 */
    public boolean debugLevelLog() default false;

}
