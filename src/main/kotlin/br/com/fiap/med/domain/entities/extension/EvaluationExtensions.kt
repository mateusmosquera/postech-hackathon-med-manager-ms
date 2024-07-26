package br.com.fiap.med.domain.entities.extension

import br.com.fiap.med.application.dto.response.EvaluationResponse
import br.com.fiap.med.domain.entities.Evaluation

fun Evaluation.toDTO() = EvaluationResponse(clientRate = clientRate, evaluationId = id!!)