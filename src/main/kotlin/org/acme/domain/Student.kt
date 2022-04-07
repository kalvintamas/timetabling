package org.acme.domain

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.PlanningVariable
import javax.persistence.*
import javax.transaction.Transactional

@Transactional
@PlanningEntity
@Entity
class Student {
    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var name: String

    @ManyToMany
    @JoinTable(name = "HaveTimeFor",
        joinColumns = [JoinColumn(name = "student_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "timeslot_id", referencedColumnName = "id")])
    //@ValueRangeProvider(id = "timeslotRange")
    var possibleTimeslots: MutableList<Timeslot>? = null

    @PlanningVariable(valueRangeProviderRefs = ["timeslotRange"])
    @OneToOne
    var timeslot: Timeslot? = null

    constructor(name: String, timeslotRange: MutableList<Timeslot>) {
        this.name = name
        this.possibleTimeslots = timeslotRange
    }

    constructor(name: String) {
        this.name = name
    }

    constructor()
}