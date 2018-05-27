package cloud.martijn.template

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class TemplateApplication : SpringBootServletInitializer() {

    /**
     * All URL's which are statically used in this application.
     */
    companion object {
        /* Authentication URL's */
        const val LOGIN_URL = "/sign-in"
        const val LOGIN_FAILURE_URL = "/sign-in?failed"
        const val LOGOUT_URL = "/sign-out"

        /* Application URL's */
        const val APP_URL = "/app"
        const val ERROR_URL = "/error"
    }

    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(this.javaClass)
    }
}

/**
 * Start the Spring Boot application.
 */
fun main(args: Array<String>) {
    runApplication<TemplateApplication>()
}
