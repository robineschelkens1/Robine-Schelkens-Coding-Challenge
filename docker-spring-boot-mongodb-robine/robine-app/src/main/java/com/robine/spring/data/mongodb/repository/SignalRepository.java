package com.robine.spring.data.mongodb.repository;

import java.util.List;
import com.robine.spring.data.mongodb.model.Signal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignalRepository extends MongoRepository<Signal, String> {
  List<Signal> findByNodeId(String nodeId);
}
