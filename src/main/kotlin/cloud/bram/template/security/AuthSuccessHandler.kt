package cloud.bram.template.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.ApplicationScope
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@ApplicationScope
class AuthSuccessHandler : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
            request: HttpServletRequest?,
            response: HttpServletResponse?,
            authentication: Authentication?) {
        response?.sendRedirect("/app")
    }
}