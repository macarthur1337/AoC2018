package com.macarthur1337.aoc2018.day5

import org.springframework.util.ResourceUtils

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
fun main(args: Array<String>) {
    val lineList = mutableListOf<String>()
    ResourceUtils.getFile("classpath:input")
        .inputStream()
        .bufferedReader()
        .useLines { lines -> lines.forEach { lineList.add(it) } }

    val line = lineList[0].asSequence().iterator()
    val initial = mutableListOf<Char>()

    while(line.hasNext()){
        val current =  line.next()
        if(line.hasNext()){
            val next = line.next()
            if (current.toLowerCase() == next.toLowerCase() &&
                ((current.isLowerCase() && next.isUpperCase())
                        || (current.isUpperCase() && next.isLowerCase()))) {
               continue
            }
            else{
                var previous = ' '
                if(initial.isNotEmpty()) { previous = initial.last() }
                if (current.toLowerCase() == previous.toLowerCase() &&
                    ((current.isLowerCase() && previous.isUpperCase())
                            || (current.isUpperCase() && previous.isLowerCase()))) {
                    initial.removeAt(initial.size-1)
                    initial.add(next)
                }
                else {
                    initial.add(current)
                    initial.add(next)

                }
            }
        }
    }
    val result = reduce(initial.joinToString(prefix = "", postfix = "", separator = ""))
    println(result.length)

    println("dabCBAcaDA")
    println(result)
    require(result == "dabCBAcaDA")

   // val result = reduce(initial.joinToString(prefix = "", postfix = "", separator = ""))
   /* val result = reduce(lineList[0])
    println(result)
    println(result.length)*/
}

//recursive reduction function
fun reduce(polymer: String): String {
    println(polymer.length)
    var reduced = polymer
    var index = 0
        while (index + 1 < polymer.length) {
            var current = polymer[index]
            var next = polymer[index+1]
            if (current.toLowerCase() == next.toLowerCase() &&
                ((current.isLowerCase() && next.isUpperCase())
                        || (current.isUpperCase() && next.isLowerCase()))) {
                 return reduce(polymer.removeRange(index, index+1))
            }
                index++
        }

    return reduced
}