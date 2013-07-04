package cc.csa.util

import scala.collection.mutable.HashMap
import scala.io.Source
import java.io.FileWriter
import java.io.PrintWriter
import java.io.File

object FileUtil {

    /**
     * @param fileName
     * @return the size(KB) of file
     */
    def fileSize(fileName: String) = new File(fileName).length() / 1024

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

    def writeMapToFile(fileName: String, map: HashMap[String, (Int, Int)]) {
        var first = true
        for (li <- map) {
            var line = ""
            if (li._2._2 == 1)
                line = li._1 + ":" + li._2._1
            else
                line = li._1 + ":" + li._2._1 + "," + li._2._2
            if (first) {
                writeToFile(fileName, "")
                first = false
            }
            appendToFile(fileName, line)
        }

    }

}