package com.macarthur1337.aoc2018.day4

import org.springframework.util.ResourceUtils

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
fun main(args: Array<String>) {
    val lineList = mutableListOf<String>()
    ResourceUtils.getFile("classpath:input4")
            .inputStream()
            .bufferedReader()
            .useLines { lines -> lines.forEach { lineList.add(it) } }

    val sleepCycles = mutableMapOf<Int, MutableList<SleepCycle>>()

    //create an ordered (by time) for all shifts
    val shifts = mutableListOf<Shift>()
    lineList.forEach { shifts.add(Shift(getDateTime(it), it.substringAfter("] "))) }
    shifts.sortBy { it.timeStamp }

    //find the guard that sleeps the most
    val guards = mutableMapOf<Int, Int>()
    var guard = 0
    var asleep = 0
    shifts.forEach {
        val current = it.descriptor.substringAfterLast(' ')
        when (current) {
            "shift" -> guard = it.descriptor.substringAfter('#').substringBefore(' ').toInt()
            "asleep" -> asleep = it.timeStamp.minute
            "up" -> {
                val awake = it.timeStamp.minute - 1
                guards[guard] = guards[guard]?.plus(awake - asleep) ?: awake - asleep
                when (sleepCycles.containsKey(guard)) {
                    false -> sleepCycles[guard] = mutableListOf(SleepCycle(asleep, awake))
                    true -> sleepCycles[guard]!!.add(SleepCycle(asleep, awake))
                }
            }
        }
    }

    val frequency = mutableMapOf<Int, IntArray>()
    sleepCycles.forEach {

        frequency[it.key] = IntArray(59)
        val minutes = frequency[it.key]
        it.value.forEach {
            for (i in it.startSleep..it.endSleep) {
                minutes!![i]++
            }
        }
    }

    //find guard with most frequent minute slept
    var max = 0
    var min = -1
    var guardId = 0
    frequency.forEach {
        val current = it.value
        current.forEachIndexed { index, element ->
            if (current[index] > max) {
                max = element
                min = index
                guardId = it.key
            }
        }
    }
    print(min * guardId)
}