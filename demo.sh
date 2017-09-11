#!/usr/bin/env bash

set -x

sbt clean; stty echo
sbt test; stty echo
sbt test; stty echo

sbt << __EOF__
clean
test
test
reload
test
exit
__EOF__

sbt << __EOF2__
test
exit
__EOF2__

stty echo
