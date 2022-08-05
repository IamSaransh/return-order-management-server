#!/bin/bash
echo "Script executed from: ${PWD}"
cd "${BASH_SOURCE%/*}" || exit
export BASE_PATH=`pwd`;
echo "exported BASE_PATH as $BASE_PATH"

#modlule 1
cd "api-gateway/buildscripts" || exit
echo "Script executed from: ${PWD}"
chmod +x build.sh
./build.sh

echo "Going back to root directory"
cd $BASE_PATH
cd "authorization-server/buildscripts" || exit
echo "Script executed from: ${PWD}"
chmod +x build.sh
./build.sh

echo "Going back to root directory"
cd $BASE_PATH
echo "Script executed from: ${PWD}"
cd "packaging-and-delivery/buildscripts" || exit
chmod +x build.sh
./build.sh

echo "Going back to root directory"
cd $BASE_PATH
cd "component-processing-service/buildscripts" || exit
echo "Script executed from: ${PWD}"
chmod +x build.sh
./build.sh


exit
