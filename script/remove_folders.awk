
BEGIN {
    URL="localhost:8090/dms-misura-service/folders"
    HEADER="-H 'Content-Type: application/json'"
}
{
    cmd="curl -X DELETE \"" URL "/" $1 "\""
    print cmd
}
