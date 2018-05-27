package cloud.martijn.template.controller

import cloud.martijn.template.TemplateApplication
import cloud.martijn.template.security.SecurityUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController : ErrorController {


    @Value("\${spring.application.name}")
    lateinit var applicationName: String

    /**
     * When logged in, redirect to the Vaadin Servlet.
     */
    @RequestMapping(path = ["/"])
    fun redirectToApp() = "redirect:app"

    override fun getErrorPath(): String = TemplateApplication.APP_URL

    @RequestMapping(path = [TemplateApplication.LOGIN_URL])
    fun displayLoginView(model: Model): String {
        if (SecurityUtils.getPrincipal() != null) {
            return "redirect:app"
        }
        model.addAttribute("appName", applicationName)
        model.addAttribute("appDescription", "Description here")
        return "sign-in"
    }
}
