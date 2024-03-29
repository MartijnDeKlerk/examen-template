package cloud.martijn.template.domain.repository

import cloud.martijn.template.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    /**
     * Find a user by it's email.
     *
     * @param email the email for the user
     */
    fun findByEmail(email: String): User?
}
