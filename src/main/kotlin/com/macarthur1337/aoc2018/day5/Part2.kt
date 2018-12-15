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

    val line = lineList[0]
    val polymers = mutableMapOf<Char, Int>()
    line.forEach {
        if (!polymers.containsKey(it.toLowerCase())) {
            val current = line.replace(it.toUpperCase().toString(), "")
                    .replace(it.toLowerCase().toString(), "")
            polymers[it] = reduce(current)
        }
    }
    println(polymers.values.min())

}

fun reduce(poly: String): Int {
    val polymer = mutableListOf<Char>()
    val line = poly.asSequence().iterator()
    polymer.add(line.next())

    loop@ while (line.hasNext()) {
        val current = line.next()
        when {
            polymer.size > 0 && reducePolymer(current, polymer.last()) -> {
                //if the current and previous reduce, remove it from the list
                polymer.removeAt(polymer.size - 1)
                continue@loop
            }
            else -> polymer.add(current)
        }
    }
    return polymer.size
}

