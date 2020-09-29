#!/bin/bash

set -e

./gradlew -q clean cJ

LOG_FILE=out.log
rm -f $LOG_FILE

RANGE_SIZE=10
MAX=100
./gradlew -q run --args "$RANGE_SIZE $MAX" | tee $LOG_FILE

stat $LOG_FILE > /dev/null 2>&1

echo "count of log lines:"
wc -l $LOG_FILE
echo ""

echo "elapsed:"
grep -i elapsed $LOG_FILE
echo ""

echo "count of Reporter received:"
grep -i "tracer reporter" $LOG_FILE
