package org.acme.domain

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
class TimeTable {
//    @ValueRangeProvider(id = "timeslotRange")
    lateinit var timeslotList: List<Timeslot>
    @PlanningEntityCollectionProperty
    lateinit var studentList: List<Student>

    @PlanningScore
    var score: HardSoftScore? = null

    constructor(timeslotList: List<Timeslot>, studentList: List<Student>) {
        this.timeslotList = timeslotList
        this.studentList = studentList
    }

    constructor()
}