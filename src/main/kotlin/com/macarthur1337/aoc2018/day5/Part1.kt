package com.macarthur1337.aoc2018.day5

import org.springframework.util.ResourceUtils

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
fun main(args: Array<String>) {
    val lineList = mutableListOf<String>()
    ResourceUtils.getFile("classpath:input5")
        .inputStream()
        .bufferedReader()
        .useLines { lines -> lines.forEach { lineList.add(it) } }

    val line = lineList[0].asSequence().iterator()
    val polymers = mutableListOf<Char>()
    polymers.add(line.next())

    loop@ while (line.hasNext()) {
        val current = line.next()
        when {
            polymers.size > 0 && reducePolymer(current, polymers.last()) -> {
                //if the current and previous reduce, remove it from the list
                polymers.removeAt(polymers.size - 1)
                continue@loop
            }
            else -> polymers.add(current)
        }
    }

    println(polymers.size)
}

fun reducePolymer(first: Char, second: Char): Boolean {
    if (first == second) return false
    if (first.toUpperCase() == second) return true
    if (first.toLowerCase() == second) return true
    return false
}