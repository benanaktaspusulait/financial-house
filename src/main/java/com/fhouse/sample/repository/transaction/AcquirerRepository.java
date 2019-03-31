package com.fhouse.sample.repository.transaction;

import com.fhouse.sample.model.Acquirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirerRepository extends JpaRepository<Acquirer, Long> {

}
