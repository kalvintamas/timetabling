package org.acme

import org.acme.domain.Student
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider
import org.optaplanner.core.api.score.stream.Joiners

class TimeTableConstraintProvider : ConstraintProvider {
    override fun defineConstraints(constraintFactory: ConstraintFactory): Array<Constraint> {
        return arrayOf(
            timeslotConflict(constraintFactory)
        )
    }

    private fun timeslotConflict(constraintFactory: ConstraintFactory): Constraint {
        return constraintFactory.forEach(Student::class.java)
            .join(Student::class.java,
                    Joiners.equal(Student::timeslot))
            .penalize("student conflict", HardSoftScore.ONE_HARD)
    }
}