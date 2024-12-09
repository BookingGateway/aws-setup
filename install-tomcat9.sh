#! /usr/bin/env bash
set -uvx
set -e
sudo yum install -y tomcat9.noarch
sudo yum install -y tomcat9-webapps.noarch tomcat9-admin-webapps.noarch
tomcat9 version
sudo systemctl enable tomcat9

