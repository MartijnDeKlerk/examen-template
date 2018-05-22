package cloud.bram.template.ui

import cloud.bram.template.TemplateApplication.Companion.APP_URL
import cloud.bram.template.domain.Authority
import cloud.bram.template.ui.view.TemplateLayout
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

@Theme("template")
@PushStateNavigation
@SpringUI(path = APP_URL)
@Secured(Authority.NORMAL, Authority.ADMIN)
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
class TemplateUI : UI() {

    @set:Autowired
    lateinit var navigator: TemplateNavigator
    @set:Autowired
    lateinit var layout: TemplateLayout

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

        navigator.navigateToDefaultView()

        errorHandler = ErrorHandler { event ->
            with(event.throwable) {
                Notification.show(message, Notification.Type.ERROR_MESSAGE)
                logger.error(message, this)
            }
        }
    }

    /**
     * Log information for a request.
     */
    private fun logRequestInformation(request: VaadinRequest) {
        logger.run {
            info("UI Initialization:")
            info("| Secure: {}", if (request.isSecure) "Yes" else "No")
            info("| Browser: {}", page.webBrowser.browserApplication)
        }
    }
}
