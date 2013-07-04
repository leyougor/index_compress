package cc.hello.test

import scala.util.Random
import scala.collection.mutable.HashMap

object HelloScala {
    def main(args: Array[String]) {
        def max(a: Int, b: Int): Int = if (a > b) a else b

        def min(a: Int, b: Int): Int = if (a < b) a else b

        def gcd(a: Int, b: Int): Int = {
            if (b == 0) a
            else gcd(b, a % b)
        }

        val intArr = new Array[Int](10)

        for (i <- 0 until intArr.length) {
            intArr(i) = Random.nextInt(100)
        }
        
        val map = new HashMap[String,Int]
        map.put("one", 1)

        println(map.get("two").isEmpty)
    }
}