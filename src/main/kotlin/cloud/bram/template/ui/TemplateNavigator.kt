package cloud.bram.template.ui

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

    fun getViewName(view: Class<out View>): String? {
        val annotation: SpringView = view.getAnnotation(SpringView::class.java)
        return Conventions.deriveMappingForView(view, annotation)
    }

    /**
     * Navigate to a specific view.
     */
    fun navigateTo(view: Class<out View>) = navigateTo(getViewName(view))

    /**
     * Navigate to a kotlin class view.
     */
    fun navigateTo(kotlinClass: KClass<out View>) {
        navigateTo(kotlinClass.java)
    }
}