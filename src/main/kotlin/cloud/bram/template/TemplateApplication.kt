package cloud.bram.template

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TemplateApplication {

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
}

/**
 * Start the Spring Boot application.
 */
fun main(args: Array<String>) {
    runApplication<TemplateApplication>()
}
