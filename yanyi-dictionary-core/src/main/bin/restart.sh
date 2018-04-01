#!/bin/bash

set -o errexit
set -o nounset

readonly LOGBOOK_CONFIG_CNETER_DIR="/export/servers/logbook-config-center"

$LOGBOOK_CONFIG_CNETER_DIR/bin/stop.sh
$LOGBOOK_CONFIG_CNETER_DIR/bin/start.sh
