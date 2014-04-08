#!/bin/sh

ASSEMBLY_PATH=target/stereoswitch-0.1-SNAPSHOT-assembly/stereoswitch-0.1-SNAPSHOT

JAR_PATH="" 
for JAR in `ls $ASSEMBLY_PATH/lib/*.jar`
do
  if [ -z "${JAR_PATH}" ]
  then
    JAR_PATH="${JAR}"
  else
    JAR_PATH="${JAR_PATH}:${JAR}"
  fi
done

exec java -cp "${JAR_PATH}" "dk.cirque.stereoswitch.Server" $@
