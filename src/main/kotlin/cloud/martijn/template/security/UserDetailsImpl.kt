package cloud.martijn.template.security

import cloud.martijn.template.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

/**
 * The user details implementation.
 *
 * @param user the user for this principal
 */
class UserDetailsImpl(val user: User) : UserDetails {

    override fun getUsername(): String? = user.email

    override fun getPassword(): String? = user.password

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return Collections.singleton(SimpleGrantedAuthority(user.authority))
    }
}