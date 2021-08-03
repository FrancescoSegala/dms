@include "util.awk"


function url( folder ){
    return URL "/" trim(folder) "/subfolders"
}



{
    #print "echo " body( $2 , $3 ) "> data.json"
    print "echo 'elaborazione' " $1 "/" $2
    print "if [ $(curl -I -s \"" url($1) "/" $2 "\" | head -1 | awk '{print $2}') -ne 200 ]"
    print "  then "
    print " echo 'creazione' " $1 "/" $2
    cmd="curl -s -i -X POST \"" url($1) "\" " HEADER " -d " body( $2 , $3 ) parse( $1 "/" $2 )  #PARSE_RESPONSE($2)
    print cmd
    print "fi"
    #test = "if [ $(curl -I -s \"" url($1) "/" $2 "\" | head -1 | awk '{print $2}') -ne 200 ] ; then ; "
    #print test  cmd  " ; fi"
}
