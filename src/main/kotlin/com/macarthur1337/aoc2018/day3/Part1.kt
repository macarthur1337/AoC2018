package com.macarthur1337.aoc2018.day3

import org.springframework.util.ResourceUtils

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
data class Point(val x: Int, val y: Int)
data class Dimension(val width: Int, val height: Int)

fun main(args: Array<String>) {
    val lineList = mutableListOf<String>()
    ResourceUtils.getFile("classpath:input3")
            .inputStream()
            .bufferedReader()
            .useLines { lines -> lines.forEach { lineList.add(it) } }

    val size = 1000
    val array = Array(size) { IntArray(size) { 0 } }
    var claims = 0

    lineList.forEach {
        val claim = it.substringBefore(' ').substringAfter('#').toInt()
        val point = getPoint(it)
        val dimensions = getDimensions(it)

        (1..dimensions.height).forEach { i ->
            (1..dimensions.width).forEach { j ->
                val index = array[point.y + i][point.x + j]
                when(index){
                    0 -> array[point.y + i][point.x + j] = claim
                    -1 -> {} //already marked as overlapped
                    else -> { claims++
                        array[point.y + i][point.x + j] = -1 //mark as overlap
                    }
                }
            }
        }
    }
    println(claims)
}

fun getPoint(line: String): Point {
    val points = line.substringAfter("@ ").substringBefore(' ')
    val x = points.substringBefore(',').toInt()
    val y = points.substringAfter(',').substringBefore(':').toInt()
    return Point(x,y)
}

fun getDimensions(line: String): Dimension {
    val dimensions = line.substringAfterLast(' ')
    val width = dimensions.substringBefore('x').toInt()
    val height = dimensions.substringAfter('x').toInt()
    return Dimension(width,height)
}