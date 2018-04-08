#!/bin/bash

set -o errexit
set -o nounset

readonly YANYI_DICTIONARY_DIR="/export/servers/yanyi-dictionary"

$YANYI_DICTIONARY_DIR/bin/stop.sh
$YANYI_DICTIONARY_DIR/bin/start.sh
