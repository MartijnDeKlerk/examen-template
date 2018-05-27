package cloud.martijn.template.domain

class Authority {

    companion object {
        fun getAll(): Array<String> = arrayOf(NORMAL, ADMIN)

        const val ADMIN: String = "admin"
        const val NORMAL: String = "normal"
    }
}