package com.lenovo.cloud.device.service;

import com.lenovo.cloud.device.domain.Threshold;
import com.lenovo.cloud.device.repository.ThresholdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ThresholdService {

    private final Logger log = LoggerFactory.getLogger(ThresholdService.class);

    private final ThresholdRepository thresholdRepository;

    public ThresholdService(ThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    public Threshold save(Threshold threshold) {
        log.debug("Request to save Threshold : {}", threshold);
        return thresholdRepository.save(threshold);
    }

    @Transactional(readOnly = true)
    public Page<Threshold> findAll(Pageable pageable) {
        log.debug("Request to get all Thresholds");
        return thresholdRepository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public Optional<Threshold> findOne(Long id) {
        log.debug("Request to get Threshold : {}", id);
        return thresholdRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Threshold : {}", id);
        thresholdRepository.deleteById(id);
    }
}
