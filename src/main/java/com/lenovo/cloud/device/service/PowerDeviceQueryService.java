package com.lenovo.cloud.device.service;

import com.lenovo.cloud.device.domain.PowerDevice;
import com.lenovo.cloud.device.domain.*; // for static metamodels
import com.lenovo.cloud.device.repository.PowerDeviceRepository;
import com.lenovo.cloud.device.service.dto.PowerDeviceCriteria;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PowerDeviceQueryService extends QueryService<PowerDevice> {

    private final Logger log = LoggerFactory.getLogger(PowerDeviceQueryService.class);

    private final PowerDeviceRepository powerDeviceRepository;

    public PowerDeviceQueryService(PowerDeviceRepository powerDeviceRepository) {
        this.powerDeviceRepository = powerDeviceRepository;
    }

    @Transactional(readOnly = true)
    public List<PowerDevice> findByCriteria(PowerDeviceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PowerDevice> specification = createSpecification(criteria);
        return powerDeviceRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<PowerDevice> findByCriteria(PowerDeviceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PowerDevice> specification = createSpecification(criteria);
        return powerDeviceRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(PowerDeviceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PowerDevice> specification = createSpecification(criteria);
        return powerDeviceRepository.count(specification);
    }

    protected Specification<PowerDevice> createSpecification(PowerDeviceCriteria criteria) {
        Specification<PowerDevice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PowerDevice_.id));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArea(), PowerDevice_.area));
            }
            if (criteria.getSpace() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSpace(), PowerDevice_.space));
            }
            if (criteria.getMainPart() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMainPart(), PowerDevice_.mainPart));
            }
            if (criteria.getSubPart() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubPart(), PowerDevice_.subPart));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PowerDevice_.name));
            }
            if (criteria.getRecognizeType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecognizeType(), PowerDevice_.recognizeType));
            }
            if (criteria.getRecognizeContent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecognizeContent(), PowerDevice_.recognizeContent));
            }
            if (criteria.getSite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSite(), PowerDevice_.site));
            }
            if (criteria.getLine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLine(), PowerDevice_.line));
            }
            if (criteria.getSource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSource(), PowerDevice_.source));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PowerDevice_.serialNumber));
            }
            if (criteria.getInstallDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInstallDate(), PowerDevice_.installDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), PowerDevice_.status));
            }
            if (criteria.getDeviceModel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDeviceModel(), PowerDevice_.deviceModel));
            }
            if (criteria.getCreateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateTime(), PowerDevice_.createTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PowerDevice_.updateTime));
            }
            if (criteria.getRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemark(), PowerDevice_.remark));
            }
            if (criteria.getThresholdId() != null) {
                specification = specification.and(buildSpecification(criteria.getThresholdId(),
                    root -> root.join(PowerDevice_.threshold, JoinType.LEFT).get(Threshold_.id)));
            }
            if (criteria.getPatrolDeviceId() != null) {
                specification = specification.and(buildSpecification(criteria.getPatrolDeviceId(),
                    root -> root.join(PowerDevice_.patrolDevices, JoinType.LEFT).get(PatrolDevice_.id)));
            }
        }
        return specification;
    }
}
