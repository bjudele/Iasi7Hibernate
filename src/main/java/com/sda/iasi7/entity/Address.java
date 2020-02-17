package com.sda.iasi7.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
public class Address {
  @Id
  @GeneratedValue
  private int id;
  @Column
  private String city;
  @Column
  private String street;
  @Column
  private int number;

  @OneToOne(mappedBy = "address")
  @ToString.Exclude
  private User user;
}
