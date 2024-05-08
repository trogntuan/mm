package com.member.management.service;

import com.member.management.model.entity.UsageInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface UsageInformationService {
    Page<UsageInformation> getUsageInformationByMemberId(Specification<UsageInformation> specification, PageRequest pageRequest);
}
