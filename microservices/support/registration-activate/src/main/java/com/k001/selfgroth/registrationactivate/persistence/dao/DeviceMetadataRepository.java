package com.k001.selfgroth.registrationactivate.persistence.dao;

import com.k001.selfgroth.registrationactivate.persistence.model.DeviceMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {

    List<DeviceMetadata> findByUserId(Long userId);
}
