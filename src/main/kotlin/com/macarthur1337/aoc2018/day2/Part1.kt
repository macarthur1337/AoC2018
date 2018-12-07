package com.macarthur1337.aoc2018.day2

import org.springframework.util.ResourceUtils

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
fun main(args: Array<String>) {
    val lineList = mutableListOf<String>()
    ResourceUtils.getFile("classpath:input2")
        .inputStream()
        .bufferedReader()
        .useLines { lines -> lines.forEach { lineList.add(it) } }

    var twos = 0
    var threes = 0
    val list = lineList.asSequence().iterator()

    while (list.hasNext()) {
        val counts = list.next().asSequence().groupingBy { it }.eachCount().filter { it.value > 1 }
        println(counts)
        if (counts.values.contains(2)) {
            twos++
        }
        if (counts.values.contains(3)) {
            threes++
        }
    }

    println("twos: $twos, threes: $threes")
    println("check digit: ${twos * threes}")

}