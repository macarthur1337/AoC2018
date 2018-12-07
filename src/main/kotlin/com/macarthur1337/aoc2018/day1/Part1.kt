package com.macarthur1337.aoc2018.day1

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 02-12-2018
 */

fun main(args: Array<String>) {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input1").inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }
    val frequency = lineList.sum()

    println("frequency: $frequency")
}
