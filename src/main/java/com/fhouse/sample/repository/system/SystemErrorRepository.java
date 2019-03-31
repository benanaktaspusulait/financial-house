package com.fhouse.sample.repository.system;

import com.fhouse.sample.model.error.SystemError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemErrorRepository extends JpaRepository<SystemError, Long> {
}
