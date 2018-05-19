package cloud.bram.template.security

import cloud.bram.template.domain.User
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Security utilities, for retrieving current user information.
 */
class SecurityUtils private constructor() {

    companion object {
        fun getPrincipal(): UserDetailsImpl? {
            val context = SecurityContextHolder.getContext()
            val auth = context?.authentication
            return when {
                auth?.principal is String -> null
                else -> auth?.principal as UserDetailsImpl?
            }
        }

        fun getUser(): User? = getPrincipal()?.user

        fun getEmail(): String? = getUser()?.email

        fun getRole(): String? = getUser()?.authority
    }
}