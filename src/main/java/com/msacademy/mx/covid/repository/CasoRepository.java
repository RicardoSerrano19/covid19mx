package com.msacademy.mx.covid.repository;

import com.msacademy.mx.covid.model.Caso;
import com.msacademy.mx.covid.model.dto.CasoDTO;
import com.msacademy.mx.covid.model.dto.ConfirmadosTiempoDTO;
import com.msacademy.mx.covid.model.dto.TipoCasoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasoRepository extends JpaRepository<Caso,Integer> {

    @Query(
            value = "SELECT c FROM Caso c WHERE c.resultado = :resultado")
    List<Caso> findAll(@Param("resultado") Integer resultado);


    @Query("SELECT new com.msacademy.mx.covid.model.dto.CasoDTO(c.entidadUnidadMedica.codigo AS estado,SUM(CASE WHEN c.resultado = 1 THEN 1 ELSE 0 END) AS confirmados," +
            "SUM(CASE WHEN c.resultado = 3 THEN 1 ELSE 0 END) AS sospechosos," +
            "SUM(CASE WHEN c.resultado = 2 THEN 1 ELSE 0 END) AS negativos," +
            "SUM(CASE WHEN c.fechaDefuncion != '2000-01-01' AND c.resultado = 1 THEN 1 ELSE 0 END) AS muertes," +
            "SUM(CASE WHEN c.sexo = 1 AND c.resultado = 1 THEN 1 ELSE 0 END) AS mujer," +
            "SUM(CASE WHEN c.sexo = 2 AND c.resultado = 1 THEN 1 ELSE 0 END) AS hombre) " +
            "FROM Caso c "+
            "GROUP BY c.entidadUnidadMedica.descripcion "+
            "ORDER BY c.entidadUnidadMedica.descripcion")
    List<CasoDTO> findResumenTodo();

    @Query("SELECT new com.msacademy.mx.covid.model.dto.ConfirmadosTiempoDTO(c.fechaIngreso AS fecha,COUNT(c) AS cantidad) FROM Caso c WHERE c.entidadUnidadMedica.codigo = :codigo AND c.resultado = 1 " +
            "GROUP BY c.fechaIngreso " +
            "ORDER BY fechaIngreso")
    List<ConfirmadosTiempoDTO> findLineaTiempoConfirmados(@Param("codigo") String state);

    @Query("SELECT new com.msacademy.mx.covid.model.dto.ConfirmadosTiempoDTO(c.fechaIngreso AS fecha,COUNT(c) AS cantidad) FROM Caso c WHERE c.entidadUnidadMedica.codigo = :codigo AND c.resultado = 2 " +
            "GROUP BY c.fechaIngreso " +
            "ORDER BY fechaIngreso")
    List<ConfirmadosTiempoDTO> findLineaTiempoSospechosos(@Param("codigo") String state);

    @Query("SELECT new com.msacademy.mx.covid.model.dto.ConfirmadosTiempoDTO(c.fechaDefuncion AS fecha,COUNT(c) AS cantidad) FROM Caso c WHERE c.entidadUnidadMedica.codigo = :codigo AND c.resultado = 1 " +
            "AND c.fechaDefuncion != '2000-01-01' " +
            "GROUP BY c.fechaDefuncion " +
            "ORDER BY fechaDefuncion")
    List<ConfirmadosTiempoDTO> findLineaTiempoMuertes(@Param("codigo") String state);

    @Query("SELECT new com.msacademy.mx.covid.model.dto.TipoCasoDTO(c.entidadUnidadMedica.codigo AS estado," +
            "SUM(CASE WHEN c.tipoPaciente = 1 THEN 1 ELSE 0 END) AS ambulatorio," +
            "SUM(CASE WHEN c.tipoPaciente = 2 AND c.intubado != 1 THEN 1 ELSE 0 END) AS estable," +
            "SUM(CASE WHEN c.intubado = 1 THEN 1 ELSE 0 END) AS intubado) " +
            "FROM Caso c WHERE c.resultado = 1 " +
            "GROUP BY c.entidadUnidadMedica.codigo " +
            "ORDER BY c.entidadUnidadMedica.codigo")
    List<TipoCasoDTO> findByTipoPaciente();

    @Query("SELECT new com.msacademy.mx.covid.model.dto.TipoCasoDTO(c.entidadUnidadMedica.codigo AS estado," +
            "SUM(CASE WHEN c.tipoPaciente = 1 THEN 1 ELSE 0 END) AS ambulatorio," +
            "SUM(CASE WHEN c.tipoPaciente = 2 AND c.intubado != 1 THEN 1 ELSE 0 END) AS estable," +
            "SUM(CASE WHEN c.intubado = 1 THEN 1 ELSE 0 END) AS intubado) " +
            "FROM Caso c WHERE c.resultado = 1 " +
            "AND c.edad BETWEEN :inicio AND :fin " +
            "GROUP BY c.entidadUnidadMedica.codigo " +
            "ORDER BY c.entidadUnidadMedica.codigo")
    List<TipoCasoDTO> findByTipoPacienteAndAge(@Param("inicio") Integer inicio, @Param("fin") Integer fin);



}
