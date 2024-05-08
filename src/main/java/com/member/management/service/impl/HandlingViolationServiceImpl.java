package com.member.management.service.impl;

import com.member.management.model.entity.HandlingViolation;
import com.member.management.repository.HandlingViolationRepository;
import com.member.management.service.HandlingViolationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class HandlingViolationServiceImpl implements HandlingViolationService {
    private final HandlingViolationRepository handlingViolationRepository;

    public HandlingViolationServiceImpl(HandlingViolationRepository handlingViolationRepository) {
        this.handlingViolationRepository = handlingViolationRepository;
    }

    @Override
    public Page<HandlingViolation> getViolationsByMemberId(Specification<HandlingViolation> specification, PageRequest pageRequest) {
        return handlingViolationRepository.findAll(specification, pageRequest);
    }
}
