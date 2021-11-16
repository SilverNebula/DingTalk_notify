#! /bin/bash
echo "start"
title="test_rsync_1"
at_person="13453114137"
currentdate=$(date +%Y/%m/%d_%H:%M:%S)
#--------
#-your program
output=$(python testpy.py)
#--------
echo $output
python bot_message.py --title $title --at $at_person --time $currentdate