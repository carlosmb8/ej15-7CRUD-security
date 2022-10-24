package com.example.ej157crudsecurity.security.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationReq implements Serializable {

  private static final long serialVersionUID = 1L;

  private String usuario;

  private String clave;

  public AuthenticationReq(String usuario, String clave) {
    this.usuario = usuario;
    this.clave = clave;
  }

}
