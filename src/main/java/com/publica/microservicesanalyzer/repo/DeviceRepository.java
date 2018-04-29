package com.publica.microservicesanalyzer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publica.microservicesanalyzer.model.RegisteredDevice;

@Repository
public interface DeviceRepository extends JpaRepository<RegisteredDevice, String> {

}
