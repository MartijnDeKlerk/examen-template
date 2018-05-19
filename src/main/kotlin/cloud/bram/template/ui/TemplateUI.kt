package cloud.bram.template.ui

import cloud.bram.template.domain.Role
import cloud.bram.template.ui.view.TemplateUILayout
import cloud.bram.template.ui.view.dashboard.DashboardView
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Viewport
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.server.ErrorHandler
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Notification
import com.vaadin.ui.UI
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured

@SpringUI(path = "/app")
@Theme("template")
@PushStateNavigation
@Secured(Role.NORMAL, Role.ADMIN)
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
class TemplateUI : UI() {

    @set:Autowired
    lateinit var navigator: TemplateNavigator
    @set:Autowired
    lateinit var layout: TemplateUILayout

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TemplateUI::class.java)
    }

    /**
     * Initialize the UI.
     */
    override fun init(request: VaadinRequest) {
        logRequestInformation(request)

        navigator = navigator
        content = layout

        navigator.navigateTo(DashboardView::class)

        errorHandler = ErrorHandler { event ->
            val throwable = event.throwable
            Notification.show(throwable.message, Notification.Type.ERROR_MESSAGE)
            logger.error(throwable.message, throwable)
        }
    }

    /**
     * Log information for a request.
     */
    fun logRequestInformation(request: VaadinRequest) {
        logger.info("UI Initialization:")
        logger.info("| Secure: {}", if (request.isSecure) "Yes" else "No")
    }
}
