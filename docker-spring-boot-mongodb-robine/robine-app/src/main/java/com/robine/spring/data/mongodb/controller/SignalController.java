package com.robine.spring.data.mongodb.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.robine.spring.data.mongodb.model.Signal;
import com.robine.spring.data.mongodb.repository.SignalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SignalController {

  @Autowired
  SignalRepository signalRepository;

  @Autowired
  public void initDatabase(){

  }

  @PostMapping("/addSignal")
  public String addSignal(@RequestBody Signal signal){
    signalRepository.save(signal);

    return "Added Successfully";
  }

  @GetMapping("/signals")
  public ResponseEntity<List<Signal>> getAllSignals(@RequestParam(required = false) String nodeId) {
    try {
      List<Signal> signals = new ArrayList<Signal>();

      if (nodeId == null)
        signalRepository.findAll().forEach(signals::add);
      else
        signalRepository.findByNodeId(nodeId).forEach(signals::add);
      if (signals.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(signals, HttpStatus.OK);
    } catch (Exception e) {
      System.out.print(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/signals/{id}")
  public ResponseEntity<Signal> getSignalById(@PathVariable("id") String id) {
    Optional<Signal> signalData = signalRepository.findById(id);

    if (signalData.isPresent()) {
      return new ResponseEntity<>(signalData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/signals")
  public ResponseEntity<Signal> createSignal(@RequestBody Signal signal) {
    try {
      // Signal _signal = signalRepository.save(new Signal(signal.getNodeId(),signal.getSamplingIntervalMs(), signal.getDeadbandValue(), signal.getDeadbandType(), signal.getActive(), signal.getKeywords()));
      // return new ResponseEntity<>(_signal, HttpStatus.CREATED);
      return null;
    } catch (Exception e) {
      System.out.print(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/signals/{id}")
  public ResponseEntity<Signal> updateSignal(@PathVariable("id") String id, @RequestBody Signal signal) {
    Optional<Signal> signalData = signalRepository.findById(id);

    if (signalData.isPresent()) {
      Signal _signal = signalData.get();
      _signal.setNodeId((signal.getNodeId()));
      _signal.setSamplingIntervalMs(signal.getSamplingIntervalMs());
      _signal.setDeadbandValue(signal.getDeadbandValue());
      _signal.setDeadbandType(signal.getDeadbandType());
      _signal.setActive(signal.getActive());
      _signal.setKeywords(signal.getKeywords());
      return new ResponseEntity<>(signalRepository.save(_signal), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/signals/{id}")
  public ResponseEntity<HttpStatus> deleteSignal(@PathVariable("id") String id) {
    try {
      signalRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/signals")
  public ResponseEntity<HttpStatus> deleteAllSignals() {
    try {
      signalRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
