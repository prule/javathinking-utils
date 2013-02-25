/**

 echo /home/paul/Podcasts=1218823213000 > .javathinking/copy.properties
 groovy copy.groovy -s ~/Podcasts/ -d /tmp
 Copying from /home/paul/Podcasts where lastmod > 1225601077000
 1225602077000 - /home/paul/Podcasts/test/The Java Posse
 1225602077000 - /home/paul/Podcasts/test/The Java Posse/2.mp3
 1225602077000 - /home/paul/Podcasts/test/The Java Posse/1.mp3

 */


@Grab(group = 'commons-io', module = 'commons-io', version = '1.4')

def copy() {


    def propsDir = new File(System.getProperty("user.home"), ".javathinking")
    propsDir.mkdirs()

    def properties = new java.util.Properties()
    def propsFile = new File(propsDir, "copy.properties")
    if (propsFile.exists())
        properties.load(new java.io.FileInputStream(propsFile));


    def cli = new CliBuilder(usage: 'groovy copy -s sourcedir -d destdir')
    cli.h(longOpt: 'help', 'usage information')
    cli.d(longOpt: 'dest', required: true, 'directory to copy to', args: 1)
    cli.s(longOpt: 'source', required: true, 'directory to copy from', args: 1)

    def opt = cli.parse(args)
    if (!opt) {
        return
    }
    if (opt.h) {
        cli.usage();
        return
    }

    def source = new File(opt.s)
    def dest = new File(opt.d)
    def lastCopy = 0
    if (properties.containsKey(source.getCanonicalPath()))
        lastCopy = Long.parseLong(properties.getProperty(source.getCanonicalPath()))

    println "Copying from ${source.getCanonicalPath()} where lastmod > ${lastCopy}"
    if (source.exists()) {
        def files = []
        // build a list of all the files to check
        source.eachDirRecurse { dir ->
            dir.eachFile() { file ->
                files << file
            }
        }
        // sort by date
        files.sort { it.lastModified() }
        // now process the list
        def copyCount = 0
        def fileCount = 0
        def copySize = 0
        def outOfSpace = false
        files.each() { file ->
            if (file.isFile() && dest.getFreeSpace() > file.length()) {
                fileCount++
//			println "${file.lastModified()} -- ${lastCopy}"
                if (file.lastModified() > lastCopy) {
                    def to = new File(dest, file.getName())
                    copySize += file.length()
                    copyCount++
                    println file.lastModified() + ' - ' + to.getAbsolutePath()
                    FileUtils.copyFile(file, to)
                    properties.setProperty(source.getCanonicalPath(), "${file.lastModified()}")
                }
            } else {
                outOfSpace = true
            }
        }
        if (outOfSpace) {
            println "Could not complete due to lack of disk space."
        }
        properties.save(new java.io.FileOutputStream(propsFile), "UTF-8")
        println "Copied ${copyCount} files from ${fileCount} (${Math.ceil(copySize / 1024 / 1024)}MB)"
    } else {
        println "Source does not exist (${source})"
    }
}

copy()