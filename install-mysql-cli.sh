#! /usr/bin/env bash
set -uvx
set -e
sudo dnf -y localinstall https://dev.mysql.com/get/mysql80-community-release-el9-1.noarch.rpm
sudo rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2023
sudo dnf -y install mysql mysql-community-client
mysql --version

