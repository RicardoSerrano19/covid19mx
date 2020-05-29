package com.msacademy.mx.covid.repository;

import com.msacademy.mx.covid.model.dto.EnfermedadesDTO;
import com.msacademy.mx.covid.model.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad,Integer> {

    @Query("SELECT new com.msacademy.mx.covid.model.dto.EnfermedadesDTO(c.entidadUnidadMedica.codigo AS estado,COUNT(c) As totalMuertes," +
            "SUM(CASE WHEN c.enfermedad.neumonia = 1 THEN 1 ELSE 0 END) AS neumonia," +
            "SUM(CASE WHEN c.enfermedad.diabetes = 1 THEN 1 ELSE 0 END) AS diabetes," +
            "SUM(CASE WHEN c.enfermedad.epoc = 1 THEN 1 ELSE 0 END) AS epoc," +
            "SUM(CASE WHEN c.enfermedad.hipertension = 1 THEN 1 ELSE 0 END) AS hipertension," +
            "SUM(CASE WHEN c.enfermedad.asma = 1 THEN 1 ELSE 0 END) AS asma," +
            "SUM(CASE WHEN c.enfermedad.inmunusuprimido = 1 THEN 1 ELSE 0 END) AS inmunusuprimido," +
            "SUM(CASE WHEN c.enfermedad.cardiovascular = 1 THEN 1 ELSE 0 END) AS cardiovascular," +
            "SUM(CASE WHEN c.enfermedad.obesidad = 1 THEN 1 ELSE 0 END) AS obesidad," +
            "SUM(CASE WHEN c.enfermedad.renal_cronica = 1 THEN 1 ELSE 0 END) AS renalCronica) " +
            "FROM Caso c WHERE c.resultado = 1 " +
            "AND c.fechaDefuncion != '2000-01-01' " +
            "GROUP BY c.entidadUnidadMedica.codigo " +
            "ORDER BY c.entidadUnidadMedica.codigo")
    List<EnfermedadesDTO> findByEnfermedad();

    @Query("SELECT new com.msacademy.mx.covid.model.dto.EnfermedadesDTO(c.entidadUnidadMedica.codigo AS estado,COUNT(c) As totalMuertes," +
            "SUM(CASE WHEN c.enfermedad.neumonia = 1 THEN 1 ELSE 0 END) AS neumonia," +
            "SUM(CASE WHEN c.enfermedad.diabetes = 1 THEN 1 ELSE 0 END) AS diabetes," +
            "SUM(CASE WHEN c.enfermedad.epoc = 1 THEN 1 ELSE 0 END) AS epoc," +
            "SUM(CASE WHEN c.enfermedad.hipertension = 1 THEN 1 ELSE 0 END) AS hipertension," +
            "SUM(CASE WHEN c.enfermedad.asma = 1 THEN 1 ELSE 0 END) AS asma," +
            "SUM(CASE WHEN c.enfermedad.inmunusuprimido = 1 THEN 1 ELSE 0 END) AS inmunusuprimido," +
            "SUM(CASE WHEN c.enfermedad.cardiovascular = 1 THEN 1 ELSE 0 END) AS cardiovascular," +
            "SUM(CASE WHEN c.enfermedad.obesidad = 1 THEN 1 ELSE 0 END) AS obesidad," +
            "SUM(CASE WHEN c.enfermedad.renal_cronica = 1 THEN 1 ELSE 0 END) AS renalCronica) " +
            "FROM Caso c WHERE c.resultado = 1 " +
            "AND c.fechaDefuncion != '2000-01-01' " +
            "AND c.entidadUnidadMedica.codigo = :codigo " +
            "GROUP BY c.entidadUnidadMedica.codigo " +
            "ORDER BY c.entidadUnidadMedica.codigo")
    EnfermedadesDTO findByEnfermedadAndState(@Param("codigo") String state);
}
