package br.com.fiap.med.application.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull

data class EvaluationRequest(@field:NotNull
                             @field:Min(1)
                             @field:Max(5)
                             val clientRate: Int)