package cloud.martijn.template.ui.view.dashboard

import cloud.martijn.template.domain.Authority
import cloud.martijn.template.domain.User
import cloud.martijn.template.domain.repository.UserRepository
import com.github.appreciated.material.MaterialTheme
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.Grid
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.crypto.password.PasswordEncoder
import org.vaadin.viritin.form.AbstractForm
import javax.annotation.PostConstruct
import javax.annotation.security.RolesAllowed

@SpringView(name = "users")
@Secured(Authority.ADMIN)
class UserView : VerticalLayout(), View {

    @Autowired
    lateinit var userRepository: UserRepository

    private val userForm = UserForm()

    private val createForm = CreateUserForm()

    private val grid: Grid<User> = buildGrid()

    @set:Autowired
    lateinit var encoder: PasswordEncoder

    @PostConstruct
    fun init() {
        userForm.savedHandler = handleUserSave()
        userForm.deleteHandler = handleUserDelete()
        createForm.savedHandler = handleUserCreate()

        val label = Label("Users")
        label.addStyleNames(MaterialTheme.LABEL_H1)
        updateGrid(grid)
        grid.setSizeFull()

        val createButton = Button(VaadinIcons.PLUS, createAddUserForm())
        createButton.addStyleNames(MaterialTheme.BUTTON_PRIMARY, MaterialTheme.BUTTON_SMALL, MaterialTheme.BUTTON_ICON_ONLY)

        addComponents(label, grid, createButton)
    }

    private fun createAddUserForm(): Button.ClickListener {
        val user = User(
                email = "email",
                password = "",
                role = Authority.NORMAL
        )
        return Button.ClickListener {
            val window = createForm.openInModalPopup()
            createForm.entity = user
            window.center()
            window.isModal = true
            window.isDraggable = true
            window.isResizable = false
            window.addCloseListener { updateGrid(grid) }
        }
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

    private fun handleUserCreate(): AbstractForm.SavedHandler<User> =
            AbstractForm.SavedHandler { entity ->
                entity.password = encoder.encode(entity.password)
                userRepository.save(entity)
                createForm.closePopup()
            }

    private fun handleUserSave(): AbstractForm.SavedHandler<User> =
            AbstractForm.SavedHandler { entity ->
                userRepository.save(entity)
                userForm.closePopup()
            }

    private fun handleUserDelete(): AbstractForm.DeleteHandler<User> =
            AbstractForm.DeleteHandler { entity ->
                userRepository.delete(entity)
                userForm.closePopup()
            }

    @Secured(Authority.ADMIN)
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
