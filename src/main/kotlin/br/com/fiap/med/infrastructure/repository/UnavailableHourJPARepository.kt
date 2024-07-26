package br.com.fiap.med.infrastructure.repository

import br.com.fiap.med.application.gateway.UnavailableHourRepositoryGateway
import br.com.fiap.med.domain.entities.UnavailableHours
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UnavailableHourJPARepository : UnavailableHourRepositoryGateway, JpaRepository<UnavailableHours, Long>{

}