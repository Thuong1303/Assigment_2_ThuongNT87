package com.assigment_2_thuongnt87.exception;

public class NotFoundException extends BusinessException {
  public NotFoundException(String message) {
    super("NOT_FOUND", message);
  }
}
