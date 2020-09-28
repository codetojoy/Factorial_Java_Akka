#!/bin/bash

TAG=parity.with.groovy.v2
MESSAGE="matches Groovy version via diff on out.csv, ignoring false info in out.log"

git tag -a $TAG -m "${MESSAGE}"
git push origin $TAG

