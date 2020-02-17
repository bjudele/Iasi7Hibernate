package com.sda.iasi7.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column
  private String brand;
  @Column
  private String model;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
