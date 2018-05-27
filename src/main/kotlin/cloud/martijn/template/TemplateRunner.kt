package cloud.martijn.template

import cloud.martijn.template.domain.Authority
import cloud.martijn.template.domain.User
import cloud.martijn.template.domain.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class TemplateRunner {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @set:Autowired
    lateinit var userRepository: UserRepository
    @set:Autowired
    lateinit var encoder: PasswordEncoder

    @Bean
    fun generateData(): ApplicationRunner {
        return ApplicationRunner {
            if (userRepository.findAll().isEmpty()) {
                val user = User(
                        email = "martijndeklerk@me.com",
                        password = encoder.encode("poep"),
                        role = Authority.ADMIN
                )
                userRepository.save(user)

                logger.info("Saved ${userRepository.count()} user(s).")
                for (singleUser in userRepository.findAll()) {
                    logger.info(singleUser.email)
                }
            }
        }
    }
}