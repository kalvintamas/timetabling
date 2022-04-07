package org.acme.domain

import java.time.DayOfWeek
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Timeslot {
    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var dayOfWeek: DayOfWeek
    lateinit var startTime: LocalTime
    lateinit var endTime: LocalTime

    @ManyToMany
    var possibleStudents: MutableList<Student>? = null

    constructor(dayOfWeek: DayOfWeek, startTime: LocalTime, endTime: LocalTime,
                possibleStudents: MutableList<Student>?) {
        if (endTime <= startTime)
            throw IllegalArgumentException("End time is not after start time!")
        this.dayOfWeek = dayOfWeek
        this.startTime = startTime
        this.endTime = endTime
        this.possibleStudents = possibleStudents
    }

    constructor(dayOfWeek: DayOfWeek, startTime: LocalTime, endTime: LocalTime) {
        if (endTime <= startTime)
            throw IllegalArgumentException("End time is not after start time!")
        this.dayOfWeek = dayOfWeek
        this.startTime = startTime
        this.endTime = endTime
    }

    constructor()
}