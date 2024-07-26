package br.com.fiap.med.infrastructure.repository

import br.com.fiap.med.application.gateway.MedRepositoryGateway
import br.com.fiap.med.domain.entities.Med
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MedJPARepository : MedRepositoryGateway, JpaRepository<Med, Long>{

    @Query(value = """
    SELECT m.*, avg_ratings.avg_client_rate
FROM med m
         LEFT JOIN (
    SELECT e.med_id AS doctor_id, AVG(e.client_rate) AS avg_client_rate
    FROM evaluation e
    GROUP BY e.med_id
) avg_ratings ON avg_ratings.doctor_id = m.id
WHERE (m.speciality = :speciality OR :speciality IS NULL)
  AND (avg_ratings.avg_client_rate >= :minRating OR :minRating IS NULL)
  AND (avg_ratings.avg_client_rate <= :maxRating OR :maxRating IS NULL)
ORDER BY CASE
             WHEN :point IS NULL THEN avg_ratings.avg_client_rate
             ELSE ST_Distance(m.geometry_point, ST_GeomFromText(:point, 4326))
             END;
    """, countQuery = "SELECT count(*) FROM med m", nativeQuery = true)
    override fun findAllMed(
        @Param("speciality") speciality: String?,
        @Param("minRating") minRating: Int?,
        @Param("maxRating") maxRating: Int?,
        @Param("point") point: String?,
        pageable: Pageable
    ): Page<Med>
}