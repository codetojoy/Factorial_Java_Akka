#!/bin/bash

set -e

LOG_FILE=out.log

stat $LOG_FILE > /dev/null 2>&1

echo "count of log lines:"
wc -l $LOG_FILE
echo ""

echo "elapsed:"
grep -i elapsed $LOG_FILE
echo ""

echo "count of Worker DONE:"
grep -i "tracer worker.*done" $LOG_FILE | wc -l 

echo "count of Worker STOP:"
grep -i "tracer worker.*stop" $LOG_FILE | wc -l 

echo "Reporter received:"
grep -i "tracer reporter" $LOG_FILE
