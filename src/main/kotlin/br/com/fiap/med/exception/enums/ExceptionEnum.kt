package br.com.fiap.med.exception.enums

import br.com.fiap.med.exception.dto.ResponseErrorDto

fun interface ExceptionEnum {
    fun getResponseError(): ResponseErrorDto
}