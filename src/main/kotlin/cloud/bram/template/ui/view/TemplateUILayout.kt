package cloud.bram.template.ui.view

import cloud.bram.template.security.SecurityUtils
import cloud.bram.template.ui.TemplateNavigator
import cloud.bram.template.ui.view.dashboard.DashboardView
import cloud.bram.template.ui.view.dashboard.SettingView
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.Resource
import com.vaadin.spring.annotation.SpringViewDisplay
import com.vaadin.ui.Panel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.vaadin.teemusa.sidemenu.SideMenu
import java.net.URI
import javax.annotation.PostConstruct
import kotlin.reflect.KClass

@SpringViewDisplay
class TemplateUILayout : Panel(), ViewDisplay {

    @set:Autowired
    lateinit var navigator: TemplateNavigator
    @set:Value("\${spring.application.name}")
    lateinit var appName: String

    val sideMenu = SideMenu()

    /**
     * Add all the necessary menu items, and set the user menu caption.
     */
    @PostConstruct
    fun init() {
        sideMenu.setMenuCaption(appName)
        sideMenu.setUserName(SecurityUtils.getEmail())
        sideMenu.addUserMenuItem("Logout", VaadinIcons.SIGN_OUT, logout)

        addMenuItem("Dashboard", VaadinIcons.DASHBOARD, DashboardView::class)
        addMenuItem("Settings", VaadinIcons.COG, SettingView::class)

        content = sideMenu
        setSizeFull()
    }

    /**
     * Add a menu item which navigates to a specific view.
     */
    fun addMenuItem(caption: String, icon: Resource, view: KClass<out View>) {
        sideMenu.addMenuItem(caption, icon, SideMenu.MenuClickHandler {
            navigator.navigateTo(view)
        })
    }

    /**
     * Show the contents of a view.
     */
    override fun showView(view: View?) {
        ui.page.setTitle(appName)
        sideMenu.setContent(view?.viewComponent)
    }

    /**
     * Log out of the application.
     */
    private val logout = SideMenu.MenuClickHandler {
        ui.access {
            session.session.invalidate()
            ui.page.location = URI("/sign-out")
        }
    }
}