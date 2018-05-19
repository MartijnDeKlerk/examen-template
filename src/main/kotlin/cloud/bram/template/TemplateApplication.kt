package cloud.bram.template

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TemplateApplication {

    /**
     * All URL's which are statically used in this application.
     */
    companion object {
        const val LOGIN_URL = "/sign-in"
        const val LOGIN_FAILURE_URL = "/sign-in?failed"
        const val LOGOUT_URL = "/sign-out"
    }
}


fun main(args: Array<String>) {
    runApplication<TemplateApplication>()
}
