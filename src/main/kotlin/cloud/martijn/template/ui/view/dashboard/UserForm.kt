package cloud.martijn.template.ui.view.dashboard

import cloud.martijn.template.domain.User
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.ui.Component
import org.vaadin.viritin.fields.EmailField
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.form.AbstractForm
import org.vaadin.viritin.layouts.MFormLayout

@SpringComponent
class UserForm : AbstractForm<User>(User::class.java) {

    val email = EmailField("Email")
    val name = MTextField("Name")

    override fun createContent(): Component {
        return MFormLayout(name, email, toolbar).withMargin(true)
    }
}