package com.robine.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "signals")
public class Signal {

  // could also use nodeId as unique identifier
  @Id
  private String Id;

  private String nodeId;
  private long samplingIntervalMs;
  private String deadbandValue;
  private String deadbandType;
  // gives back 0 or 1, could also be a boolean
  private String active;
  // could also be handled as a array of strings
  private String keywords;

  public Signal(){

  }

  public Signal(String nodeId, long samplingIntervalMs, String deadbandValue, String deadbandType, String active, String keywords) {
    this.nodeId = nodeId;
    this.samplingIntervalMs = samplingIntervalMs;
    this.deadbandValue = deadbandValue;
    this.deadbandType = deadbandType;
    this.active = active;
    this.keywords = keywords;
  }

  @Override
  public String toString() {
    return "Signal [nodeId=" + nodeId + ", deadbandValue=" + deadbandValue + ", deadbandType=" + deadbandType + ", active=" + active + ", keywords=" + keywords + "]";
  }

  public String getId() {
    return Id;
  }

  public long getSamplingIntervalMs() {
    return samplingIntervalMs;
  }

  public void setSamplingIntervalMs(long samplingIntervalMs) {
    this.samplingIntervalMs = samplingIntervalMs;
  }

  public String getDeadbandValue() {
    return deadbandValue;
  }

  public void setDeadbandValue(String deadbandValue) {
    this.deadbandValue = deadbandValue;
  }

  public String getDeadbandType() {
    return deadbandType;
  }

  public void setDeadbandType(String deadbandType) {
    this.deadbandType = deadbandType;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }
}
