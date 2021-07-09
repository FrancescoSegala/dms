@include "util.awk"

{
     cmd="curl -s -i -X POST \"" URL "\" " HEADER " -d " body($1, $2) parse($1)
    print cmd
}



