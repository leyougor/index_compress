package cc.csa.test

import org.junit.Test
import java.io.FileWriter
import java.io.PrintWriter
import java.io.File
import scala.io.Source
import java.io.BufferedWriter

/**
 * @author lau
 *
 */
class FileTest {

    @Test
    def test() {
        println("hello, test")
    }

    @Test
    def writeArray() {
        val data = Array("Five", "strings", "in", "a", "file!")
        printToFile(new File("/tmp/ex.txt"))(p => {
            data.foreach(p.println)
        })
    }

    @Test
    def writeString() {
        val str = "You are my sunshine!"
        writeToFile("/tmp/ex.txt", str)
    }

    @Test
    def writeFile() {
        //        writeToFile("/tmp/ex2.txt", "Name   Weight(kg)\n")
        appendToFile("/tmp/ex2.txt", "Ron       70")
        appendToFile("/tmp/ex2.txt", "May       60")
    }

    /**
     * @param param
     * @param f
     * @return (B)x
     */
    def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B =
        try { f(param) } finally { param.close() }

    def writeToFile(fileName: String, data: String) =
        using(new FileWriter(fileName)) {
            fileWriter => fileWriter.write(data)
        }

    def appendToFile(fileName: String, textData: String) =
        using(new FileWriter(fileName, true)) {
            fileWriter =>
                using(new PrintWriter(fileWriter)) {
                    printWriter => printWriter.println(textData)
                }
        }

    def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
        val p = new java.io.PrintWriter(f)
        try { op(p) } finally { p.close() }
    }

    def writeToFileDemo(p: String, s: String) {
        val pw = new java.io.PrintWriter(new File(p))
        try {
            pw.write(s)
        } finally {
            pw.close()
        }
    }

    def fileWriter(args: Array[String]): Unit = {
        val file = Source.fromFile("/scala_fileio/file-to-read.txt")
        val outputFile = new File("/scala_fileio/output.txt")
        val writer = new BufferedWriter(new FileWriter(outputFile))
        // use curly brackets {} to tell Scala that it's now a multi-line statement!
        file.getLines().foreach { line =>
            println(line)
            writer.write("***" + line + "***")
            writer.newLine()
        }
        writer.flush()
        writer.close()
    }

}