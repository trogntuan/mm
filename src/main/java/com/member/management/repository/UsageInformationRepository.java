package com.member.management.repository;

import com.member.management.model.entity.UsageInformation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageInformationRepository extends PagingAndSortingRepository<UsageInformation, String>,
        CrudRepository<UsageInformation, String>, JpaSpecificationExecutor<UsageInformation> {
}
