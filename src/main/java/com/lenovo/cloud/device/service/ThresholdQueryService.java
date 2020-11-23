package com.lenovo.cloud.device.service;

import com.lenovo.cloud.device.domain.Threshold;
import com.lenovo.cloud.device.domain.*; // for static metamodels
import com.lenovo.cloud.device.repository.ThresholdRepository;
import com.lenovo.cloud.device.service.dto.ThresholdCriteria;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ThresholdQueryService extends QueryService<Threshold> {

    private final Logger log = LoggerFactory.getLogger(ThresholdQueryService.class);

    private final ThresholdRepository thresholdRepository;

    public ThresholdQueryService(ThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    @Transactional(readOnly = true)
    public List<Threshold> findByCriteria(ThresholdCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Threshold> specification = createSpecification(criteria);
        return thresholdRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Threshold> findByCriteria(ThresholdCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Threshold> specification = createSpecification(criteria);
        return thresholdRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(ThresholdCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Threshold> specification = createSpecification(criteria);
        return thresholdRepository.count(specification);
    }

    protected Specification<Threshold> createSpecification(ThresholdCriteria criteria) {
        Specification<Threshold> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Threshold_.id));
            }
            if (criteria.getLowLimit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLowLimit(), Threshold_.lowLimit));
            }
            if (criteria.getHighLimit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHighLimit(), Threshold_.highLimit));
            }
            if (criteria.getOpenCloseIndicator() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOpenCloseIndicator(), Threshold_.openCloseIndicator));
            }
        }
        return specification;
    }
}
