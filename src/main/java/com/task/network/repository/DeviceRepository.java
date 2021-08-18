package com.task.network.repository;

import com.task.network.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT d FROM Device d " +
            " ORDER BY " +
            " (CASE WHEN (d.deviceType = 'GATEWAY') THEN 1 " +
            " WHEN (d.deviceType = 'SWITCH') THEN 2 " +
            " WHEN (d.deviceType = 'ACCESS_POINT') THEN 3 " +
            " ELSE 4 END)")
    List<Device> findAllSortedByDeviceType();
    
    Device findByMacAddress(String macAddress);
    
}
