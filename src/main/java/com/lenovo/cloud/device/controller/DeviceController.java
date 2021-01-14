package com.lenovo.cloud.device.controller;

import com.lenovo.cloud.device.domain.PatrolDevice;
import com.lenovo.cloud.device.service.PatrolDeviceQueryService;
import com.lenovo.cloud.device.service.dto.PatrolDeviceCriteria;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.PaginationUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-service")
public class DeviceController {

    private final PatrolDeviceQueryService patrolDeviceQueryService;

    public DeviceController(PatrolDeviceQueryService patrolDeviceQueryService) {
        this.patrolDeviceQueryService = patrolDeviceQueryService;
    }

    @ApiOperation("电力设备列表")
    @GetMapping("/devices/powers")
    public ResponseEntity<List<PatrolDevice>> getAllPowerDevices(
            @RequestParam(required = false) String deviceName,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PatrolDeviceCriteria criteria = new PatrolDeviceCriteria();
        criteria.setName(new StringFilter().setContains(deviceName));
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<PatrolDevice> page = patrolDeviceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
