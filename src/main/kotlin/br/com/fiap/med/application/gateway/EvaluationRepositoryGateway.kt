package br.com.fiap.med.application.gateway

import br.com.fiap.med.domain.entities.Evaluation
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface EvaluationRepositoryGateway {
    fun save(evaluation: Evaluation): Evaluation
}