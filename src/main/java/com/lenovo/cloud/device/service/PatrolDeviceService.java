package com.lenovo.cloud.device.service;

import com.lenovo.cloud.device.domain.PatrolDevice;
import com.lenovo.cloud.device.repository.PatrolDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PatrolDeviceService {

    private final Logger log = LoggerFactory.getLogger(PatrolDeviceService.class);

    private final PatrolDeviceRepository patrolDeviceRepository;

    public PatrolDeviceService(PatrolDeviceRepository patrolDeviceRepository) {
        this.patrolDeviceRepository = patrolDeviceRepository;
    }

    public PatrolDevice save(PatrolDevice patrolDevice) {
        log.debug("Request to save PatrolDevice : {}", patrolDevice);
        return patrolDeviceRepository.save(patrolDevice);
    }

    @Transactional(readOnly = true)
    public Page<PatrolDevice> findAll(Pageable pageable) {
        log.debug("Request to get all PatrolDevices");
        return patrolDeviceRepository.findAll(pageable);
    }


    public Page<PatrolDevice> findAllWithEagerRelationships(Pageable pageable) {
        return patrolDeviceRepository.findAllWithEagerRelationships(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<PatrolDevice> findOne(Long id) {
        log.debug("Request to get PatrolDevice : {}", id);
        return patrolDeviceRepository.findOneWithEagerRelationships(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete PatrolDevice : {}", id);
        patrolDeviceRepository.deleteById(id);
    }
}
