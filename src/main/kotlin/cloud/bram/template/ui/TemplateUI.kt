package cloud.bram.template.ui

import cloud.bram.template.domain.Role
import cloud.bram.template.security.SecurityUtils
import com.vaadin.annotations.Theme
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.server.ErrorHandler
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import java.net.URI

@SpringUI(path = "/app")
@Theme("template")
@PushStateNavigation
@Secured(Role.NORMAL, Role.ADMIN)
class TemplateUI : UI() {

    @set:Autowired
    lateinit var templateNavigator: TemplateNavigator

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TemplateUI::class.java)
    }

    override fun init(request: VaadinRequest) {
        content = VerticalLayout(
                Label("Hello, World!"),
                Label(SecurityUtils.getEmail() ?: "Guest"),
                Button("Click Me Daddy", Button.ClickListener {
                    Notification.show("I CLICKED MY DADDY", Notification.Type.TRAY_NOTIFICATION)
                }),
                Button("Sign Out", Button.ClickListener {
                    access {
                        session.session.invalidate()
                        page.location = URI("/sign-out")
                    }
                })
        )

        errorHandler = ErrorHandler { event ->
            val throwable = event.throwable
            Notification.show(throwable.message, Notification.Type.ERROR_MESSAGE)
            logger.error(throwable.message, throwable)
        }
    }
}
