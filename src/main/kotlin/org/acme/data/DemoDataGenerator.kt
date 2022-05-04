package org.acme.data

import io.quarkus.runtime.StartupEvent
import org.acme.domain.Student
import org.acme.domain.Timeslot
import org.acme.persistence.StudentRepository
import org.acme.persistence.TimeslotRepository
import java.time.DayOfWeek
import java.time.LocalTime
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
class DemoDataGenerator {
    @Inject
    private lateinit var timeslotRepository: TimeslotRepository
    @Inject
    private lateinit var studentRepository: StudentRepository

    private val timeslotData: List<List<Int>> = listOf(
        listOf(0, 14,  0, 14, 30),
        listOf(0, 14, 30, 15,  0),
        listOf(0, 15,  0, 15, 30),
        listOf(0, 15, 30, 16,  0),
        listOf(0, 16,  0, 16, 30),
        listOf(0, 16, 30, 17,  0),

        listOf(1, 14,  0, 14, 30),
        listOf(1, 14, 30, 15,  0),
        listOf(1, 15,  0, 15, 30),
        listOf(1, 15, 30, 16,  0),
        listOf(1, 16,  0, 16, 30),
        listOf(1, 16, 30, 17,  0),
    )

    private val studentsData: List<List<Any>> = listOf(
        listOf("Kiss Péter", 0, 11),
        listOf("Nagy Zsolt", 7, 11),
        listOf("Kerek Zsófia", 0, 5),
        listOf("Szép Áron", 2, 12),
        listOf("Kövér László", 0, 12),
        listOf("Takács Gábor", 5, 11),
        listOf("Jászai Mari", 6, 12),
        listOf("Szinetár Dóra", 3, 10),
        listOf("Demjén Rózsi", 0, 12),
        listOf("Magyar Ágnes", 0, 12),
        listOf("Major Anna", 0, 12),
        listOf("Hegedűs Dániel", 0, 12),
    )

    @Transactional
    fun generateDemoData(@Observes startupEvent: StartupEvent) {
//region old code
//        timeslotRepository.persist(mutableListOf(
//            Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14,30)),
//            Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15,0)),
//            Timeslot(DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15,30)),
//            Timeslot(DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16,0)),
//            Timeslot(DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16,30)),
//            Timeslot(DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17,0)),
//
//            Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(14,30)),
//            Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15,0)),
//            Timeslot(DayOfWeek.TUESDAY, LocalTime.of(15, 0), LocalTime.of(15,30)),
//            Timeslot(DayOfWeek.TUESDAY, LocalTime.of(15, 30), LocalTime.of(16,0)),
//            Timeslot(DayOfWeek.TUESDAY, LocalTime.of(16, 0), LocalTime.of(16,30)),
//            Timeslot(DayOfWeek.TUESDAY, LocalTime.of(16, 30), LocalTime.of(17,0)),
//        ))
//
//        studentRepository.persist(mutableListOf(
//            Student("Kiss Péter", timeslotRepository.listAll().subList(0, 11).toSet()),
//            Student("Nagy Zsolt", timeslotRepository.listAll().subList(1, 11)),
//            Student("Kerek Zsófia", timeslotRepository.listAll().subList(0, 5)),
//            Student("Szép Áron", timeslotRepository.listAll().subList(2, 12)),
//            Student("Kövér László", timeslotRepository.listAll().subList(0, 12)),
//            Student("Takács Gábor", timeslotRepository.listAll().subList(5, 11)),
//            Student("Jászai Mari", timeslotRepository.listAll().subList(6, 12)),
//            Student("Szinetár Dóra", timeslotRepository.listAll().subList(3, 10)),
//            Student("Demjén Rózsi", timeslotRepository.listAll().subList(0, 12)),
//            Student("Magyar Ágnes", timeslotRepository.listAll().subList(0, 12)),
//            Student("Major Anna", timeslotRepository.listAll().subList(0, 12)),
//            Student("Hegedűs Dániel", timeslotRepository.listAll().subList(0, 12)),
//        ))
//endregion
        addAllTimeslots()
        addAllStudents()
    }

    private fun addAllTimeslots() {
        val timeslots: List<Timeslot> = timeslotData.map { list ->
            Timeslot(
                (DayOfWeek.MONDAY + list[0].toLong()) as DayOfWeek,
                LocalTime.of(list[1], list[2]),
                LocalTime.of(list[3],list[4]),)
        }
        timeslotRepository.persist(timeslots)
    }

    private fun addAllStudents() {
        val students: List<Student> = studentsData.map { list ->
            Student(
                list[0] as String,
                timeslotRepository.listAll().subList(list[1] as Int, list[2] as Int).toMutableList(),
//                (list[1] as Int .. list[2] as Int).toList(),
                )
        }
        studentRepository.persist(students)
    }
}