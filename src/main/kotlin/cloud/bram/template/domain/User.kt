package cloud.bram.template.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var email: String? = null
    var password: String? = null
    var role: Role? = null

    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }
}