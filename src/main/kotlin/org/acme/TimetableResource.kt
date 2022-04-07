package org.acme

import org.acme.domain.TimeTable
import org.acme.persistence.StudentRepository
import org.acme.persistence.TimeslotRepository
import org.optaplanner.core.api.solver.SolverManager
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/timetable")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TimetableResource {
    @Inject
    lateinit var timeslotRepository: TimeslotRepository
    @Inject
    lateinit var studentRepository: StudentRepository

    @Inject
    lateinit var solverManager: SolverManager<TimeTable, Long>

    @GET
    @Transactional
    fun getTimeTable(): TimeTable {
        return TimeTable(timeslotRepository.listAll(), studentRepository.listAll())
    }

    @POST
    @Path("solve")
    fun solve() {
        solverManager.solveAndListen(1L,
            { getTimeTable() },
            ::save)
    }

    @Transactional
    fun save(timeTable: TimeTable) {
        for (student in timeTable.studentList) {
            val attachedStudent = studentRepository.findById(student.id!!)!!
            attachedStudent.timeslot = student.timeslot
        }
    }
}