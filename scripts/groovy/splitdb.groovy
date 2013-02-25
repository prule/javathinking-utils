/**
 * http://www.javathinking.com
 *
 * Used to extract a single database from a mysqldump --all-databases file.
 *  groovy splitdb [inputfile] [databasename]
 *
 * Dependencies:
 *  Groovy - http://groovy.codehaus.org/
 *  Java - http://java.com
 *
 * Sample use:
 * -- Export all of the databases
 *  mysqldump --all-databases -u root > alldb.sql
 * -- Extract one database from the dump
 *  groovy splitdb alldb.sql testdb
 * -- Produces the following output
 *  Input: /home/paul/alldb.sql
 *  Extracting database: testdb
 *  Found database  `DEVbudget`
 *  Found database  `DEVdogblogs`
 *  Found database  `DEVfilmsugg`
 *  Found database  `mysql`
 *  Found database  `util`
 *  Database testdb extracted to testdb.sql
 *
 * Implementation
 *  Reads [inputfile] until it finds the start of the specified database,
 *  and outputs from that point until the next database or end of file. 
 */

def infile = args[0]
def dbname = args[1]

println "Input: " + infile
println "Extracting database: " + dbname

File src = new File(infile)
File out = new File("${dbname}.sql")

/* find unique output file name */
def count = 1
while (out.exists()) {
    out = new File("${dbname}-${count}.sql")
    count++
}


def record = false
src.eachLine() { line ->
    if (line.indexOf("Current Database:") > -1) {
        record = line.indexOf("`${dbname}`") > -1
        println "Found database ${line.substring(line.indexOf(':') + 1)}"
    }
    if (record) {
        out << line + '\n'
    }
}

println "Database ${dbname} extracted to ${out}"