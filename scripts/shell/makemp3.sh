# http://www.javathinking.com
#
# This script produces an mp3 audio track from an mp4 file.
# It could be useful if you have MP4 files (such as TED podcasts)
# but don't have a portable video player.
#
# Install dependencies
#  sudo apt-get install faad lame
# 
# Sample use:
#  -- get a sample mp4 file to work with
#  wget http://www.ted.com/index.php/talks/download/video/3149/talk/364
#  unzip JamesBurchfield_2003-\[None\].zip
#  -- extract mp3 
#  sh makemp3.sh JamesBurchfield_2003-\[None\].mp4 
#  
# This results in the file 'JamesBurchfield_2003-[None].mp4.mp3'

file=$1
echo "makemp3 Converting ${file}"
WAV="${file}.tmp.wav"
faad -o "${WAV}" "${file}"
lame -V 9 --tt "${file}" --ta "${file}" --tl "${file}" "${WAV}" "${file}.mp3"
rm -f ${WAV} 
	