package cloud.bram.template.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User() : BaseEntity() {
    var email: String? = null
    var password: String? = null
    var role: Role? = null

    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }
}