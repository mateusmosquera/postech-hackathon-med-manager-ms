package br.com.fiap.med.domain.entities.extension

import br.com.fiap.med.application.dto.response.UnavailableHourResponse
import br.com.fiap.med.domain.entities.UnavailableHours

fun UnavailableHours.toDTO() = UnavailableHourResponse(id = id,startTime = startTime, endTime = endTime)