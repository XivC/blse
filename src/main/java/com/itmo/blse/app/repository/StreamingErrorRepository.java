package com.itmo.blse.app.repository;


import com.itmo.blse.app.model.StreamingError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingErrorRepository extends JpaRepository<StreamingError, Long> {


}
