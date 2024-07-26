package br.com.fiap.med.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
data class UnavailableHours(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unavailable_hours_id_seq")
    @SequenceGenerator(name = "unavailable_hours_id_seq", sequenceName = "unavailable_hours_id_seq", allocationSize = 1)
    val id: Long?,
    @Column(name = "start_time", nullable = false)
    val startTime: LocalDateTime,
    @Column(name = "end_time", nullable = false)
    val endTime: LocalDateTime,
    @ManyToOne
    val med: Med
)