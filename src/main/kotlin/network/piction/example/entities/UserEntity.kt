package network.piction.example.entities

import javax.persistence.*

@MappedSuperclass
class UserEntity : ParentEntity() {

    enum class STATUS { ACTIVE, DELETE, DEPRECATED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "login_id", columnDefinition = "varchar(15)", nullable = false, unique = true)
    lateinit var loginId: String

    @Column(columnDefinition = "varchar(100)", nullable = true, unique = true)
    var email: String? = null

    @Column(columnDefinition = "varchar(20)", nullable = false)
    lateinit var username: String

    @Column(columnDefinition = "char(60)", nullable = false)
    lateinit var password: String

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) DEFAULT 'ACTIVE'", nullable = false)
    var status: STATUS = STATUS.ACTIVE
}