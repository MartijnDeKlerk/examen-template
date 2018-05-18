package cloud.bram.template.security

import cloud.bram.template.domain.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    private val publicUrls = arrayOf(
            "/login",
            "/css/**",
            "/js/**",
            "/app",
            "/VAADIN/**",
            "/vaadinServlet/**"
    )

    @Autowired
    @Qualifier("user-details-service")
    lateinit var userDetailsService: UserDetailsService

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers(*publicUrls).permitAll()
                .anyRequest().hasAnyAuthority(*Role.getAllRoles())

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successForwardUrl("/app")
                .failureUrl("/login?failed")
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
