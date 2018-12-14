package com.macarthur1337.aoc2018.day3

import org.springframework.util.ResourceUtils

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
fun main(args: Array<String>) {
    val lineList = mutableListOf<String>()
    ResourceUtils.getFile("classpath:input3")
            .inputStream()
            .bufferedReader()
            .useLines { lines -> lines.forEach { lineList.add(it) } }

    val size = 1000
    val array = Array(size) { IntArray(size) { 0 } }
    val conflicts = BooleanArray(lineList.size + 1)


    lineList.forEach {
        val claim = getClaim(it)
        val point = getPoint(it)
        val dimensions = getDimensions(it)

        (1..dimensions.height).forEach { i ->
            (1..dimensions.width).forEach { j ->
                val index = array[point.y + i][point.x + j]

                when(index){
                    0 -> array[point.y + i][point.x + j] = claim
                    -1 -> { conflicts[claim] = true} //already marked as overlapped
                    else -> {
                        conflicts[claim] = true
                        conflicts[index] = true
                        array[point.y + i][point.x + j] = -1 //mark as overlap
                    }
                }
            }
        }
    }
    println(conflicts.indexOfLast { !it }) //last because the first is the 0 index of the array
    // which is unused
}

fun getClaim(line: String) : Int {
    return line.substringBefore(' ').substringAfter('#').toInt()
}