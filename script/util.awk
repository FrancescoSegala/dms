BEGIN {
    URL="localhost:8090/dms-misura-service/folders"
    HEADER="-H 'Content-Type: application/json'"
}


function body(id , description ){
    return "'{ \"id\": \"" trim(escape(id)) "\" , \"description\" : \"" trim(escape(description)) "\"  }'"
}

function escape( string ){
    gsub( /'/ , "'\"'\"'" , string )
    return string
}

function parse_response( id ){
    return "| jq . \
            | jq -c --arg id " id " ' if .status == null \
                    then {\"status\": 200 ,  \"id\": .id , \"description\": .description } \
                    else {\"status\":  .status , \"reason\":  .reason, \"id\" : $id} end'"
}

function parse( id ){
    return "| echo  $(date --iso-8601=seconds) " id ": $(awk 'NR==1 {printf( \"%s - \", $2 ) }; END {print }')"
}

function ltrim(s) { sub(/^[ \t\r\n]+/, "", s); return s }
function rtrim(s) { sub(/[ \t\r\n]+$/, "", s); return s }
function trim(s) { return rtrim(ltrim(s)); }
