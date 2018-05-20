package cloud.bram.template.ui

import cloud.bram.template.domain.Authority
import cloud.bram.template.security.SecurityUtils
import cloud.bram.template.ui.view.dashboard.DashboardView
import cloud.bram.template.ui.view.dashboard.UserView
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.spring.internal.Conventions
import com.vaadin.spring.navigator.SpringNavigator
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@UIScope
@Component
class TemplateNavigator : SpringNavigator() {

    /**
     * Get the view name for a view class.
     */
    fun getViewName(view: Class<out View>): String? {
        val annotation: SpringView = view.getAnnotation(SpringView::class.java)
        return Conventions.deriveMappingForView(view, annotation)
    }

    /**
     * Navigate to a specific view.
     */
    fun navigateTo(view: Class<out View>) = navigateTo(getViewName(view))

    fun navigateTo(view: KClass<out View>) = navigateTo(view.java)

    /**
     * Navigate to the default view for the current role.
     */
    fun navigateToDefaultView() {
        if (state != null && !state.isEmpty()) {
            return
        }

        val role = SecurityUtils.getRole()
        when (role) {
            Authority.ADMIN -> navigateTo(UserView::class)
            Authority.NORMAL -> navigateTo(DashboardView::class)
            else -> navigateTo(DashboardView::class)
        }
    }
}