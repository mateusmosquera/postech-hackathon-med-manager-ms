package br.com.fiap.med.application.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF

data class MedRequest(@field:CPF @field:NotBlank val cpf: String,
                      @field:NotBlank val name: String,
                      @field:Email @field:NotBlank val email: String,
                      @field:NotBlank val crm: String,
                      @field:NotBlank val specialty: String,
                      @field:NotNull val latitude: Double,
                      @field:NotNull val longitude: Double
                      )