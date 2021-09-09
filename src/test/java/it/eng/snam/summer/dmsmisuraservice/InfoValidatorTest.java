// package it.eng.snam.summer.dmsmisuraservice;

// import java.util.List;

// import org.junit.jupiter.api.Test;

// import it.eng.snam.summer.dmsmisuraservice.util.Entity;
// import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.*;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;

// public class InfoValidatorTest {


//     List<Entity> context = listOf(new Entity().with("trueField", true)
//                                     .with("falseField", false)
//                                     .with("numberField", 14)
//                                     .with("stringField", "string")
//                                     .with("wrongDate", "2020!12_55")
//                                     .with("dateField", "2021-08-04T14:03:46Z")
//                                     .with("wrongRegex", "?:[1#9]\\d{q}-(?:(?:0[fgg]|1[7hj])-(")
//                                     .with("regexField", "(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)T(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d(?:Z|[+-][01]\\d:[0-5]\\d)")
//                                     .with("regexNotString", 22)
//                                     );

//     @Test
//     public void testRequired(){
//         isValid(required("stringField").apply(context));
//         isNotValid(required("fieldInesistente").apply(context));
//     }

//     @Test
//     public void testTrueOrFalse(){
//         isValid(trueOrFalse("fieldInesistente").apply(context));
//         isValid(trueOrFalse("testField").apply(context));
//         isValid(trueOrFalse("falseField").apply(context));
//         isNotValid(trueOrFalse("numberField").apply(context));
//         isNotValid(trueOrFalse("stringField").apply(context));
//         isNotValid(trueOrFalse("dateField").apply(context));
//     }

//     @Test
//     public void testNumber(){
//         isValid(number("fieldInesistente").apply(context));
//         isValid(number("numberField").apply(context));
//         isNotValid(number("stringField").apply(context));
//         isNotValid(number("dateField").apply(context));
//     }

//     @Test
//     public void testStringOf(){
//         isValid(stringOf("stringField", 6).apply(context));
//         isNotValid(stringOf("stringField", 5).apply(context));
//         isNotValid(stringOf("numberField", 1).apply(context));
//     }

//     @Test
//     public void testDate(){
//         isValid(date("dateField").apply(context));
//         isNotValid(date("wrongDate").apply(context));
//         isNotValid(date("stringField").apply(context));
//         isNotValid(date("numberField").apply(context));

//     }

//     @Test
//     public void testRegex(){
//         isValid(regex("dateField", context.get(0).getAsString("regexField")).apply(context));
//         isNotValid(regex("regexNotString", context.get(0).getAsString("regexField")).apply(context));
//         isNotValid(regex("wrongDate", context.get(0).getAsString("regexField")).apply(context));
//     }



//     private void isValid(String x){

//         assertNull(x);
//     }

//     private void isNotValid(String x){

//         assertNotNull(x);
//     }
// }
