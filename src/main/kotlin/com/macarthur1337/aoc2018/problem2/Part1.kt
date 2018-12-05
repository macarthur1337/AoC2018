package com.macarthur1337.aoc2018.problem2

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
fun main(args: Array<String>) {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input1").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }

}