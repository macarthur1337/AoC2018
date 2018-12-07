package com.macarthur1337.aoc2018.problem2

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

    val list = lineList.asSequence().iterator()

    while (list.hasNext()) {
        val candidate = list.next()
        lineList.forEach {
            if (hammingDist(candidate, it) == 1) {
                val match = it
                //find only matching chars
                match.chars()
                    .mapToObj { ch -> ch.toChar().toString() }
                    .filter { candidate.contains(it) }
                    .forEach { print(it) }

                return //end
            }
        }
    }

}

fun hammingDist(str1: String, str2: String): Int {
    var i = 0
    var count = 0
    while (i < str1.length) {
        if (str1[i] != str2[i])
            count++
        i++
    }
    return count
}