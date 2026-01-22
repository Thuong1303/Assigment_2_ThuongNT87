package com.assigment_2_thuongnt87.exception;
public class BadRequestException extends BusinessException {
  public BadRequestException(String code, String message) {
    super(code, message);
  }
}
