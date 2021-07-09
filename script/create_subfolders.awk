@include "util.awk"


function url( folder ){
    return URL "/" trim(folder) "/subfolders"
}


{
    #print "echo " body( $2 , $3 ) "> data.json"
    cmd="curl -s -i -X POST \"" url($1) "\" " HEADER " -d " body( $2 , $3 ) parse( $1 "/" $2 )  #PARSE_RESPONSE($2)
    print cmd
}
