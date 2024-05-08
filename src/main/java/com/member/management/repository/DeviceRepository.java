package com.member.management.repository;

import com.member.management.model.entity.Device;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String>, JpaSpecificationExecutor<Device> {
}
