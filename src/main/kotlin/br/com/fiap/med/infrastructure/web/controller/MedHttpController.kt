package br.com.fiap.med.infrastructure.web.controller

import br.com.fiap.med.application.controller.MedApplicationController
import br.com.fiap.med.application.dto.request.EvaluationRequest
import br.com.fiap.med.application.dto.request.MedRequest
import br.com.fiap.med.application.dto.response.EvaluationResponse
import br.com.fiap.med.application.dto.response.MedResponse
import jakarta.validation.Valid
import org.locationtech.jts.geom.Point
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/med")
class MedHttpController(private val medService: MedApplicationController) {

    @PostMapping
    fun create(@Valid @RequestBody med: MedRequest,
               uriBuilder: UriComponentsBuilder): ResponseEntity<MedResponse> {
        val medCreated = medService.create(med)
        val uri = uriBuilder.path("/api/v1/med/{cpf}").buildAndExpand(med.cpf).toUri()
        return ResponseEntity.created(uri).body(medCreated)
    }

    @GetMapping("/{cpf}/cpf")
    fun findByCpf(@PathVariable(required = true) cpf: String) =
        ResponseEntity(medService.findByCpf(cpf = cpf), HttpStatus.OK)

    @GetMapping("/{id}")
    fun findById(@PathVariable(required = true) id: Long) =
        ResponseEntity(medService.findById(id = id), HttpStatus.OK)

    @GetMapping("/list")
    fun listAllDoctor(@RequestParam(required = false) specialty: String?,
                      @RequestParam(required = false) minRating: Int?,
                      @RequestParam(required = false) maxRating: Int?,
                      @RequestParam(required = false) longitude: Double?,
                      @RequestParam(required = false) latitude: Double?,
                      @RequestParam(defaultValue = "0") page: Int,
                      @RequestParam(defaultValue = "10") size: Int
    ):ResponseEntity<Page<MedResponse>> {
        val pageable = PageRequest.of(page, size)

        return ResponseEntity(medService.listAllDoctors(specialty,
                                                        minRating,
                                                        maxRating,
                                                        longitude,
                                                        latitude,
                                                        pageable), HttpStatus.OK)
    }


    @PostMapping("/{medId}/evaluation")
    fun addEvaluation(@PathVariable medId: Long, @RequestBody evaluation: EvaluationRequest): ResponseEntity<EvaluationResponse> {
        val response = medService.addEvaluation(medId, evaluation.clientRate)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }



}

