package com.teste.springgeoservice.repository;

import com.teste.springgeoservice.entity.CoordenadasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadasRepository extends JpaRepository<CoordenadasEntity, Long> {
}
