package br.com.fiap.med.application.gateway

import br.com.fiap.med.domain.entities.UnavailableHours
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface UnavailableHourRepositoryGateway {
    fun save(unavailableHour: UnavailableHours): UnavailableHours
    fun findById(id: Long): UnavailableHours?
    fun delete(unavailable: UnavailableHours)
    fun findByMedId(medId: Long): List<UnavailableHours>
}