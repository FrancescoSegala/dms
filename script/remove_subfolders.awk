BEGIN {
    URL="localhost:8090/dms-misura-service/folders"
}


function url( folder , subfolder ){
    return URL "/" folder "/subfolders/" subfolder
}


function ltrim(s) { sub(/^[ \t\r\n]+/, "", s); return s }
function rtrim(s) { sub(/[ \t\r\n]+$/, "", s); return s }
function trim(s) { return rtrim(ltrim(s)); }

{
    cmd="curl -X DELETE \"" url($1, trim($2) ) "\" "
    print cmd
}
