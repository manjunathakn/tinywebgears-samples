#!/bin/sh

mvn -Dmaven.test.skip=true install
cd tinywebgears-appengine
mvn gae:deploy
cd -

