package br.com.fiap.med.application.dto.response

import java.time.LocalDateTime

data class UnavailableHourResponse(
    val id: Long?,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime)