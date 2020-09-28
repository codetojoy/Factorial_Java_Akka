#!/bin/bash 

set -e 

./gradlew -q clean cJ

LOG_FILE=out.log
rm -f $LOG_FILE

./gradlew -q run | tee $LOG_FILE 

stat $LOG_FILE > /dev/null 2>&1

echo "count of log lines:"
wc -l $LOG_FILE
echo ""

echo "elapsed:"
grep -i elapsed $LOG_FILE
echo ""

echo "count of Reporter received:"
grep -i "tracer.*reporter.*received" $LOG_FILE | wc -l 
