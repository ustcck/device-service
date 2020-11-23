package com.lenovo.cloud.device.repository;

import com.lenovo.cloud.device.domain.PowerDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PowerDevice entity.
 */
@Repository
public interface PowerDeviceRepository extends JpaRepository<PowerDevice, Long>, JpaSpecificationExecutor<PowerDevice> {
}
