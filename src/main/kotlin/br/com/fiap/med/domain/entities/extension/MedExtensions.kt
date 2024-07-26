package br.com.fiap.med.domain.entities.extension

import br.com.fiap.med.application.dto.request.MedRequest
import br.com.fiap.med.application.dto.response.MedResponse
import br.com.fiap.med.domain.entities.Med
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

fun Med.toDTO() = MedResponse(id = id,name = name, email = email, speciality = specialty)
fun MedRequest.toEntity(): Med {

    val geometryFactory = GeometryFactory()

    val coordinate = Coordinate(longitude!!, latitude!!)

    val point = geometryFactory.createPoint(coordinate)

    return Med(id = null, cpf = cpf, name = name, email = email, crm = crm, specialty = specialty, geometryPoint = point)
}
