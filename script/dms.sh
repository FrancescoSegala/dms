#!/bin/bash
export SERIVCE_URL="localhost:8090/dms-misura-service"


function generate_body() {
    cat <<EOF
        {
            "id": "$1",
            "description" : "$2"
        }
EOF
}

function post {
    curl --location --request POST "$SERIVCE_URL/folders" \
    --header 'Content-Type: application/json' \
    --data "$(generate_body "$1" "$2")"
}

function split_csv_string (){
    IFS=';'
    read -a strarr <<< "$1"
    echo ${strarr[$2]}
}

function read_file(){
    while IFS= read -r line;
     do
        FOLDER=$(split_csv_string $line 0)
        DESCRIPTION=$(split_csv_string $line 1)
        echo $FOLDER
        echo $DESCRIPTION
     done < folders.txt
}


function remove_all() {

}

#awk -F ';' -f create_subfolders.awk folders.csv | bash

#awk -F ';' -f create_folders.awk folders.txt | bash

case "$1" in
    remove_all ) remove_all
    ;;
    remove ) remove
    ;;
    build ) build
    ;;
    * ) fodlersHelp
esac

