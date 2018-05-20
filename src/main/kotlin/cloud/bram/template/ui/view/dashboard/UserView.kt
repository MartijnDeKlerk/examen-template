package cloud.bram.template.ui.view.dashboard

import cloud.bram.template.domain.User
import cloud.bram.template.domain.repository.UserRepository
import com.github.appreciated.material.MaterialTheme
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.Grid
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.renderers.DateRenderer
import org.springframework.beans.factory.annotation.Autowired
import org.vaadin.viritin.form.AbstractForm
import javax.annotation.PostConstruct

@SpringView(name = "users")
class UserView : VerticalLayout(), View {

    @Autowired
    lateinit var userRepository: UserRepository

    private val userForm = UserForm()

    private val grid: Grid<User> = buildGrid()

    @PostConstruct
    fun init() {
        userForm.savedHandler = handleUserSave()
        val label = Label("Users")
        label.addStyleNames(MaterialTheme.LABEL_H1)
        updateGrid(grid)
        grid.setSizeFull()
        addComponents(label, grid)
    }

    private fun updateGrid(grid: Grid<User>) {
        grid.setItems(userRepository.findAll())

    }

    private fun buildGrid(): Grid<User> {
        val grid = Grid<User>()
        grid.addColumn(User::id).caption = "ID"
        grid.addColumn(User::uuid).caption = "UUID"
        grid.addColumn(User::name).caption = "Name"
        grid.addColumn(User::email).caption = "Mail"
        grid.addColumn(User::authority).caption = "Role"
        grid.addComponentColumn { user ->
            val editButton = Button(VaadinIcons.EDIT, handleEditButtonClick(user))
            editButton.addStyleNames(MaterialTheme.BUTTON_PRIMARY, MaterialTheme.BUTTON_SMALL, MaterialTheme.BUTTON_ICON_ONLY)
            editButton
        }
        return grid
    }

    private fun handleUserSave(): AbstractForm.SavedHandler<User> =
            AbstractForm.SavedHandler { entity ->
                userRepository.save(entity)
                userForm.closePopup()
            }

    private fun handleEditButtonClick(user: User?): Button.ClickListener {
        return Button.ClickListener {
            val window = userForm.openInModalPopup()
            userForm.entity = user
            window.center()
            window.isModal = true
            window.isDraggable = true
            window.isResizable = false
            window.addCloseListener { updateGrid(grid) }
        }
    }
}
