package org.acme.domain

import java.time.DayOfWeek
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.transaction.Transactional

@Entity
@Transactional
class Timeslot {
    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var dayOfWeek: DayOfWeek
    lateinit var startTime: LocalTime
    lateinit var endTime: LocalTime

    constructor(dayOfWeek: DayOfWeek, startTime: LocalTime, endTime: LocalTime) {
        if (endTime <= startTime)
            throw IllegalArgumentException("End time is not after start time!")
        this.dayOfWeek = dayOfWeek
        this.startTime = startTime
        this.endTime = endTime
    }

    constructor()
}