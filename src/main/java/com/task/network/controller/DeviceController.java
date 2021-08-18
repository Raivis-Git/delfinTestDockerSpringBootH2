package com.task.network.controller;

import com.task.network.controller.response.DeviceByMacAddressResponse;
import com.task.network.model.Device;
import com.task.network.model.Node;
import com.task.network.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/device")
public class DeviceController {
    private final DeviceService deviceService;
    
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    
    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        if (deviceService.addDevice(device))
            return ResponseEntity.created(null).build();
        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("findAll")
    public List<Device> getAllDevices() {
        return deviceService.findAllDevices();
    }
    
    @GetMapping("macAddress/{macAddress}")
    public ResponseEntity<?> getByMacAddress(@PathVariable String macAddress) {
        Device device = deviceService.findByMacAddress(macAddress);
        if (device == null)
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok().body(new DeviceByMacAddressResponse(device.getDeviceType(), device.getMacAddress()));
    }
    
    @GetMapping("topology")
    public ResponseEntity<?> getDeviceTree() {
        HashMap<String, ArrayList<String>> deviceTopology = deviceService.orderDevicesFirstLevel();
        Node node = deviceService.getDeviceTopologyForDevice("00-40-96-01-23-47");
        return ResponseEntity.ok().body(deviceTopology);
    }
    
}
