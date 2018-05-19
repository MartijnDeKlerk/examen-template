package cloud.bram.template.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "users")
class User() : BaseEntity() {

    @Column(nullable = false, unique = true)
    var email: String? = null

    var name: String? = null

    var password: String? = null

    @Column(nullable = false)
    var authority: String? = null

    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }

    constructor(email: String, password: String, role: String) : this(email, password) {
        this.authority = role
    }
}