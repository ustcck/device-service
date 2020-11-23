package com.lenovo.cloud.device.resource;

import com.lenovo.cloud.device.domain.Threshold;
import com.lenovo.cloud.device.resource.errors.BadRequestAlertException;
import com.lenovo.cloud.device.service.ThresholdQueryService;
import com.lenovo.cloud.device.service.ThresholdService;
import com.lenovo.cloud.device.service.dto.ThresholdCriteria;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ThresholdResource {

    private final Logger log = LoggerFactory.getLogger(ThresholdResource.class);

    private static final String ENTITY_NAME = "threshold";

    @Value("${clientApp.name}")
    private String applicationName;

    private final ThresholdService thresholdService;

    private final ThresholdQueryService thresholdQueryService;

    public ThresholdResource(ThresholdService thresholdService, ThresholdQueryService thresholdQueryService) {
        this.thresholdService = thresholdService;
        this.thresholdQueryService = thresholdQueryService;
    }

    @PostMapping("/thresholds")
    public ResponseEntity<Threshold> createThreshold(@RequestBody Threshold threshold) throws URISyntaxException {
        log.debug("REST request to save Threshold : {}", threshold);
        if (threshold.getId() != null) {
            throw new BadRequestAlertException("A new threshold cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Threshold result = thresholdService.save(threshold);
        return ResponseEntity.created(new URI("/api/thresholds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/thresholds")
    public ResponseEntity<Threshold> updateThreshold(@RequestBody Threshold threshold) throws URISyntaxException {
        log.debug("REST request to update Threshold : {}", threshold);
        if (threshold.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Threshold result = thresholdService.save(threshold);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, threshold.getId().toString()))
            .body(result);
    }

    @GetMapping("/thresholds")
    public ResponseEntity<List<Threshold>> getAllThresholds(ThresholdCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Thresholds by criteria: {}", criteria);
        Page<Threshold> page = thresholdQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/thresholds/count")
    public ResponseEntity<Long> countThresholds(ThresholdCriteria criteria) {
        log.debug("REST request to count Thresholds by criteria: {}", criteria);
        return ResponseEntity.ok().body(thresholdQueryService.countByCriteria(criteria));
    }

    @GetMapping("/thresholds/{id}")
    public ResponseEntity<Threshold> getThreshold(@PathVariable Long id) {
        log.debug("REST request to get Threshold : {}", id);
        Optional<Threshold> threshold = thresholdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(threshold);
    }

    @DeleteMapping("/thresholds/{id}")
    public ResponseEntity<Void> deleteThreshold(@PathVariable Long id) {
        log.debug("REST request to delete Threshold : {}", id);
        thresholdService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
