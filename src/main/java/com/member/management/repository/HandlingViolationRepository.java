package com.member.management.repository;

import com.member.management.model.entity.HandlingViolation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandlingViolationRepository extends CrudRepository<HandlingViolation, String>, JpaSpecificationExecutor<HandlingViolation> {
}
