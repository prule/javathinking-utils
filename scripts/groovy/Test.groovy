import org.apache.commons.io.FileUtils;

@Grab(group = 'commons-io', module = 'commons-io', version = '1.4')
def a() {
    FileUtils.copyFile(new File('/tmp/a'), new File('/tmp/b'))
}