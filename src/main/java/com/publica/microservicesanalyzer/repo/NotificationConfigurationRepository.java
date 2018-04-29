package com.publica.microservicesanalyzer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publica.microservicesanalyzer.model.NotificationConfiguration;

@Repository
public interface NotificationConfigurationRepository extends JpaRepository<NotificationConfiguration, String> {

}
