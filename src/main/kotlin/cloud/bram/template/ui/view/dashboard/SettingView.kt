package cloud.bram.template.ui.view.dashboard

import cloud.bram.template.domain.User
import cloud.bram.template.domain.repository.UserRepository
import com.github.appreciated.material.MaterialTheme
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.Autowired
import javax.annotation.PostConstruct

@SpringView(name = "settings")
class SettingView : VerticalLayout(), View {

    @Autowired
    lateinit var userRepository: UserRepository

    @PostConstruct
    fun init() {
        val label = Label("Setting View")
        label.addStyleNames(MaterialTheme.LABEL_H1, MaterialTheme.LABEL_COLORED)
        val grid = Grid<User>()
        grid.addColumn(User::id).caption = "ID"
        grid.addColumn(User::uuid).caption = "UUID"
        grid.addColumn(User::email).caption = "Mail"
        grid.addColumn(User::authority).caption = "Role"
        grid.addColumn(User::createdAt).caption = "Created At"
        grid.addComponentColumn { user ->
            val editButton = Button(VaadinIcons.EDIT, Button.ClickListener {
                Notification.show("Editing user: ${user.email}")
            })
            editButton.addStyleNames(MaterialTheme.BUTTON_PRIMARY, MaterialTheme.BUTTON_SMALL, MaterialTheme.BUTTON_ICON_ONLY)
            editButton
        }
        grid.setItems(userRepository.findAll())
        grid.setSizeFull()
        addComponents(label, grid)
    }
}
