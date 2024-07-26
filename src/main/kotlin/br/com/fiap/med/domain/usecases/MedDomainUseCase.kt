package br.com.fiap.med.domain.usecases

import br.com.fiap.med.application.dto.response.MedResponse
import br.com.fiap.med.application.gateway.EvaluationRepositoryGateway
import br.com.fiap.med.application.gateway.MedRepositoryGateway
import br.com.fiap.med.domain.entities.Evaluation
import br.com.fiap.med.domain.entities.Med
import br.com.fiap.med.domain.exception.MedExceptionEnum
import br.com.fiap.med.exception.BusinessException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MedDomainUseCase(@Qualifier("medJPARepository") private val medRepositoryGateway: MedRepositoryGateway,
                       private val evaluationRepositoryGateway: EvaluationRepositoryGateway) {

    @Transactional
    fun create(med: Med): Med {

        if(medExists(med) == true) {
            throw BusinessException(MedExceptionEnum.MED_ALREADY_EXISTS_CPF)
        }

        if(emailExists(med) == true){
            throw BusinessException(MedExceptionEnum.MED_ALREADY_EXISTS_EMAIL)
        }

        if(crmExists(med) == true){
            throw BusinessException(MedExceptionEnum.MED_ALREADY_EXISTS_CRM)
        }

        return medRepositoryGateway.save(med)
    }

    fun findByCpf(cpf: String) = medRepositoryGateway.findByCpf(cpf)
        ?: throw BusinessException(MedExceptionEnum.MED_NOT_FOUND)

    fun listAllDoctors(
        specialty: String?,
        minRating: Int?,
        maxRating: Int?,
        longitude: Double?,
        latitude: Double?,
        pageable: Pageable
    ): Page<MedResponse> {

        val point: String? = if (longitude != null && latitude != null) {
            String.format("POINT(%s %s)", longitude, latitude)
        } else null

        val medPage = medRepositoryGateway.findAllMed(specialty,minRating,maxRating,point,pageable)

        return medPage.map(this::convertMedicoToMedResponse)
    }

    fun convertMedicoToMedResponse(m: Med): MedResponse {
        return MedResponse(m.id,m.name, m.email, m.specialty)
    }

    fun medExists(med: Med?) = med?.cpf?.let { medRepositoryGateway.existsByCpf(it) }

    fun emailExists(med: Med) = med.email?.let { medRepositoryGateway.existsByEmail(it) }

    fun crmExists(med: Med) = med.crm?.let { medRepositoryGateway.existsByCrm(it) }

    fun findMedByid(id: Long): Med? { return medRepositoryGateway.findById(id) }

    fun addEvaluation(medId: Long, clientRate: Int): Evaluation {

        val optMed = findMedByid(medId) ?:  throw BusinessException(MedExceptionEnum.MED_NOT_FOUND)

       return evaluationRepositoryGateway.save(Evaluation(id = null, clientRate = clientRate, med = optMed))
    }

}