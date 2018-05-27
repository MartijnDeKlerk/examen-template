package cloud.martijn.template.domain

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "invoices")
class Invoice() : BaseEntity() {

    @Column(nullable = false, unique = true)
    var invoiceNumber: Int? = null

    @Column(nullable = false)
    var invoiceDate: Date? = null

//    @OneToOne(cascade = [CascadeType.ALL])
    @Column(nullable = false)
    var customerCode: String? = null

    @Column(nullable = false)
    var productCode: String? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "invoices_products", joinColumns = [JoinColumn(name = "invoice_id")],
            inverseJoinColumns = [JoinColumn(name = "product_id")])
    val product: List<Product>? = null


    constructor(invoiceNumber: Int, invoiceDate: Date, customerCode: String, productCode: String) : this() {
        this.invoiceNumber = invoiceNumber
        this.invoiceDate = invoiceDate
        this.customerCode = customerCode
        this.productCode = productCode
    }
}