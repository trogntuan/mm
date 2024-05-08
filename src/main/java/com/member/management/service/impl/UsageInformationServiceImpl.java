package com.member.management.service.impl;

import com.member.management.model.entity.UsageInformation;
import com.member.management.repository.UsageInformationRepository;
import com.member.management.service.UsageInformationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UsageInformationServiceImpl implements UsageInformationService {
    private final UsageInformationRepository usageInformationRepository;

    public UsageInformationServiceImpl(UsageInformationRepository usageInformationRepository) {
        this.usageInformationRepository = usageInformationRepository;
    }

    @Override
    public Page<UsageInformation> getUsageInformationByMemberId(Specification<UsageInformation> specification, PageRequest pageRequest) {
        return usageInformationRepository.findAll(specification, pageRequest);
    }
}
