package cloud.bram.template.security

import cloud.bram.template.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * The user details implementation.
 *
 * @param user the user for this principal
 */
class UserDetailsImpl(val user: User) : UserDetails {

    override fun getUsername(): String? = user.email

    override fun getPassword(): String? = user.password

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        SimpleGrantedAuthority(user.role?.name)
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return null
    }
}