package cloud.martijn.template.ui.view.dashboard

import cloud.martijn.template.domain.User
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.ui.Component
import com.vaadin.ui.PasswordField
import org.vaadin.viritin.fields.EmailField
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.form.AbstractForm
import org.vaadin.viritin.layouts.MFormLayout

@SpringComponent
class CreateUserForm : AbstractForm<User>(User::class.java) {

    val email = EmailField("Email")
    val password = PasswordField("Password")
    val name = MTextField("Name")

    override fun createContent(): Component {
        return MFormLayout(email, password, name, toolbar).withMargin(true)
    }
}