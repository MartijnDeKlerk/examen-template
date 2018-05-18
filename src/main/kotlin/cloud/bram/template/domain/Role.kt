package cloud.bram.template.domain

class Role {

    companion object {
        fun getAllRoles(): Array<String> = arrayOf(NORMAL, ADMIN)

        const val ADMIN: String = "admin"
        const val NORMAL: String = "normal"
    }
}