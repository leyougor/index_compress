package cc.csa.test

object StemmerTest extends App{
    
    val b = "hello"
    
    def cons(i: Int): Boolean =
        // cons(i) Ϊ�� <=> b[i] ��һ������
        {
            var ch = b(i)

            // magic!
            var vowels = "aeiou"

            // multi return. yuck
            if (vowels.contains(ch))
                return false
                
            //y��ͷ��Ϊ��������i-1λ�����i-1λΪ����yΪԪ����֮��Ȼ
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