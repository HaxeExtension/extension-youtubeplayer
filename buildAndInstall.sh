#!/bin/bash
dir=`dirname "$0"`
cd "$dir"

EXTNAME="extension-youtubeplayer"

rm -rf $EXTNAME.zip

zip -r $EXTNAME.zip haxelib.json include.xml dependencies extension

haxelib remove $EXTNAME
haxelib local $EXTNAME.zip
