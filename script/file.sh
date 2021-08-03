echo 'elaborazione' VERBNM/VERBNM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/VERBNM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/VERBNM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VERBNM" , "description" : "VERBNM - VERBALI DI INTERVENTO NO MISURA"  }'| echo  $(date --iso-8601=seconds) VERBNM/VERBNM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMFP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMFP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMFP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMFP" , "description" : "RMFP - CHIUSURA REMI STAGIONALE/TEMPORANEA/DEFINITIVA"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMFP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMIP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMIP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMIP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMIP" , "description" : "RMIP - RIAPERTURA REMI A SEGUITO DI RMFP"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMIP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMPR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMPR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMPR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMPR" , "description" : "RMPR - CABINA PROVVISORIA"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMPR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMEE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMEE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMEE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMEE" , "description" : "RMEE - APERTURA NUOVA CABINA REMI"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMEE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMCB
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMCB" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMCB
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMCB" , "description" : "RMCB - INS. / DISINS. CARRO BOMBOLAIO"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMCB: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMCC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMCC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMCC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMCC" , "description" : "RMCC - MANUTENZIONE IMPIANTO REMI"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMCC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMCH
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMCH" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMCH
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMCH" , "description" : "RMCH - SOSTITUZIONE APPARATI VARI"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMCH: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMCP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMCP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMCP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMCP" , "description" : "RMCP - CAMBIO PRESSIONE DI MISURA"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMCP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMLS
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMLS" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMLS
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMLS" , "description" : "RMLS - LAVORI SNAM"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMLS: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERBNM/RMTA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERBNM/subfolders/RMTA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERBNM/RMTA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERBNM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RMTA" , "description" : "RMTA - MANUTENZIONE RIDUTTORI"  }'| echo  $(date --iso-8601=seconds) VERBNM/RMTA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
