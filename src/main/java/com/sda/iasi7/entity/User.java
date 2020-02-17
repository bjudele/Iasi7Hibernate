package com.sda.iasi7.entity;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column
  private String username;
  @Column
  private String password;
  @Column
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Phone> phones;

  @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
  private Set<Vehicle> vehicles = new HashSet<>();

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public void addPhone(Phone phone) {
    if (phones == null) { //lazy
      phones = new ArrayList<>();
    }
    phones.add(phone);
  }

  public void addVehicle(Vehicle... vehicles) {
    for (int index = 0; index < vehicles.length; index++) {
      Vehicle currentVehicle = vehicles[index];

      this.vehicles.add(currentVehicle);//il adaugam in lista
      //ne adaugam si noi,
      // ca useri, in lista de user a masinii
      currentVehicle.addUser(this);
    }
  }
}
