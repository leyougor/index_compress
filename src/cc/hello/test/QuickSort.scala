package cc.hello.test

import scala.util.Random

object QuickSort extends App {

    val intArr = new Array[Int](20)

    for (i <- 0 until intArr.length) {
        intArr(i) = Random.nextInt(10)
        if (i == intArr.length - 1)
            println(intArr(i) + " ")
        else
            print(intArr(i) + " ")
    }

    def quickSort(intArr: Array[Int]) {
        def swap(i: Int, j: Int) {
            if (i != j) {
                val tmp = intArr(i)
                intArr(i) = intArr(j)
                intArr(j) = tmp
            }
        }
        def partition(begin: Int, end: Int): Int = {
            val pivot = intArr(begin)
            var left = begin
            var right = end

            while (left < right) {
                while (left < right && intArr(right) >= pivot) right -= 1;
                swap(left, right)
                while (left < right && intArr(left) <= pivot) left += 1;
                swap(left, right)
            }
            intArr(left) = pivot
            left
        }

        def sort(begin: Int, end: Int) {
            if (begin < end) {
                var pivot = partition(begin, end);
                sort(begin, pivot - 1)
                sort(pivot + 1, end)
            }
        }

        sort(0, intArr.length - 1)
        println("Sort finished!");
        for (i <- 0 until intArr.length) {
            print(intArr(i) + " ")
        }
    }

    def quickSort2(intArr: Array[Int]) {
        def swap(i: Int, j: Int) {
            if (i != j) {
                val tmp = intArr(i)
                intArr(i) = intArr(j)
                intArr(j) = tmp
            }
        }

        def sort(begin: Int, end: Int) {
            val pivot = intArr((begin + end) / 2)
            var left = begin
            var right = end
            while (left <= right) {
                while (intArr(left) < pivot) left += 1
                while (intArr(right) > pivot) right -= 1
                if (left <= right) {
                //	left==right继续移动，为递归做准备
                    swap(left, right)
                    left += 1
                    right -= 1
                }
            }

            if (begin < right) sort(begin, right)
            if (end > left) sort(left, end)
        }
        sort(0, intArr.length - 1)
        println("Sort2 finished!");
        for (i <- 0 until intArr.length) {
            print(intArr(i) + " ")
        }
    }
    //    quickSort(intArr)
    quickSort2(intArr)
}