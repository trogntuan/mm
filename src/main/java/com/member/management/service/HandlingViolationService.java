package com.member.management.service;

import com.member.management.model.entity.HandlingViolation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface HandlingViolationService {
    Page<HandlingViolation> getViolationsByMemberId(Specification<HandlingViolation> specification, PageRequest pageRequest);
}
