#! /usr/bin/env bash
set -uvx
set -e
sudo systemctl start tomcat9
systemctl status tomcat9

