package cloud.bram.template.ui.view.dashboard

import com.github.appreciated.material.MaterialTheme
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import javax.annotation.PostConstruct

@SpringView(name = "settings")
class SettingView : VerticalLayout(), View {

    @PostConstruct
    fun init() {
        val label = Label("Setting View")
        label.addStyleNames(MaterialTheme.LABEL_H1, MaterialTheme.LABEL_COLORED)
        val button = Button("Notify me!")
        button.addStyleNames(MaterialTheme.BUTTON_PRIMARY)
        addComponents(label, button)
    }
}
