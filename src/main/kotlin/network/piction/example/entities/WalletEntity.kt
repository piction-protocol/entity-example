package network.piction.example.entities

import javax.persistence.*

@MappedSuperclass
class WalletEntity : ParentEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "publicKey", columnDefinition = "char(42)", nullable = false, unique = true)
    lateinit var publicKey: String

    @Column(name = "privateKey", nullable = false, unique = true)
    lateinit var privateKey: String
}
