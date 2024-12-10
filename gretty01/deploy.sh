#! /usr/bin/env bash
set -uvx
set -e
gradle clean
gradle war
cp ./build/libs/gretty01.war ~/tomcat/webapps/ROOT.war
