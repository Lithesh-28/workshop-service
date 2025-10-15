package com.ivoyant.workshop_service.repository;

import com.ivoyant.workshop_service.entity.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic,Integer> {
}
