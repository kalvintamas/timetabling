package org.acme.persistence

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.acme.domain.Timeslot
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TimeslotRepository : PanacheRepository<Timeslot> {

}