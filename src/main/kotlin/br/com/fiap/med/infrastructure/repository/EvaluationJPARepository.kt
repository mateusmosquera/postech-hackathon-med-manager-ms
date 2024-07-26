package br.com.fiap.med.infrastructure.repository

import br.com.fiap.med.application.gateway.EvaluationRepositoryGateway
import br.com.fiap.med.domain.entities.Evaluation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluationJPARepository : EvaluationRepositoryGateway, JpaRepository<Evaluation, Long>{

}