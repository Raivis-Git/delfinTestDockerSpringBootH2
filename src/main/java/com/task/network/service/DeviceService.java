package com.task.network.service;

import com.task.network.model.Device;
import com.task.network.model.Node;
import com.task.network.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    
    public List<Device> findAllDevices() {
        return deviceRepository.findAllSortedByDeviceType();
    }
    
    public boolean addDevice(Device device) {
        String macAddress = device.getMacAddress();
        if (macAddress == null)
            return false;
        
        if (deviceRepository.findByMacAddress(macAddress) != null)
            return false;
        
        deviceRepository.save(device);
        return true;
    }
    
    public Device findByMacAddress(String macAddress) {
        return deviceRepository.findByMacAddress(macAddress);
    }
    
    public Node getDeviceTopologyForDevice(String macAddress) {
        HashMap<String, ArrayList<String>> orderedHashMap = orderEveryDeviceSingleLevel();
        
        Node node = new Node(macAddress);
        iterateForChild(orderedHashMap, node, macAddress);
        return node;
    }
    
    public  void iterateForChild(HashMap<String, ArrayList<String>> hashMap, Node node, String macAddress) {
        List<String> nextMacAddressList = new ArrayList<>();
        for (String key : hashMap.keySet()) {
            if (key.equals(macAddress)) {
                node.addChild(hashMap.get(key));
                node.getChildNodes().forEach(childNode -> nextMacAddressList.add(childNode.getValue()));
                break;
            }
        }
        
        if (nextMacAddressList.size() == 0)
            return;
        
        for (String nextMacAddress : nextMacAddressList)
            iterateForChild(hashMap, node.getChildNodeWithValue(nextMacAddress), nextMacAddress);
    }
    
    public HashMap<String, ArrayList<String>> orderEveryDeviceSingleLevel() {
        List<Device> deviceList = deviceRepository.findAll();
        HashMap<String, ArrayList<String>> topologyMap = new HashMap<>();
        Set<String> keySet;
        String macAddress;
        String uplinkMacAddress;
        ArrayList<String> initList;
        for (Device device : deviceList) {
            macAddress = device.getMacAddress();
            uplinkMacAddress = device.getUplinkMacAddress();
            if (uplinkMacAddress == null)
                uplinkMacAddress = "root";
            
            keySet = topologyMap.keySet();
            if (keySet.contains(uplinkMacAddress)) {
                topologyMap.get(uplinkMacAddress).add(macAddress);
            } else {
                initList = new ArrayList<>();
                initList.add(macAddress);
                topologyMap.put(uplinkMacAddress, initList);
            }
        }
        
        return topologyMap;
    }
}
