#!/bin/bash
#
# This script is to bulid a self extractable installer with all dependencices
# packed.  Expect to be run inside a clean centos 6 image (e.g. standard docker
# image centos:6) suggest to use a image with a fast yum mirror (e.g. aliyun).
#
# Base image available at,use minimal image temporarily
#
#   - http://192.168.104.19:5000/logbook/centos6-minimal
#   - https://192.168.104.19:5000/logbook/centos6-runtime

set -o errexit
set -o nounset

readonly SELF_DIR=$(cd $(dirname $0) && pwd) || exit 1
readonly TARBALL_NAME_PREFIX="yanyi-dictionary-pack"
readonly INSTALLER_NAME_PREFIX="yanyi-dictionary-installer"
readonly BINARY_DIR="binary"

function install_yanyi_dictionary
{
    mkdir -p ${BINARY_DIR}
    echo "Compiler yanyi dictionary..."
    (set -o errexit nounset; rm -rf yanyi-dictionary-core/target/*; mvn package)
}

function make_self
{
    local version=$(git describe --long | sed -r 's/-g?/./g')
    local tarball="${TARBALL_NAME_PREFIX}-${version:?}.tar"
    local installer="${INSTALLER_NAME_PREFIX}-${version:?}.bin"

    cp -r yanyi-dictionary-core/target/yanyi-dictionary-core/* ${BINARY_DIR}
    chmod 755 ${BINARY_DIR}/bin/*

    rm -rf target
    mkdir target
    cp setup.sh target
    (cd ${BINARY_DIR} && tar -cf ../target/$tarball *)
    rm -rf ${BINARY_DIR}

    makeself.sh target $installer "yanyi dictionary $version" ./setup.sh
}

function main
{
    install_yanyi_dictionary
    make_self
}

main "$@"
