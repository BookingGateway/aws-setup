#! /usr/bin/env bash
set -uvx
set -e
sudo systemctl start tomcat10
systemctl status tomcat10

