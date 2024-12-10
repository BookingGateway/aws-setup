#! /usr/bin/env bash
set -uvx
set -e
sudo systemctl stop tomcat9
sudo systemctl disable tomcat9
sudo yum remove -y tomcat9-webapps.noarch tomcat9-admin-webapps.noarch
sudo yum remove -y tomcat9.noarch
sudo rm -rf /usr/share/tomcat9/
