package com.lenovo.cloud.device.repository;

import com.lenovo.cloud.device.domain.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Threshold entity.
 */
@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long>, JpaSpecificationExecutor<Threshold> {
}
