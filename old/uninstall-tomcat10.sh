#! /usr/bin/env bash
set -uvx
set -e
sudo yum remove -y tomcat10.noarch
sudo yum remove -y tomcat10-webapps.noarch tomcat10-admin-webapps.noarch

