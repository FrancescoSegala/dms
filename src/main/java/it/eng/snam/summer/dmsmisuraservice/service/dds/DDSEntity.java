package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.isEmpty;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;

public abstract class DDSEntity {

    @Autowired
    DDSRestProvider rest;

    @Value("${external.dds.OS}")
    protected String os;


    protected abstract String sortField( String field );

    protected String clause(String field, String value, String operator) {
        return isEmpty(value) ? "" : String.format(" %s %s '%s' ", field, operator, value);
    }

    protected String clause(String field , Boolean value ) {
        return value == null ? "" : String.format(" %s = %s ", field,  value);
    }

    protected String clause(String field, String value, String operator, String prefix, String postfix){
        return isEmpty(value) ? "" : String.format(" %s %s '%s%s%s' ", field, operator, prefix ,value, postfix);
    }

    protected String clause(String field, String[] values, String operator) {
        //@formatter:off
        return isEmpty(values) ?
         "" :
          String.format(" %s %s ( %s ) ",
           field, operator, listOf(values)
                .stream().map(e -> "'"+ e + "'")
                .collect(Collectors.joining(","))
            );
        //@formatter:on
    }

    protected String pagination(Pagination p) {
        //@formatter:off
        return (isEmpty( p.getSort() ) ? "" : " order by " + sortField(p.getSort()) + " " + p.getDirection())
        + ( " limit " + p.getLimit() )
        + ( " offset " + p.getOffset());
        //@formatter:on
    }


    protected abstract List<String> clauses(Pagination p);


    /**
     * it requires clauses function to be implemented in the child class
     * @param p DTO that creates the condtion for dds calls
     * @return the string representing the where condition for the ms call
     */
    protected String where(Pagination p) {
        //@formatter:off
        System.out.println(clauses(p));
        return Stream.concat(
            listOf("_id is not null and name != '/parentFolder' ").stream(),
            clauses(p).stream())
        .filter(e -> ! isEmpty(e))
        .collect(Collectors.joining(" and "))
        + pagination(p);
        //@formatter:on
    }

}
