package org.acme.persistence

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.acme.domain.Student
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentRepository : PanacheRepository<Student> {

}