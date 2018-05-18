package cloud.bram.template.controller

import cloud.bram.template.security.SecurityUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController {

    @Value("\${spring.application.name}")
    private val appname: String? = null

    @RequestMapping(path = ["/sign-in"])
    fun displayLoginView(model: Model): String {
        if (SecurityUtils.getPrincipal() != null) {
            return "redirect:app"
        }
        model.addAttribute("appname", appname!!)
        model.addAttribute("appdescription", "Description here")
        return "login"
    }
}
