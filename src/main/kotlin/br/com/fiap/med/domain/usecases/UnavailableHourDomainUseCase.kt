package br.com.fiap.med.domain.usecases

import br.com.fiap.med.application.gateway.UnavailableHourRepositoryGateway
import br.com.fiap.med.domain.entities.UnavailableHours
import br.com.fiap.med.domain.exception.MedExceptionEnum
import br.com.fiap.med.domain.exception.UnavailableHoursExceptionEnum
import br.com.fiap.med.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class UnavailableHourDomainUseCase(
    private val unavailableHourRepositoryGateway: UnavailableHourRepositoryGateway,
    private val medDomainUseCase: MedDomainUseCase
) {

    @Transactional
    fun createUnavailableHour(medId: Long, startTime: LocalDateTime, endTime: LocalDateTime): UnavailableHours {

        val med = medDomainUseCase.findMedByid(medId) ?:  throw BusinessException(MedExceptionEnum.MED_NOT_FOUND)

        if(overlapsWithExistingUnavailability(medId, startTime, endTime)) {
            throw BusinessException(UnavailableHoursExceptionEnum.UNAVAILABLE_HOUR_OVERLAP_EXISTING)
        }

        if(!isInFuture(startTime, endTime)) {
            throw BusinessException(UnavailableHoursExceptionEnum.UNAVAILABLE_HOUR_NOT_IN_FUTURE)
        }

        val unavailableHour = UnavailableHours(id = null, startTime = startTime, endTime = endTime, med = med)
        return unavailableHourRepositoryGateway.save(unavailableHour)
    }

    fun getBusyHours(medId: Long): List<UnavailableHours> {
        return unavailableHourRepositoryGateway.findByMedId(medId)
    }

    @Transactional
    fun deleteUnavailableHour(id: Long) {
        val unavailableHours = unavailableHourRepositoryGateway.findById(id)
            ?: throw BusinessException(UnavailableHoursExceptionEnum.UNAVAILABLE_HOURS_NOT_FOUND)

        unavailableHourRepositoryGateway.delete(unavailableHours)
    }

    private fun overlapsWithExistingUnavailability(medId: Long, startTime: LocalDateTime, endTime: LocalDateTime): Boolean {
        val unavailabilityList = unavailableHourRepositoryGateway.findByMedId(medId);

        return unavailabilityList.any {
            (it.startTime.isBefore(endTime) && it.endTime.isAfter(startTime))
        }
    }

    private fun isInFuture(startTime: LocalDateTime, endTime: LocalDateTime): Boolean {
        return LocalDateTime.now().isBefore(startTime) && LocalDateTime.now().isBefore(endTime)
    }
}