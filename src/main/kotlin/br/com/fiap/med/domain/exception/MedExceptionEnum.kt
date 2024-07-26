package br.com.fiap.med.domain.exception

import br.com.fiap.med.exception.dto.ResponseErrorDto
import br.com.fiap.med.exception.enums.ExceptionEnum
import org.springframework.http.HttpStatus

enum class MedExceptionEnum(private val error: String,
                            private val httpStatusCode: HttpStatus) : ExceptionEnum {

    MED_NOT_FOUND("Med not found.", HttpStatus.NOT_FOUND),
    MED_ALREADY_EXISTS_CPF("CPF already registered for another med", HttpStatus.BAD_REQUEST),
    MED_ALREADY_EXISTS_EMAIL("Email already registered for another med.", HttpStatus.BAD_REQUEST),
    MED_ALREADY_EXISTS_CRM("CRM already registered for another doctor.", HttpStatus.BAD_REQUEST);

    override fun getResponseError(): ResponseErrorDto {
        return ResponseErrorDto(error = error, status = httpStatusCode.value())
    }

}