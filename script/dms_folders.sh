#!/bin/bash


function remove_subfolders(){
    echo "removing subfolders...."
    awk -F ';' -f remove_subfolders.awk subfolders_new.csv | bash 
}

function remove_folders(){
    echo "removing folders...."
    awk -F ';' -f remove_folders.awk folders.csv | bash 
}


function create_folders(){
    echo "creating foloders"
    awk -F ';' -f create_folders.awk folders.csv | bash 
}

function create_subfolders(){
    echo "creating subfoloders"
    awk -F ';' -f create_subfolders.awk subfolders_new.csv | bash 
}

function remove_all() {
    remove_subfolders ;
    remove_folders ;
}


function create_all() {
    echo "creating folders"
    create_folders ; 
    echo "folders created "
    echo "creating subfolders"
    create create_subfolders ; 
}

#awk -F ';' -f remove_subfolders.awk subfolders_new.csv > file.sh
#awk -F ';' -f create_subfolders.awk subfolders_new.csv > file.sh
#awk -F ';' -f create_folders.awk folders.csv > file.sh
#awk -F ';' -f remove_folders.awk folders.csv > file.sh

case "$1" in
    remove_all ) remove_all
    ;;
    remove_subfolders ) remove_subfolders
    ;;
    remove_folders ) remove_folders
    ;;
    create_folders ) create_folders
    ;;
    create_subfolders ) create_subfolders
    ;;
    create_all ) create_all
    ;;
    * ) foldersHelp
esac

