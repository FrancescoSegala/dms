echo 'elaborazione' ISRS/RISCHI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ISRS/subfolders/RISCHI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ISRS/RISCHI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ISRS/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RISCHI" , "description" : "ISRS-RISCHI SPECIFICI"  }'| echo  $(date --iso-8601=seconds) ISRS/RISCHI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LRSTD/Risposte
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LRSTD/subfolders/Risposte" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LRSTD/Risposte
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LRSTD/subfolders" -H 'Content-Type: application/json' -d '{ "id": "Risposte" , "description" : "LRSTD - RISPOSTE A SEGUITO RICHIESTA DI DOC. PER ACCESSO IMP.REMI"  }'| echo  $(date --iso-8601=seconds) LRSTD/Risposte: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' AVAR/DIVIETO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/AVAR/subfolders/DIVIETO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' AVAR/DIVIETO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/AVAR/subfolders" -H 'Content-Type: application/json' -d '{ "id": "DIVIETO" , "description" : "AVAR - DIVIETO/RIPRISTINO ACCESSO IMP. REMI"  }'| echo  $(date --iso-8601=seconds) AVAR/DIVIETO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' AVAR/RIPRISTINO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/AVAR/subfolders/RIPRISTINO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' AVAR/RIPRISTINO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/AVAR/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RIPRISTINO" , "description" : "AVAR - DIVIETO/RIPRISTINO ACCESSO IMP. REMI"  }'| echo  $(date --iso-8601=seconds) AVAR/RIPRISTINO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COAR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COAR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COAR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COAR" , "description" : "COAR - AVVIO REMI"  }'| echo  $(date --iso-8601=seconds) COMM/COAR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/CONF
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/CONF" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/CONF
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CONF" , "description" : "CONF - VERBALE VERIFICA ATTIVAZIONE"  }'| echo  $(date --iso-8601=seconds) COMM/CONF: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COCO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COCO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COCO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COCO" , "description" : "COCO - COMUNICAZIONI: VARIE"  }'| echo  $(date --iso-8601=seconds) COMM/COCO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COCP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COCP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COCP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COCP" , "description" : "COCP - CAMBIO/AGGIORN. PRESS."  }'| echo  $(date --iso-8601=seconds) COMM/COCP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COCQ
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COCQ" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COCQ
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COCQ" , "description" : "COCQ - CAMBIO PORTATE"  }'| echo  $(date --iso-8601=seconds) COMM/COCQ: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COVA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COVA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COVA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COVA" , "description" : "COVA - INTERNE SRG VARIE"  }'| echo  $(date --iso-8601=seconds) COMM/COVA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COSC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COSC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COSC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COSC" , "description" : "COSC - DA SRG A CLIENTE"  }'| echo  $(date --iso-8601=seconds) COMM/COSC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COCS
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COCS" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COCS
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COCS" , "description" : "COCS - DA CLIENTE A SRG"  }'| echo  $(date --iso-8601=seconds) COMM/COCS: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COSS
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COSS" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COSS
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COSS" , "description" : "COSS - DA SRG A SHIPPER"  }'| echo  $(date --iso-8601=seconds) COMM/COSS: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COSH
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COSH" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COSH
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COSH" , "description" : "COSH - DA SHIPPER A SRG"  }'| echo  $(date --iso-8601=seconds) COMM/COSH: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COTL
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COTL" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COTL
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COTL" , "description" : "COTL - TELELETTURA"  }'| echo  $(date --iso-8601=seconds) COMM/COTL: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COTA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COTA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COTA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COTA" , "description" : "COTA - TELEALLARME"  }'| echo  $(date --iso-8601=seconds) COMM/COTA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/CODQ
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/CODQ" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/CODQ
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CODQ" , "description" : "CODQ - DATI QUALITA'"'"'"  }'| echo  $(date --iso-8601=seconds) COMM/CODQ: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COGC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COGC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COGC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COGC" , "description" : "COGC - GASCROMATOGRAFO"  }'| echo  $(date --iso-8601=seconds) COMM/COGC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COUM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COUM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COUM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COUM" , "description" : "COUM - UFFICIO METRICO"  }'| echo  $(date --iso-8601=seconds) COMM/COUM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COLE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COLE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COLE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COLE" , "description" : "COLE - CONTENZIOSI / PRATICHE LEGALI"  }'| echo  $(date --iso-8601=seconds) COMM/COLE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/VEMA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/VEMA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/VEMA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VEMA" , "description" : "VEMA - MAIL PER VERIFICA"  }'| echo  $(date --iso-8601=seconds) COMM/VEMA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/CORACO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/CORACO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/CORACO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CORACO" , "description" : "CORACO - Invio rapporto controllo VERIMP al Titolare"  }'| echo  $(date --iso-8601=seconds) COMM/CORACO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COMORG
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COMORG" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COMORG
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COMORG" , "description" : "COMORG - Condivisione organizzazione VERIMP con Titolare"  }'| echo  $(date --iso-8601=seconds) COMM/COMORG: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/CFOR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/CFOR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/CFOR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CFOR" , "description" : "CFOR - COMUNICAZIONI CON I FORNITORI"  }'| echo  $(date --iso-8601=seconds) COMM/CFOR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' COMM/COISRS
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/COMM/subfolders/COISRS" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' COMM/COISRS
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/COMM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COISRS" , "description" : "COISRS - Comunicazioni mail per ISRS"  }'| echo  $(date --iso-8601=seconds) COMM/COISRS: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-PV
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-PV" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-PV
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-PV" , "description" : "APIM-PV - LETTERA DI PRESA VISIONE"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-PV: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-VI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-VI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-VI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-VI" , "description" : "APIM-VI - RAPPORTO DI VERIFICA IMPIANTO"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-VI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-CF
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-CF" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-CF
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-CF" , "description" : "APIM-CF - RICHIESTA / COMUNICAZIONI DEL CLIENTE FINALE"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-CF: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-DP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-DP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-DP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-DP" , "description" : "APIM-DP - COMUNICAZIONE DEI DATI DI PROGETTO DEL CF"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-DP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-DC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-DC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-DC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-DC" , "description" : "APIM-DC - DICHIARAZIONE DI CONFORMITA'"'"'"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-DC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-EP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-EP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-EP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-EP" , "description" : "APIM-EP - ELENCO APPARATI"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-EP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-SC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-SC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-SC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-SC" , "description" : "APIM-SC - SCHEMA IMPIANTO"  }'| echo  $(date --iso-8601=seconds) APIM/APIM-SC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APIM/APIM-XX
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APIM/subfolders/APIM-XX" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APIM/APIM-XX
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APIM/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APIM-XX" , "description" : "APIM-XX - ALTRI DOC. NON RIENTRANTI NELLE CATEGORIE PREC."  }'| echo  $(date --iso-8601=seconds) APIM/APIM-XX: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-PV
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-PV" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-PV
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-PV" , "description" : "APRI-PV - LETTERA DI PRESA VISIONE"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-PV: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-VI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-VI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-VI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-VI" , "description" : "APRI-VI - RAPPORTO DI VERIFICA IMPIANTO"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-VI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-CF
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-CF" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-CF
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-CF" , "description" : "APRI-CF - RICHIESTA / COMUNICAZIONI DEL CLIENTE FINALE"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-CF: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-DP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-DP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-DP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-DP" , "description" : "APRI-DP - COMUNICAZIONE DEI DATI DI PROGETTO DEL CF"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-DP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-DC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-DC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-DC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-DC" , "description" : "APRI-DC - DICHIARAZIONE DI CONFORMITA'"'"'"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-DC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-EP
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-EP" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-EP
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-EP" , "description" : "APRI-EP - ELENCO APPARATI"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-EP: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-SC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-SC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-SC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-SC" , "description" : "APRI-SC - SCHEMA IMPIANTO"  }'| echo  $(date --iso-8601=seconds) APRI/APRI-SC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' APRI/APRI-XX
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/APRI/subfolders/APRI-XX" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' APRI/APRI-XX
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/APRI/subfolders" -H 'Content-Type: application/json' -d '{ "id": "APRI-XX" , "description" : "APRI-XX - ALTRI DOC. NON RIENTRANTI NELLE CATEGORIE PREC."  }'| echo  $(date --iso-8601=seconds) APRI/APRI-XX: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/MOGE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/MOGE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/MOGE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MOGE" , "description" : "MOGE - MODIFICA GENERICA"  }'| echo  $(date --iso-8601=seconds) MODIMP/MOGE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/MORG
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/MORG" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/MORG
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MORG" , "description" : "COMORG - Condivisione organizzazione VERIMP con Titolare"  }'| echo  $(date --iso-8601=seconds) MODIMP/MORG: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/MOMI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/MOMI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/MOMI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MOMI" , "description" : "MOMI - MODIFICA MISURA"  }'| echo  $(date --iso-8601=seconds) MODIMP/MOMI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/MOPM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/MOPM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/MOPM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MOPM" , "description" : "MOPM - MODIFICA PRESSIONE DI MISURA"  }'| echo  $(date --iso-8601=seconds) MODIMP/MOPM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/MOCO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/MOCO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/MOCO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MOCO" , "description" : "MOCO - MODIFICA  CONTATORE"  }'| echo  $(date --iso-8601=seconds) MODIMP/MOCO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/MODI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/MODI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/MODI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MODI" , "description" : "MODIMP - MODIFICA IMPIANTI"  }'| echo  $(date --iso-8601=seconds) MODIMP/MODI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' MODIMP/TEMA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/MODIMP/subfolders/TEMA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' MODIMP/TEMA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/MODIMP/subfolders" -H 'Content-Type: application/json' -d '{ "id": "TEMA" , "description" : "TEMA - MISURA AUTOMATIZZATA"  }'| echo  $(date --iso-8601=seconds) MODIMP/TEMA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' METRO/VMPR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/METRO/subfolders/VMPR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' METRO/VMPR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/METRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VMPR" , "description" : "VMPR - VERIFICA METRICA PRIMA IN FABBRICA"  }'| echo  $(date --iso-8601=seconds) METRO/VMPR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' METRO/VMPL
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/METRO/subfolders/VMPL" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' METRO/VMPL
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/METRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VMPL" , "description" : "VMPL - VERIFICA METRICA PRIMA SUL POSTO"  }'| echo  $(date --iso-8601=seconds) METRO/VMPL: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' METRO/VMPE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/METRO/subfolders/VMPE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' METRO/VMPE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/METRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VMPE" , "description" : "VMPE - VERIFICA METRICA PERIODICA"  }'| echo  $(date --iso-8601=seconds) METRO/VMPE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' METRO/VCPO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/METRO/subfolders/VCPO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' METRO/VCPO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/METRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VCPO" , "description" : "VCPO - COLLAUDO DI POSA IN OPERA"  }'| echo  $(date --iso-8601=seconds) METRO/VCPO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' METRO/VUPM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/METRO/subfolders/VUPM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' METRO/VUPM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/METRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VUPM" , "description" : "VUPM - UFF. PROVINCIALE METRICO VARIE"  }'| echo  $(date --iso-8601=seconds) METRO/VUPM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' METRO/UPMR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/METRO/subfolders/UPMR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' METRO/UPMR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/METRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "UPMR" , "description" : "UPMR - UFF. PROVINCIALE METRICO RIPROGRAMMAZIONI"  }'| echo  $(date --iso-8601=seconds) METRO/UPMR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FTCH
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FTCH" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FTCH
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FTCH" , "description" : "FTCH - INVERSIONE CONTATORI"  }'| echo  $(date --iso-8601=seconds) VERB/FTCH: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FTGU
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FTGU" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FTGU
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FTGU" , "description" : "FTGU - GUASTO CONTATORE"  }'| echo  $(date --iso-8601=seconds) VERB/FTGU: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FTEE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FTEE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FTEE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FTEE" , "description" : "FTEE - NUOVA INSTALL. O SOSTITUZIONE CONTATORE"  }'| echo  $(date --iso-8601=seconds) VERB/FTEE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FTTA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FTTA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FTTA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FTTA" , "description" : "FTTA - VERIFICA CONTATORE CON FT IN SERIE"  }'| echo  $(date --iso-8601=seconds) VERB/FTTA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FECH
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FECH" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FECH
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FECH" , "description" : "FECH - CAMBIO FLANGIA O TRONCO VENT."  }'| echo  $(date --iso-8601=seconds) VERB/FECH: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FEEE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FEEE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FEEE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FEEE" , "description" : "FEEE - NUOVA INSTALLAZIONE TRONCO VENTURIMETRICO"  }'| echo  $(date --iso-8601=seconds) VERB/FEEE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/CEDI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/CEDI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/CEDI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CEDI" , "description" : "CEDI - CERTIFICATO DI CALIB. DIAFRAMMA E TRONCO"  }'| echo  $(date --iso-8601=seconds) VERB/CEDI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FETA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FETA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FETA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FETA" , "description" : "FETA - VERIFICA CON MULTIVARIABILE"  }'| echo  $(date --iso-8601=seconds) VERB/FETA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FRGU
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FRGU" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FRGU
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FRGU" , "description" : "FRGU - GUASTO REGISTRATORI / DATA LOGGER"  }'| echo  $(date --iso-8601=seconds) VERB/FRGU: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FREE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FREE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FREE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FREE" , "description" : "FREE - NUOVA INSTALL. O SOSTITUZIONE REGISTRATORI / DATA LOGGER"  }'| echo  $(date --iso-8601=seconds) VERB/FREE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FRTA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FRTA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FRTA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FRTA" , "description" : "FRTA - TARATURA REGISTRATORI /DATA LOGGER"  }'| echo  $(date --iso-8601=seconds) VERB/FRTA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/TREE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/TREE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/TREE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "TREE" , "description" : "TREE - SOSTITUZIONE TRASMETTITORI"  }'| echo  $(date --iso-8601=seconds) VERB/TREE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/TRTA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/TRTA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/TRTA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "TRTA" , "description" : "TRTA - TARATURA TRASMETTITORI (ANCHE FR)"  }'| echo  $(date --iso-8601=seconds) VERB/TRTA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFGU
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFGU" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFGU
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFGU" , "description" : "FFGU - FLOW-COMPUTER GUASTO"  }'| echo  $(date --iso-8601=seconds) VERB/FFGU: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFME
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFME" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFME
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFME" , "description" : "FFME - MANCANZA ENERGIA ELETTRICA FF"  }'| echo  $(date --iso-8601=seconds) VERB/FFME: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/TLGU
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/TLGU" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/TLGU
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "TLGU" , "description" : "TLGU - GUASTO TELELETTURA"  }'| echo  $(date --iso-8601=seconds) VERB/TLGU: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFEE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFEE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFEE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFEE" , "description" : "FFEE - NUOVA INSTALL. O SOSTITUZIONE FF"  }'| echo  $(date --iso-8601=seconds) VERB/FFEE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFET
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFET" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFET
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFET" , "description" : "FFET - NUOVA INSTALL. TELEALLARME"  }'| echo  $(date --iso-8601=seconds) VERB/FFET: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFPR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFPR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFPR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFPR" , "description" : "FFPR - PROGRAMMAZIONE FF"  }'| echo  $(date --iso-8601=seconds) VERB/FFPR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFAN
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFAN" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFAN
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFAN" , "description" : "FFAN - PROGR. SOLO ANALISI FF"  }'| echo  $(date --iso-8601=seconds) VERB/FFAN: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/PELA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/PELA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/PELA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "PELA" , "description" : "PELA - POST ELABORAZIONE"  }'| echo  $(date --iso-8601=seconds) VERB/PELA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/AGPE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/AGPE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/AGPE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "AGPE" , "description" : "AGPE - AGGIORNAMENTO ANALISI PER POST ELABORAZIONE"  }'| echo  $(date --iso-8601=seconds) VERB/AGPE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFNE1
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFNE1" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFNE1
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFNE1" , "description" : "FFNE1 - PROGR. SOLO ANALISI - NON EFFETTUATA, ASSENZA CLIENTE"  }'| echo  $(date --iso-8601=seconds) VERB/FFNE1: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFNE2
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFNE2" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFNE2
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFNE2" , "description" : "FFNE2 - PROGR. SOLO ANALISI - NON EFFETTUATA, ASSENZA CLIENTE 2� TENTATIVO"  }'| echo  $(date --iso-8601=seconds) VERB/FFNE2: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFNE3
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFNE3" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFNE3
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFNE3" , "description" : "FFNE3 - PROGR. SOLO ANALISI - NON EFFETTUATA, RIFIUTO C.F. CON FIRMA"  }'| echo  $(date --iso-8601=seconds) VERB/FFNE3: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/FFNE4
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/FFNE4" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/FFNE4
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFNE4" , "description" : "FFNE4 - PROGR. SOLO ANALISI - NON EFFETTUATA, RIFIUTO C.F. SENZA FIRMA"  }'| echo  $(date --iso-8601=seconds) VERB/FFNE4: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/SDIVO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/SDIVO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/SDIVO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "SDIVO" , "description" : "SDIVO - SCHEDA D'"'"'INTERVENTO VOLUMETRICA"  }'| echo  $(date --iso-8601=seconds) VERB/SDIVO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/SDIVE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/SDIVE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/SDIVE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "SDIVE" , "description" : "SDIVE - SCHEDA D'"'"'INTERVENTO VENTURIMETRICA"  }'| echo  $(date --iso-8601=seconds) VERB/SDIVE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/VINT
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/VINT" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/VINT
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VINT" , "description" : "VINT - VERBALE DI INTERVENTO DA PORTALE MISURA"  }'| echo  $(date --iso-8601=seconds) VERB/VINT: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/COST
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/COST" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/COST
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "COST" , "description" : "COST - CONTROLLO STRUMENTAZIONE"  }'| echo  $(date --iso-8601=seconds) VERB/COST: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' VERB/VERI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/VERB/subfolders/VERI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' VERB/VERI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/VERB/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VERI" , "description" : "CONF - VERBALE VERIFICA ATTIVAZIONE"  }'| echo  $(date --iso-8601=seconds) VERB/VERI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' M3/M3RI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/M3/subfolders/M3RI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' M3/M3RI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/M3/subfolders" -H 'Content-Type: application/json' -d '{ "id": "M3RI" , "description" : "M3RI - MISURA DA APPARATI RISERVA"  }'| echo  $(date --iso-8601=seconds) M3/M3RI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' M3/M3FO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/M3/subfolders/M3FO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' M3/M3FO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/M3/subfolders" -H 'Content-Type: application/json' -d '{ "id": "M3FO" , "description" : "M3FO - FORFAIT"  }'| echo  $(date --iso-8601=seconds) M3/M3FO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' M3/M3CO
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/M3/subfolders/M3CO" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' M3/M3CO
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/M3/subfolders" -H 'Content-Type: application/json' -d '{ "id": "M3CO" , "description" : "M3CO - CONGUAGLI"  }'| echo  $(date --iso-8601=seconds) M3/M3CO: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' M3/M3TA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/M3/subfolders/M3TA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' M3/M3TA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/M3/subfolders" -H 'Content-Type: application/json' -d '{ "id": "M3TA" , "description" : "M3TA - VERIFICA APPARATI MISURA"  }'| echo  $(date --iso-8601=seconds) M3/M3TA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' M3/CONG
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/M3/subfolders/CONG" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' M3/CONG
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/M3/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CONG" , "description" : "M3CO - CONGUAGLI"  }'| echo  $(date --iso-8601=seconds) M3/CONG: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
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
echo 'elaborazione' FOTO/FOTO1
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO1" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO1
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO1" , "description" : "FOTO1 - PUNTO DI CONSEGNA TRASPORTATORE"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO1: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO2
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO2" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO2
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO2" , "description" : "FOTO2 - PANORAMICA CABINA REMI"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO2: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO3
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO3" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO3
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO3" , "description" : "FOTO3 - TRATTO DI MONTE"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO3: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO4
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO4" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO4
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO4" , "description" : "FOTO4 - FILTRAGGIO, PRERISCALDO, REGOLAZIONE"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO4: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO5
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO5" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO5
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO5" , "description" : "FOTO5 - PIPING MISURA + ORGANO PRIMARIO"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO5: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO6
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO6" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO6
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO6" , "description" : "FOTO6 - STRUMENTAZIONE"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO6: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO7
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO7" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO7
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO7" , "description" : "FOTO7 - MISURA AUTOMATIZZATA"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO7: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO8
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO8" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO8
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO8" , "description" : "FOTO8 - LOCALE CALDAIA + ODORIZZANTE"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO8: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' FOTO/FOTO9
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/FOTO/subfolders/FOTO9" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' FOTO/FOTO9
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/FOTO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FOTO9" , "description" : "FOTO9 - VARIE"  }'| echo  $(date --iso-8601=seconds) FOTO/FOTO9: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/VVDM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/VVDM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/VVDM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VVDM" , "description" : "VVDM - VERIFICA VERBALI DI MISURA"  }'| echo  $(date --iso-8601=seconds) ALTRO/VVDM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/CPIS
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/CPIS" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/CPIS
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CPIS" , "description" : "CPIS - CERT. PREVENZIONE INCENDI O SOSTITUTIVI"  }'| echo  $(date --iso-8601=seconds) ALTRO/CPIS: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/UTIF
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/UTIF" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/UTIF
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "UTIF" , "description" : "UTIF - UFF. TECNICO DI FINANZA-DOC. DOGANALI"  }'| echo  $(date --iso-8601=seconds) ALTRO/UTIF: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/VARI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/VARI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/VARI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VARI" , "description" : "COCO - COMUNICAZIONI: VARIE"  }'| echo  $(date --iso-8601=seconds) ALTRO/VARI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/ORDI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/ORDI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/ORDI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "ORDI" , "description" : "ORDI - ORDINATIVI / PREVENTIVI / FATTURE"  }'| echo  $(date --iso-8601=seconds) ALTRO/ORDI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/CONT
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/CONT" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/CONT
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CONT" , "description" : "CONT - CONTRATTI"  }'| echo  $(date --iso-8601=seconds) ALTRO/CONT: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/SPEC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/SPEC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/SPEC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "SPEC" , "description" : "SPEC - SPECIFICHE"  }'| echo  $(date --iso-8601=seconds) ALTRO/SPEC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' ALTRO/RACE
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/ALTRO/subfolders/RACE" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' ALTRO/RACE
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/ALTRO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "RACE" , "description" : "RACE - RAPPORTI CENTRALI"  }'| echo  $(date --iso-8601=seconds) ALTRO/RACE: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/VERB
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/VERB" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/VERB
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VERB" , "description" : "VERB - VECCHI DOC. NON CLASSIFICATI (METER READING)"  }'| echo  $(date --iso-8601=seconds) OLDOC/VERB: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/ALTR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/ALTR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/ALTR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "ALTR" , "description" : "ALTR - VECCHI DOC. NON CLASSIFICATI (METERING)"  }'| echo  $(date --iso-8601=seconds) OLDOC/ALTR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/GECR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/GECR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/GECR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "GECR" , "description" : "GECR - COMUNICAZIONI  DA/PER GECRE"  }'| echo  $(date --iso-8601=seconds) OLDOC/GECR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/VCVA
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/VCVA" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/VCVA
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VCVA" , "description" : "VCVA - VERBALE CONSTATAZIONE VERIFICA APPARECCHIATURE"  }'| echo  $(date --iso-8601=seconds) OLDOC/VCVA: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/ORAL 
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/ORAL " | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/ORAL 
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "ORAL" , "description" : "ORAL  - PUNTO DI RICONSEGNA (ALLACCIAMENTO)"  }'| echo  $(date --iso-8601=seconds) OLDOC/ORAL : $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/DIPM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/DIPM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/DIPM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "DIPM" , "description" : "DIPM - DICHIARAZIONI PRESSIONE MASSIMA"  }'| echo  $(date --iso-8601=seconds) OLDOC/DIPM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/PCON
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/PCON" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/PCON
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "PCON" , "description" : "PCON - PUNTO DI RICONSEGNA (PLANIMETRIE)"  }'| echo  $(date --iso-8601=seconds) OLDOC/PCON: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/FFRC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/FFRC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/FFRC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FFRC" , "description" : "FFRC - FLOW-COMP RATIFICA CONTRIBUTO"  }'| echo  $(date --iso-8601=seconds) OLDOC/FFRC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' OLDOC/VEIM
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/OLDOC/subfolders/VEIM" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' OLDOC/VEIM
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/OLDOC/subfolders" -H 'Content-Type: application/json' -d '{ "id": "VEIM" , "description" : "VEIM - SCHEDA VERIFICA IMPIANTO"  }'| echo  $(date --iso-8601=seconds) OLDOC/VEIM: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/ACCUGC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/ACCUGC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/ACCUGC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "ACCUGC" , "description" : "ACCUGC - Certificato Accuratezza GC"  }'| echo  $(date --iso-8601=seconds) LABO/ACCUGC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/GASINT
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/GASINT" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/GASINT
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "GASINT" , "description" : "GASINT - Rapporti di prova gas interni"  }'| echo  $(date --iso-8601=seconds) LABO/GASINT: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/FUMI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/FUMI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/FUMI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "FUMI" , "description" : "FUMI - Rapporti di prova analisi dei fumi"  }'| echo  $(date --iso-8601=seconds) LABO/FUMI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/ANAGAS
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/ANAGAS" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/ANAGAS
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "ANAGAS" , "description" : "ANAGAS - Rapporti di prova analisi gas"  }'| echo  $(date --iso-8601=seconds) LABO/ANAGAS: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/CERTMI
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/CERTMI" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/CERTMI
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CERTMI" , "description" : "CERTMI -  Certificati di taratura miscele"  }'| echo  $(date --iso-8601=seconds) LABO/CERTMI: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/CERTSTRU
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/CERTSTRU" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/CERTSTRU
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "CERTSTRU" , "description" : "CERTSTRU - Certificati di taratura strumentazione"  }'| echo  $(date --iso-8601=seconds) LABO/CERTSTRU: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/MANUPROC
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/MANUPROC" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/MANUPROC
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "MANUPROC" , "description" : "MANUPROC - Manuali e procedure"  }'| echo  $(date --iso-8601=seconds) LABO/MANUPROC: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
echo 'elaborazione' LABO/ODOR
if [ $(curl -I -s "localhost:8090/dms-misura-service/folders/LABO/subfolders/ODOR" | head -1 | awk '{print $2}') -ne 200 ]
  then 
 echo 'creazione' LABO/ODOR
curl -s -i -X POST "localhost:8090/dms-misura-service/folders/LABO/subfolders" -H 'Content-Type: application/json' -d '{ "id": "ODOR" , "description" : "ODOR - Bollettino di prova tasso odorizzazione"  }'| echo  $(date --iso-8601=seconds) LABO/ODOR: $(awk 'NR==1 {printf( "%s - ", $2 ) }; END {print }')
fi
