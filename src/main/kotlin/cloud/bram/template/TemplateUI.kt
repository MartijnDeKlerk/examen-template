package cloud.bram.template

import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Label
import com.vaadin.ui.UI

@SpringUI
class TemplateUI : UI() {

    override fun init(vaadinRequest: VaadinRequest) {
        content = Label("Hello, World!")
    }
}
