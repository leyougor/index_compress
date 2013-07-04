package cc.csa.test

object StemmerTest extends App{
    
    val b = "hello"
    
    def cons(i: Int): Boolean =
        // cons(i) 为真 <=> b[i] 是一个辅音
        {
            var ch = b(i)

            // magic!
            var vowels = "aeiou"

            // multi return. yuck
            if (vowels.contains(ch))
                return false
                
            //y开头，为辅；否则看i-1位，如果i-1位为辅，y为元，反之亦然
            if (ch == 'y') {
                if (i == 0) {
                    return true
                } else {
                    // loop it!
                    return !cons(i - 1)
                }
            }

            return true
        }
    
    def calcM(s: String): Int =
        {
            var l = s.length
            var count = 0
            var currentConst = false

            for (c <- 0 to l - 1) {
                if (cons(c)) {
                    if (!currentConst && c != 0) {
                        count += 1
                    }
                    currentConst = true
                } else {
                    currentConst = false
                }
            }

            return count
        }
    
//    println(calcM("he"))
    val a = """,.:;!?"'()[]@#$%^&*_+-=|<>\/~{}"""
    println(a.substring(0,a.length()))

}