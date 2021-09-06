package it.eng.snam.summer.dmsmisuraservice.util;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.search.IdSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;

public class SnamSQLClient {

    @Value("${external.rest_client_request}")
    private boolean debugSQL = true;
    @Value("${external.debug.sql_client.response}")
    private boolean debugResponse;
    // @Autowired
    // AuditValues audit;

    private NamedParameterJdbcOperations template;
    private String table;
    private Pagination pagination;

    private static Map<String, String> operators = new Entity().with("_eq", "=").with("_ge", ">=").with("_le", "<=")
            .with("_like", "like").with("_in", "in").toMap();

    public SnamSQLClient(NamedParameterJdbcOperations template) {
        this.template = template;
    }

    public SnamSQLClient withTable(String table) {
        this.table = table;
        return this;
    }

    public SnamSQLClient withParams(Pagination p) {
        this.pagination = p;
        return this;
    }

    public List<Entity> list() {
        String where = "from " + table + " where 1=1 " + conditions();
        String sql = "select * " + where + getOrderByString() + " offset " + pagination.getOffset()
                + " rows fetch first " + pagination.getLimit() + " rows only ";

        if (debugSQL) {
            System.out.println(sql);
            System.out.println(params());
        }
        return template.queryForList(sql, params()).stream().map(Entity::build).collect(Collectors.toList());
    }

    public Long count() {
        String where = "from " + table + " where 1=1 " + conditions();
        String sql = "select count(*) " + where;
        if (debugSQL) {
            System.out.println(sql);
            System.out.println(params());
        }
        return template.queryForObject(sql, params(), Long.class);
    }

    public Map<String, Long> countByField(String field) {
        String where = " from " + table + " where 1=1 " + conditions();
        String sql = "select count(*) as count , " + field + where + " group by " + field;
        if (debugSQL) {
            System.out.println(sql);
            System.out.println(params());
        }
        return template.queryForList(sql, params()).stream()
                .collect(Collectors.toMap(e -> (String) e.get(field), e -> Long.parseLong(e.get("count").toString())));
    }

    private String conditions() {
        String res = pagination.notNulls()
                .map(e -> String.format(" %s %s %s", column(e.getName()), operator(e.getName()), name(e.getName())))
                .collect(Collectors.joining(" and "));
        return res.isEmpty() ? "" : "AND " + res;
    }

    private String name(String f) {
        String inOrDefault = "in".equals(operator(f)) ? "(:" + f + ")"  : ":" + f;
        return "like".equals(operator(f)) ? "'%'+:" + f + "+'%'" :   inOrDefault;
    }

    private String column(String f) {
        return "=".equals(operator(f)) ? f : f.substring(0, f.lastIndexOf("_"));
    }

    private String operator(String f) {
        return operators.keySet().stream().filter(e -> f.endsWith(e)).findFirst().map(e -> operators.get(e))
                .orElse("=");
    }

    private Map<String, Object> params() {
        return pagination.notNulls().collect(Collectors.toMap(f -> f.getName(), f -> {
            try {
                return f.get(pagination);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }));
    }

    private String getOrderByString() {
        if (pagination.getSort() == null || pagination.getDirection() == null || pagination.getSort().isEmpty()
                || pagination.getDirection().isEmpty() || pagination.getSort().equals("no_sort")) {
            return " order by id";
        }
        String dir = listOf("asc", "desc").stream().filter(e -> e.equals(pagination.getDirection().toLowerCase()))
                .findAny().orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Direction must be 'asc' or 'desc'"));
        return " order by " + pagination.getSort() + " " + dir;
    }

    public Entity get() {
        Entity res = find();
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        }
        return res;
    }

    public int insert(Entity entity) {
        String fieldList = entity.keySet().stream().collect(Collectors.joining(","));
        String paramList = entity.keySet().stream().map(e -> ":" + e).collect(Collectors.joining(","));

        String sql = String.format("INSERT INTO %s (%s) VALUES(%s)", table, fieldList, paramList);
        int rows = template.update(sql, entity);
        if (debugSQL) {
            System.out.println(sql);
            System.out.println(entity);
            System.out.println("affected " + rows + " rows");
        }
        // return find(entity.id());
        return rows;
    }

    public int update(Entity entity, String id ) {
        String paramList = entity.entrySet().stream().filter(e -> !e.getKey().equals("id"))
                .map(e -> e.getKey() + "=:" + e.getKey()).collect(Collectors.joining(","));
        String sql = String.format("update %s set %s where id = '%s'", table, paramList,  id );
        return template.update(sql, entity);
    }

    public void delete(String id) {
        String sql = "";
        int rows = template.update(sql, mapOf("id", id));
        if (debugSQL) {
            System.out.println(sql);
            System.out.println(params());
            System.out.println("affected " + rows + " rows");
        }
    }

    public Entity find(String id) {
        IdSearch idSearch = new IdSearch(id);
        this.pagination = idSearch;
        return this.find();
    }

    public Entity find() {
        List<Entity> res = list();
        return res.isEmpty() ? null : res.get(0);
    }

    public Boolean exist() {
        return find() != null;
    }

    public Boolean notExist() {
        return !exist();
    }

}
