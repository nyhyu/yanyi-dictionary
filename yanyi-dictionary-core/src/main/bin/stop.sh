#! /bin/bash

echo "Shutdown yanyi dictionary "
readonly YANYI_DICTIONARY_HOME=`pwd`
readonly BASE_LOG_DIR="/export/Logs/yanyi-dictionary"
CRON_ENTRY="*/1 * * * * ${YANYI_DICTIONARY_HOME}/bin/start.sh >> $BASE_LOG_DIR/console.log > /dev/null 2>&1"
CRON_ENTRY_ESCAPE=${CRON_ENTRY//\*/\\*}  # Escape all '*'
readonly YANYI_DICTIONARY_PROC="com.yanyi.core.Application"
pid=`jps -l | grep YANYI_DICTIONARY_PROC | awk '{print $1}'`

if [[ $pid != "" ]] ;then
    kill -9 $pid
fi

echo "Uninstalling crontab entry ..."
{ crontab -l | grep -v "${CRON_ENTRY_ESCAPE}" | grep -v "yanyi-dictionary"; } | crontab
crontab -l || true


