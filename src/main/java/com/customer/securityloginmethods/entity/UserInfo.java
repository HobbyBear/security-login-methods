package com.customer.securityloginmethods.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  
}