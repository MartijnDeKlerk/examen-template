package cloud.martijn.template.domain

import javax.persistence.*


@Entity
@Table(name = "users")
class User() : BaseEntity() {

    @Column(nullable = false, unique = true)
    var email: String? = null

    var name: String? = null

    var password: String? = null

    @Column(nullable = false)
    var authority: String? = null

    constructor(email: String, name: String? = "", password: String, role: String) : this() {
        this.email = email
        this.name = name
        this.password = password
        this.authority = role
    }

    @OneToOne(cascade = [CascadeType.ALL], mappedBy = "user")
    private val customer: Customer? = null
}