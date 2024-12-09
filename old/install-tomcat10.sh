#! /usr/bin/env bash
set -uvx
set -e
sudo yum install -y tomcat10.noarch
sudo yum install -y tomcat10-webapps.noarch tomcat10-admin-webapps.noarch
tomcat10 version
sudo systemctl enable tomcat10

