package com.skala.springbootsample1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  private long id;
  private String name;
  private String email;
}

