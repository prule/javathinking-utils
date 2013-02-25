#!/bin/sh
if [ $# -ne 2 ]; then
 	echo 'Usage: splitdb <file produced from mysqldump> <dbname to extract>'
    exit 127
fi

fname="`which splitdb.sh`"
fname=`dirname $fname`
echo $fname
groovy $fname/../groovy/splitdb.groovy $*

exit 0