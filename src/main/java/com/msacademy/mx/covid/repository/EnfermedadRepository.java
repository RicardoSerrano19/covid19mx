package com.msacademy.mx.covid.repository;

import com.msacademy.mx.covid.model.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermedadRepository extends JpaRepository<Enfermedad,Integer> {
}
