package cc.csa.util

import scala.collection.mutable.HashMap
import scala.io.Source
import java.io.File

object CodewordUtil {
    def main(args: Array[String]) {
        //        val infile = "/home/lau/whitman-leaves.txt"
        val infile = "/home/lau/hello/english.100MB"
        codeword(infile)

    }

    def codeword(infile: String) {
        var startTime = System.currentTimeMillis()

        val codewordMap = new HashMap[String, Pair[Int, Int]]

        val stemMap = new HashMap[String, Int] // map[stem,count]
        val wordMap = new HashMap[String, HashMap[String, Int]] // map[stem,[word,count]]

        val index = if (infile.endsWith(".txt")) infile.lastIndexOf(".") else infile.length()
        val outfile = infile.substring(0, index) + "_cw.txt"
        val source = Source.fromFile(infile)("latin1")
        val stemmer = new Stemmer()

        println("h1:" + (System.currentTimeMillis() - startTime) / 1000 + "s")
        for (li <- source.getLines()) {
            val l = li.trim()
            for (it <- l.split("\\s+") if it != "") {
                val stem = stemmer.toStemmer(it)
                if (stem != "") {
                    val map = wordMap.get(stem).getOrElse(new HashMap[String, Int])
                    map.put(it, map.get(it).getOrElse(0) + 1)
                    wordMap.put(stem, map)
                    stemMap.put(stem, stemMap.get(stem).getOrElse(0) + 1)
                }
            }
        }

        println("h2:" + (System.currentTimeMillis() - startTime) / 1000 + "s")
        val stemList = stemMap.toList.sortBy(x => (x._2, x._1)).reverse
        //        val stemList = stemMap.toList.sortWith((x, y) => x._2 < y._2)
        println("h3:" + (System.currentTimeMillis() - startTime) / 1000 + "s")

        for (i <- 0 to stemList.length - 1) {
            stemMap.put(stemList(i)._1, i + 1)
        }
//        indexOf 效率极低
        //        for (x <- stemList) {
        //            stemMap.put(x._1, stemList.indexOf(x) + 1) // 编码为位序+1
        //        }

        println("h4:" + (System.currentTimeMillis() - startTime) / 1000 + "s")

        for (it <- wordMap.toList) {
            val list = it._2.toList.sortBy(x => (x._2, x._1)).reverse
            val map = new HashMap[String, Int]
            
            for (i <- 0 to list.length - 1) {
                val first = stemMap.get(it._1).getOrElse(0)
                val second = i + 1
                map.put(list(i)._1, second) // 编码为位序+1
                codewordMap.put(list(i)._1, (first, second))
            }

            //            for (x <- list) {
            //                val first = stemMap.get(it._1).getOrElse(0)
            //                val second = list.indexOf(x) + 1
            //                map.put(x._1, second) // 编码为位序+1
            //                codewordMap.put(x._1, (first, second))
            //            }
            wordMap.put(it._1, map)
        }

        //        println(stemMap)
        //        println(wordMap)
        //        println(codewordMap)

        println("h5:" + (System.currentTimeMillis() - startTime) / 1000 + "s")
        FileUtil.writeMapToFile(outfile, codewordMap)
        println("costing time: " + (System.currentTimeMillis() - startTime) / 1000 + "s")
        println("infile size: " + FileUtil.fileSize(infile) + "KB, outfile size: " + FileUtil.fileSize(outfile) + "KB")
        println("finish!")

    }

}