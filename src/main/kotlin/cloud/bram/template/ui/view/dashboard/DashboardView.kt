package cloud.bram.template.ui.view.dashboard

import com.github.appreciated.material.MaterialTheme
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import javax.annotation.PostConstruct

@SpringView(name = "dashboard")
class DashboardView : VerticalLayout(), View {

    @PostConstruct
    fun init() {
        val label = Label("Dashboard View")
        label.addStyleNames(MaterialTheme.LABEL_H1)
        addComponent(label)
    }
}
