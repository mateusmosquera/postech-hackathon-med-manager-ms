package br.com.fiap.med.application.dto.request

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class UnavailableHourRequest(
    @field:NotNull val startTime: LocalDateTime,
    @field:NotNull val endTime: LocalDateTime)