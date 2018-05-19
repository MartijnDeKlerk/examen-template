package cloud.bram.template.ui.view

import cloud.bram.template.security.SecurityUtils
import cloud.bram.template.ui.TemplateNavigator
import cloud.bram.template.ui.view.dashboard.DashboardView
import cloud.bram.template.ui.view.dashboard.SettingView
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewDisplay
import com.vaadin.spring.annotation.SpringViewDisplay
import com.vaadin.ui.Panel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.vaadin.teemusa.sidemenu.SideMenu
import java.net.URI
import javax.annotation.PostConstruct

@SpringViewDisplay
class TemplateUILayout : Panel(), ViewDisplay {

    @set:Autowired
    lateinit var navigator: TemplateNavigator
    @set:Value("\${spring.application.name}")
    lateinit var appName: String

    val sideMenu = SideMenu()

    @PostConstruct
    fun init() {
        sideMenu.setMenuCaption(appName)
        sideMenu.setUserName(SecurityUtils.getEmail())
        sideMenu.addUserMenuItem("Logout", VaadinIcons.SIGN_OUT, logout)

        sideMenu.addMenuItem("Dashboard", VaadinIcons.DASHBOARD, SideMenu.MenuClickHandler {
            navigator.navigateTo(DashboardView::class)
        })
        sideMenu.addMenuItem("Settings", VaadinIcons.COG, SideMenu.MenuClickHandler {
            navigator.navigateTo(SettingView::class)
        })

        content = sideMenu
        setSizeFull()
    }

    /**
     * Show the contents of a view.
     */
    override fun showView(view: View?) {
        sideMenu.setContent(view?.viewComponent)
    }

    private val logout = SideMenu.MenuClickHandler {
        ui.access {
            session.session.invalidate()
            ui.page.location = URI("/sign-out")
        }
    }
}