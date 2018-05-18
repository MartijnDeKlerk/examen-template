package cloud.bram.template.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "users")
class User() : BaseEntity() {

    var email: String? = null

    var password: String? = null

    var role: String? = null

    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }

    constructor(email: String, password: String, role: String) : this(email, password) {
        this.role = role
    }
}