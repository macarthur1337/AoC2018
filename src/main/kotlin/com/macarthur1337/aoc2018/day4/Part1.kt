package com.macarthur1337.aoc2018.day4

import org.springframework.util.ResourceUtils
import java.time.LocalDateTime
import java.time.Month

/**
 * @author N. E. Absil
 * @since 05-12-2018
 */
data class Shift(val timeStamp: LocalDateTime = LocalDateTime.MIN, val descriptor: String = "")

data class SleepCycle(val startSleep: Int, val endSleep: Int)

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
                val awake = it.timeStamp.minute -1
                guards[guard] = guards[guard]?.plus(awake - asleep) ?: awake - asleep
                when (sleepCycles.containsKey(guard)) {
                    false -> sleepCycles[guard] = mutableListOf(SleepCycle(asleep, awake))
                    true -> sleepCycles[guard]!!.add(SleepCycle(asleep, awake))
                }
            }
        }
    }

    val mostSleepyGuard = guards.maxBy { it.value }?.key
    val minutes = IntArray(59)
    sleepCycles[mostSleepyGuard]!!.forEach {
        (it.startSleep..it.endSleep).forEach { i -> minutes[i]++ }
    }
    //find out when the guard was most sleepy
    val minute = minutes.withIndex().maxBy { it.value }?.index
    println(minute!! * mostSleepyGuard!!)
}

fun getDateTime(line: String): LocalDateTime {
    val year = line.substringAfter('[').substringBefore('-').toInt()
    val month = line.substringAfter('-').substringBefore('-').toInt()
    val day = line.substringAfterLast('-').substringBefore(' ').toInt()
    val hour = line.substringAfter(' ').substringBefore(':').toInt()
    val minute = line.substringAfter(':').substringBefore(']').toInt()
    return LocalDateTime.of(year, Month.of(month), day, hour, minute)
}