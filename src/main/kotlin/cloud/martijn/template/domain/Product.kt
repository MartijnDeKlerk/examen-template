package cloud.martijn.template.domain

import javax.persistence.*

@Entity
@Table(name = "products")
class Product() : BaseEntity() {

    @Column(nullable = false, unique = true)
    var productCode: String? = null

    var productDescription: String? = null

    var productPrice: Double? = 0.00

    @ManyToMany(cascade = [CascadeType.ALL], mappedBy = "product")
    val product: List<Invoice>? = null

    constructor(productCode: String, productDescription: String, productPrice: Double) : this() {
        this.productCode = productCode
        this.productDescription = productDescription
        this.productPrice = productPrice
    }
}