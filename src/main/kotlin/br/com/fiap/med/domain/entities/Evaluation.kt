package br.com.fiap.med.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min


@Entity
data class Evaluation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaluation_id_seq")
    @SequenceGenerator(name = "evaluation_id_seq", sequenceName = "evaluation_id_seq", allocationSize = 1)
    val id: Long?,

    @Min(0)
    @Max(5)
    @Column(name = "client_rate", nullable = false)
    val clientRate: Int = 0,

    @ManyToOne
    val med: Med
)