package br.com.fiap.med.application.gateway

import br.com.fiap.med.domain.entities.Med
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface MedRepositoryGateway {
    fun save(med: Med): Med
    fun findByCpf(cpf: String): Med?
    fun existsByCpf(cpf: String): Boolean
    fun existsByEmail(email: String): Boolean

    fun findById(id: Long): Med?

    fun findAllMed(
        specialty: String?,
        minRating: Int?,
        maxRating: Int?,
        point: String?,
        pageable: Pageable
    ): Page<Med>
    fun existsByCrm(it: String): Boolean
}