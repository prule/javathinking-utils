# http://www.javathinking.com
#
# This is a convenience script I use to install all the useful applications I need 
# when I do a clean install of Ubuntu.
#
# You'll need to enable all of the software repositories in System/Admin/Software sources.

sudo wget http://www.medibuntu.org/sources.list.d/intrepid.list --output-document=/etc/apt/sources.list.d/medibuntu.list

# utilities
PACKAGES="istanbul gmountiso cups-pdf mysql-server mysql-client apache2"
PACKAGES="$PACKAGES wine ttf-gentium ttf-dustin ttf-georgewilliams ttf-sjfonts sun-java6-fonts ttf-larabie-deco ttf-larabie-straight ttf-larabie-uncommon"
PACKAGES="$PACKAGES smbfs"

# apps 
PACKAGES="$PACKAGES f-spot gimp inkscape"

# media
PACKAGES="$PACKAGES faad lame vlc libdvdcss picasa xine xine-ui amarok audacity"
PACKAGES="$PACKAGES rhythmbox k3b acidrip brasero avidemux mythtv-frontend skype"

# programming
PACKAGES="$PACKAGES groovy gant postgresql"

echo "Installing: $PACKAGES"

sudo apt-get -y --force-yes install $PACKAGES

  263  sudo apt-get install myth-archive
  264  sudo apt-get install mytharchive
  265  sudo apt-get install mythdvd
  285  sudo apt-get install qdvdauthor
  339  sudo apt-get install system-config-soundcard
  340  sudo apt-get install system-config-sound
  341  sudo apt-get install kde4
  342  sudo apt-get install kde
  353  sudo apt-get install alsaconf
  369  sudo apt-get install miro
  398  sudo apt-get install 
  421  sudo apt-get install 
  455  sudo apt-get install nagios
  456  sudo apt-get install requiem
  457  sudo apt-get install bluefish
  458  sudo apt-get install fluxbox
  459  sudo apt-get install e16 e16keyedit e16menuedit2 eterm
  460  sudo apt-get install back-in-time
  466  sudo apt-get install backintime-common backintime-gnome
  467  sudo apt-get install oes
  468  sudo apt-get install peazip
  499  sudo apt-get install flock
  507  history | grep install
p