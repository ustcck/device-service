package com.lenovo.cloud.device.resource;

import com.lenovo.cloud.device.domain.PatrolDevice;
import com.lenovo.cloud.device.resource.errors.BadRequestAlertException;
import com.lenovo.cloud.device.service.PatrolDeviceQueryService;
import com.lenovo.cloud.device.service.PatrolDeviceService;
import com.lenovo.cloud.device.service.dto.PatrolDeviceCriteria;
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
public class PatrolDeviceResource {

    private final Logger log = LoggerFactory.getLogger(PatrolDeviceResource.class);

    private static final String ENTITY_NAME = "patrolDevice";

    @Value("${clientApp.name}")
    private String applicationName;

    private final PatrolDeviceService patrolDeviceService;

    private final PatrolDeviceQueryService patrolDeviceQueryService;

    public PatrolDeviceResource(PatrolDeviceService patrolDeviceService, PatrolDeviceQueryService patrolDeviceQueryService) {
        this.patrolDeviceService = patrolDeviceService;
        this.patrolDeviceQueryService = patrolDeviceQueryService;
    }

    @PostMapping("/patrol-devices")
    public ResponseEntity<PatrolDevice> createPatrolDevice(@Valid @RequestBody PatrolDevice patrolDevice) throws URISyntaxException {
        log.debug("REST request to save PatrolDevice : {}", patrolDevice);
        if (patrolDevice.getId() != null) {
            throw new BadRequestAlertException("A new patrolDevice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PatrolDevice result = patrolDeviceService.save(patrolDevice);
        return ResponseEntity.created(new URI("/api/patrol-devices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/patrol-devices")
    public ResponseEntity<PatrolDevice> updatePatrolDevice(@Valid @RequestBody PatrolDevice patrolDevice) throws URISyntaxException {
        log.debug("REST request to update PatrolDevice : {}", patrolDevice);
        if (patrolDevice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PatrolDevice result = patrolDeviceService.save(patrolDevice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, patrolDevice.getId().toString()))
            .body(result);
    }

    @GetMapping("/patrol-devices")
    public ResponseEntity<List<PatrolDevice>> getAllPatrolDevices(PatrolDeviceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PatrolDevices by criteria: {}", criteria);
        Page<PatrolDevice> page = patrolDeviceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/patrol-devices/count")
    public ResponseEntity<Long> countPatrolDevices(PatrolDeviceCriteria criteria) {
        log.debug("REST request to count PatrolDevices by criteria: {}", criteria);
        return ResponseEntity.ok().body(patrolDeviceQueryService.countByCriteria(criteria));
    }

    @GetMapping("/patrol-devices/{id}")
    public ResponseEntity<PatrolDevice> getPatrolDevice(@PathVariable Long id) {
        log.debug("REST request to get PatrolDevice : {}", id);
        Optional<PatrolDevice> patrolDevice = patrolDeviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patrolDevice);
    }

    @DeleteMapping("/patrol-devices/{id}")
    public ResponseEntity<Void> deletePatrolDevice(@PathVariable Long id) {
        log.debug("REST request to delete PatrolDevice : {}", id);
        patrolDeviceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
