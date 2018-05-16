package cloud.bram.template.domain

import javax.persistence.Entity

@Entity
class Role() : BaseEntity() {

    var name: String? = null

    constructor(name: String) : this() {
        this.name = name
    }
}