#!/bin/bash

set -o errexit
set -o nounset

PROGRAM_DIR="/export/servers/yanyi-dictionary"
readonly MKDIR="mkdir -p -m 0755"

function make_directories()
{
    $MKDIR $PROGRAM_DIR/{bin,conf,lib}
}

function unarchive_tarball()
{
    tar -C $PROGRAM_DIR -xPf yanyi-dictionary-*.tar
}

function show_messages()
{
    echo "Yanyi dictionary has been installed to $PROGRAM_DIR"
    echo ""
    echo "What next:"
    echo ""
    echo "1. Run '$PROGRAM_DIR/bin/restart.sh' to start the daemon"
    echo ""
}

function main()
{
    make_directories
    unarchive_tarball
    show_messages
}

main $@
