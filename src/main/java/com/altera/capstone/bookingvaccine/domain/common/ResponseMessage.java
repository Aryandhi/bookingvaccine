package com.altera.capstone.bookingvaccine.domain.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMessage {
  private String message;

  public ResponseMessage(String message){
    this.message = message;
  }
}
