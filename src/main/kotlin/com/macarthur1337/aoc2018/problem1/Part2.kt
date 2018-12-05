package com.macarthur1337.aoc2018.problem1

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */

fun main(args: Array<String>) {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input1").inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }

    val result: Int
    val frequencies = mutableListOf(0)
    var canidates = lineList.asSequence().iterator()

    while (true) {
        if (canidates.hasNext()) {
            val current = canidates.next() + frequencies.last()
            if (frequencies.contains(current)) {
                result = current
                break
            } else frequencies.add(current)
        } else {

            canidates = lineList.asSequence().iterator()
        }
    }

    println("frequency: $result")

}