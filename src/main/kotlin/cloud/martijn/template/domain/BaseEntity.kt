package cloud.martijn.template.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.util.StringUtils
import java.util.*
import javax.persistence.*
import java.util.UUID
import javax.persistence.PostLoad
import javax.persistence.PrePersist



@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var uuid: String? = null

    @Version
    var version: Int = 0

    @CreationTimestamp
    var createdAt: Date? = null

    @UpdateTimestamp
    var updatedAt: Date? = null

    @PrePersist
    @PostLoad
    fun generateUuid() {
        if (StringUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString().toUpperCase()
        }
    }

    fun isNew() = id == 0L
}