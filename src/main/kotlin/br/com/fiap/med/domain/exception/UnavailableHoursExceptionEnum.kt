package br.com.fiap.med.domain.exception

import br.com.fiap.med.exception.dto.ResponseErrorDto
import br.com.fiap.med.exception.enums.ExceptionEnum
import org.springframework.http.HttpStatus

enum class UnavailableHoursExceptionEnum(private val error: String,
                                         private val httpStatusCode: HttpStatus) : ExceptionEnum {

    UNAVAILABLE_HOURS_NOT_FOUND("Unavailable Hours of the med not found.", HttpStatus.NOT_FOUND),
    UNAVAILABLE_HOUR_OVERLAP_EXISTING("Unavailable Hours lap already exists", HttpStatus.BAD_REQUEST),
    UNAVAILABLE_HOUR_NOT_IN_FUTURE("Unavailable Hours must be in the future", HttpStatus.BAD_REQUEST);

    override fun getResponseError(): ResponseErrorDto {
        return ResponseErrorDto(error = error, status = httpStatusCode.value())
    }

}