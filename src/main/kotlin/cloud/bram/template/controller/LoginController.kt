package cloud.bram.template.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController {

    @Value("\${spring.application.name}")
    private val appname: String? = null

    @RequestMapping(path = arrayOf("/login"))
    fun displayLoginView(model: Model): String {
        model.addAttribute("appname", appname!!)
        model.addAttribute("appdescription", "Description here")
        return "login"
    }
}
