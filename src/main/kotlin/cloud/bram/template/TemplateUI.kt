package cloud.bram.template

import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@SpringUI(path = "/app")
class TemplateUI : UI() {

    override fun init(request: VaadinRequest) {
        content = VerticalLayout(Label("Hello, World!"), Button("Click Me"))
    }
}
