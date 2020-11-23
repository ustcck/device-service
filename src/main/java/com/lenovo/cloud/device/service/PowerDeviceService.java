package com.lenovo.cloud.device.service;

import com.lenovo.cloud.device.domain.PowerDevice;
import com.lenovo.cloud.device.repository.PowerDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PowerDeviceService {

    private final Logger log = LoggerFactory.getLogger(PowerDeviceService.class);

    private final PowerDeviceRepository powerDeviceRepository;

    public PowerDeviceService(PowerDeviceRepository powerDeviceRepository) {
        this.powerDeviceRepository = powerDeviceRepository;
    }

    public PowerDevice save(PowerDevice powerDevice) {
        log.debug("Request to save PowerDevice : {}", powerDevice);
        return powerDeviceRepository.save(powerDevice);
    }

    @Transactional(readOnly = true)
    public Page<PowerDevice> findAll(Pageable pageable) {
        log.debug("Request to get all PowerDevices");
        return powerDeviceRepository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public Optional<PowerDevice> findOne(Long id) {
        log.debug("Request to get PowerDevice : {}", id);
        return powerDeviceRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete PowerDevice : {}", id);
        powerDeviceRepository.deleteById(id);
    }
}
