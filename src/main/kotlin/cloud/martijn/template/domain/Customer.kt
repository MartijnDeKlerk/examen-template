package cloud.martijn.template.domain

import javax.persistence.*


@Entity
@Table(name = "customers")
class Customer() : BaseEntity() {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private val user: User? = null

    var customerCode: String? = null

    var contactPerson: String? = null

    // Address
    var street: String? = null

    var houseNumber: Int? = null

    var postalCode: String? = null

    var city: String? = null
    // end address


}