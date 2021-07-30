@include "util.awk"

BEGIN {
    URL="localhost:8090/dms-misura-service/documents"
    HEADER="-H 'Content-Type: multipart/form-data;'"
}

function createbody( folder, subfolder, name ,title ,info ){
    return "'{ \"folder\": \"" trim(escape(folder)) "\" , \"subfolder\" : \"" trim(escape(subfolder)) "\" ,\"name\" : \"" trim(escape(name)) "\", \"title\" : \"" trim(escape(title)) "\" , \"info\" : \"" trim(escape(info)) "\" }'"
}



{
    cmd="curl -s -i -X POST \"" URL "\" " HEADER " -F  'document=" createbody($1, $2, "name", "title", "info") "'"
    print cmd
}
