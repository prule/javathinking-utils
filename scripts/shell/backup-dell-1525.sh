#!/bin/sh

# A script using rsync to back up directories to a remote server.

# Follow the instructions here to avoid having to supply a password when using ssh:
#  http://linuxproblem.org/art_9.html



TIMESTAMP=`date +%Y%m%d-%H%M`
BACKUP_DIR=paul@mythtv:/media/disk/backup/dell-1525

#ssh paul@mythtv "mkdir -p /media/disk/backup/dell-1525/server /media/disk/backup/dell-1525/home/tools /media/disk/backup/dell-1525/home/paul /media/disk/backup/dell-1525/var /media/disk/backup/dell-1525/etc"

mkdir ~/backup/mysql/

mysqldump --all-databases --single-transaction -u root > ~/backup/mysql/mysql-all.sql

rsync -r /etc/hosts $BACKUP_DIR/etc/
rsync -r /etc/apache2/ $BACKUP_DIR/etc/apache2/
rsync -r /var/www/ $BACKUP_DIR/var/www/
rsync -r --exclude-from 'rsync-excludes.txt' /home/paul $BACKUP_DIR/home
rsync -r --exclude-from 'rsync-excludes.txt' /home/tools $BACKUP_DIR/home
rsync -r --exclude-from 'rsync-excludes.txt' /home/server $BACKUP_DIR/home


