package com.lenovo.cloud.device.resource;

import com.lenovo.cloud.device.domain.PowerDevice;
import com.lenovo.cloud.device.resource.errors.BadRequestAlertException;
import com.lenovo.cloud.device.service.PowerDeviceQueryService;
import com.lenovo.cloud.device.service.PowerDeviceService;
import com.lenovo.cloud.device.service.dto.PowerDeviceCriteria;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PowerDeviceResource {

    private final Logger log = LoggerFactory.getLogger(PowerDeviceResource.class);

    private static final String ENTITY_NAME = "powerDevice";

    @Value("${clientApp.name}")
    private String applicationName;

    private final PowerDeviceService powerDeviceService;

    private final PowerDeviceQueryService powerDeviceQueryService;

    public PowerDeviceResource(PowerDeviceService powerDeviceService, PowerDeviceQueryService powerDeviceQueryService) {
        this.powerDeviceService = powerDeviceService;
        this.powerDeviceQueryService = powerDeviceQueryService;
    }

    @PostMapping("/power-devices")
    public ResponseEntity<PowerDevice> createPowerDevice(@Valid @RequestBody PowerDevice powerDevice) throws URISyntaxException {
        log.debug("REST request to save PowerDevice : {}", powerDevice);
        if (powerDevice.getId() != null) {
            throw new BadRequestAlertException("A new powerDevice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PowerDevice result = powerDeviceService.save(powerDevice);
        return ResponseEntity.created(new URI("/api/power-devices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/power-devices")
    public ResponseEntity<PowerDevice> updatePowerDevice(@Valid @RequestBody PowerDevice powerDevice) throws URISyntaxException {
        log.debug("REST request to update PowerDevice : {}", powerDevice);
        if (powerDevice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PowerDevice result = powerDeviceService.save(powerDevice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, powerDevice.getId().toString()))
            .body(result);
    }

    @GetMapping("/power-devices")
    public ResponseEntity<List<PowerDevice>> getAllPowerDevices(PowerDeviceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PowerDevices by criteria: {}", criteria);
        Page<PowerDevice> page = powerDeviceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/power-devices/count")
    public ResponseEntity<Long> countPowerDevices(PowerDeviceCriteria criteria) {
        log.debug("REST request to count PowerDevices by criteria: {}", criteria);
        return ResponseEntity.ok().body(powerDeviceQueryService.countByCriteria(criteria));
    }

    @GetMapping("/power-devices/{id}")
    public ResponseEntity<PowerDevice> getPowerDevice(@PathVariable Long id) {
        log.debug("REST request to get PowerDevice : {}", id);
        Optional<PowerDevice> powerDevice = powerDeviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(powerDevice);
    }

    @DeleteMapping("/power-devices/{id}")
    public ResponseEntity<Void> deletePowerDevice(@PathVariable Long id) {
        log.debug("REST request to delete PowerDevice : {}", id);
        powerDeviceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
