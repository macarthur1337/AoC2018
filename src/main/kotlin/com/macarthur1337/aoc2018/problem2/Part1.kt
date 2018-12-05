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
        println(list.next().asSequence().groupingBy { it }.eachCount().filter { it.value > 1 })

    }

}