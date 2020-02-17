package com.sda.iasi7.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column
  private String make;
  @Column
  private String model;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_vehicle",
      joinColumns = @JoinColumn(name = "vehicle_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> users = new HashSet<>();

  public void addUser(User user) {
    users.add(user);
  }
}
