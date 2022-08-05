#!/bin/bash
echo "Script executed from: ${PWD}"
cd "${BASH_SOURCE%/*}"

echo "Script executed from: ${PWD}"
cd "api-gateway/buildscripts"
chmod +x build.sh
./build.sh

exit
