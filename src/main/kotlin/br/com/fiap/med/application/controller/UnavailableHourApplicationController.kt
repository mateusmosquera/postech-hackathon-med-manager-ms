package br.com.fiap.med.application.controller

import br.com.fiap.med.application.dto.request.UnavailableHourRequest
import br.com.fiap.med.application.dto.response.UnavailableHourResponse
import br.com.fiap.med.domain.entities.extension.toDTO
import br.com.fiap.med.domain.usecases.UnavailableHourDomainUseCase
import org.springframework.stereotype.Service

@Service
class UnavailableHourApplicationController(private val unavailableHourDomainUseCase: UnavailableHourDomainUseCase) {

    fun createUnavailableHour(medId: Long, unavailableHourRequest: UnavailableHourRequest): UnavailableHourResponse =
        unavailableHourDomainUseCase.createUnavailableHour(
            medId,
            unavailableHourRequest.startTime,
            unavailableHourRequest.endTime
        ).toDTO()

    fun getBusyHours(medId: Long): List<UnavailableHourResponse> =
        unavailableHourDomainUseCase.getBusyHours(medId).map { it.toDTO() }

    fun deleteUnavailableHour(id: Long) {
        unavailableHourDomainUseCase.deleteUnavailableHour(id)
    }
}