package br.com.fiap.med.domain.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.validation.constraints.Email
import org.locationtech.jts.geom.Point
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Med(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_id_seq")
    @SequenceGenerator(name = "med_id_seq", sequenceName = "med_id_seq", allocationSize = 1)
    val id: Long?,
    @Column(name = "cpf", unique = true, nullable = false)
    val cpf: String,
    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "crm", unique = true, nullable = false)
    val crm: String,

    @Email
    @Column(name = "email", unique = true, nullable = false)
    val email: String,

    @Column(name = "speciality", unique = false)
    val specialty: String?,

    @OneToMany(mappedBy = "med")
    val evaluations: List<Evaluation> = listOf(),

    @Column(name = "geometry_point", columnDefinition = "geometry(Point, 4326)", nullable = false)
    var geometryPoint: Point,

    @OneToMany(mappedBy = "med", cascade = [CascadeType.ALL])
    val unavailableHours: List<UnavailableHours> = listOf(),

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    var createDate: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "update_date", nullable = false, updatable = false)
    var updateDate: LocalDateTime? = null)
