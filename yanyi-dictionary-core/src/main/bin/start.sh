#!/bin/bash

cd "$(dirname $0)"
cd ..

readonly CONFIG_CENTER_HOME=`pwd`
readonly MAIN_CLASS="com.jd.logbook.configcenter.main.Application"
readonly CONFIG_PATH="${CONFIG_CENTER_HOME}/conf"
readonly BASE_LOG_DIR="/export/Logs/logbook-config-center"
CRON_ENTRY="*/1 * * * * ${CONFIG_CENTER_HOME}/bin/start.sh >> $BASE_LOG_DIR/console.log > /dev/null 2>&1"
CRON_ENTRY_ESCAPE=${CRON_ENTRY//\*/\\*}  # Escape all '*'
readonly GC_LOG="${BASE_LOG_DIR}/config_center_gc.log"
readonly ERROR_LOG="${BASE_LOG_DIR}/config_center_err.log"
readonly JAVA_OPTS="-server -XX:+PrintGCDetails -XX:+PrintGCDateStamps \
        -Xloggc:${GC_LOG} \
        -XX:+HeapDumpOnOutOfMemoryError \
        -XX:ErrorFile=${ERROR_LOG} \
        -XX:HeapDumpPath=/export/Logs/logbook-config-center/config_center_dump.hprof \
        -Xms{__MIN_MEMORY__} -Xmx{__MAX_MEMORY__} -Xmn{__YOUNG_GEN__} -Xss256k \
        -XX:MaxDirectMemorySize=1536M -XX:+ExplicitGCInvokesConcurrent \
        -XX:SurvivorRatio=7 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC \
        -XX:CMSMaxAbortablePrecleanTime=1500 -XX:+CMSParallelRemarkEnabled \
        -XX:+CMSScavengeBeforeRemark -XX:+UseCMSInitiatingOccupancyOnly \
        -XX:CMSInitiatingOccupancyFraction=68 -XX:GCTimeRatio=49"

export CLASSPATH="$CONFIG_CENTER_HOME/lib/*"
export LOGBOOK_CONFIG_CENTER_CFG_PATH="file:${CONFIG_PATH}/spring/application.xml"

if  [[ $(java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }' | grep 1.8) = "" ]];then
    export JAVA_HOME=/export/servers/jdk1.8.0_20
    export PATH=$JAVA_HOME/bin:$PATH
fi

function reg_auto_start
{
    if crontab -l | grep -q -e "^${CRON_ENTRY_ESCAPE}"; then
        echo "Crontab entry already installed, skipping"
    else
        echo "Installing crontab entry..."
        { crontab -l || true; echo "$CRON_ENTRY"; } | crontab
        crontab -l
    fi
}

pid=`jps -l | grep $MAIN_CLASS | awk '{print $1}'`

if [[ $pid != "" ]];then
    echo "Logbook config center has beean started, pid is ${pid}"
    exit 0
fi

if [[ ! -d $BASE_LOG_DIR ]];then
   mkdir -p $BASE_LOG_DIR
fi

if [[ ! -f $GC_LOG ]];then
    touch $GC_LOG
fi

echo "logbook-config-center is starting"

java $JAVA_OPTS -Dapp.name="logbook-config-center" $MAIN_CLASS &

reg_auto_start

sleep 0.5

pid=`jps -l | grep $MAIN_CLASS | awk '{print $1}'`

if [[ $pid == "" ]];then
    echo "Logbook config center not starte, please check" >> "${BASE_LOG_DIR}/console.log" 2>&1
    exit 1
fi
