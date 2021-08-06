events = _ e:event* _ {
let res = []
for (let i = 0; i< e.length ; i+= 2 ){
 res.push( {"value": e[i].value , "event": e[i+1].value.trim(), "type": e[i].field } )
}
return res
}

event = f:field _ v:value _  {
    let result = {"field": f , "value": v}
    return result
}

field = "data:" {return "data"}
    / "event:" {return "event"}

value = [A-Za-z0-9_ ]+ {return text()}
    / json

json = "{" _ pairs _ "}"  {
    return JSON.parse( text() )
}

pairs =  ( pair _ "," _ pairs ) / pair

pair =  string _ ":" _ string
    / string _ ":" _  number

number = [0-9]+

string = '"' v:value '"' {return v}

_ "whitespace"
  = [ \t\n\r]*
