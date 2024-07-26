package br.com.fiap.med.infrastructure.web.controller

import br.com.fiap.med.application.controller.UnavailableHourApplicationController
import br.com.fiap.med.application.dto.request.UnavailableHourRequest
import br.com.fiap.med.application.dto.response.UnavailableHourResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/med/{medId}/unavailableHours")
class UnavailableHourHttpController(private val unavailableHourApplicationService: UnavailableHourApplicationController) {

    @PostMapping
    fun createUnavailableHour(
        @PathVariable medId: Long,
        @Valid @RequestBody unavailableHourRequest: UnavailableHourRequest
    ): ResponseEntity<UnavailableHourResponse> {
        val createdUnavailableHour = unavailableHourApplicationService.createUnavailableHour(
            medId,
            unavailableHourRequest
        )
        return ResponseEntity(createdUnavailableHour, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllBusyHours(@PathVariable medId: Long): ResponseEntity<List<UnavailableHourResponse>> {
        val busyHours = unavailableHourApplicationService.getBusyHours(medId)
        return ResponseEntity(busyHours, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUnavailableHour(@PathVariable id: Long): ResponseEntity<Void> {
        unavailableHourApplicationService.deleteUnavailableHour(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}