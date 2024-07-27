package br.com.fiap.med.application.controller

import br.com.fiap.med.application.dto.request.MedRequest
import br.com.fiap.med.application.dto.response.EvaluationResponse
import br.com.fiap.med.application.dto.response.MedResponse
import br.com.fiap.med.domain.entities.extension.toDTO
import br.com.fiap.med.domain.entities.extension.toEntity
import br.com.fiap.med.domain.usecases.MedDomainUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MedApplicationController(private val medDomainUseCase: MedDomainUseCase) {

    fun create(med: MedRequest): MedResponse =
        medDomainUseCase.create(med.toEntity()).toDTO()

    fun findByCpf(cpf: String): MedResponse =
        medDomainUseCase.findByCpf(cpf).toDTO()

    fun listAllDoctors(specialty: String?,
                       minRating: Int?,
                       maxRating: Int?,
                       longitude: Double?,
                       latitude: Double?,
                       pageable: Pageable
    ): Page<MedResponse> {
        return medDomainUseCase.listAllDoctors(
            specialty,
            minRating,
            maxRating,
            longitude,
            latitude,
            pageable
        )
    }

    fun addEvaluation(medId: Long, clientRate: Int): EvaluationResponse {
        return medDomainUseCase.addEvaluation(
            medId, clientRate
        ).toDTO()
    }

    fun findById(id: Long): MedResponse = medDomainUseCase.findMedByid(id).toDTO()


}