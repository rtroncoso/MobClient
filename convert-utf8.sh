#!/bin/sh
find ./ -name \*.java -type f | \
  (while read file; do
  iconv -f ISO-8859-1 -t UTF-8 "$file" > "${file%.java}.utf8";
  done);
