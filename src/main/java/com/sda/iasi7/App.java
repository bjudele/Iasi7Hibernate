package com.sda.iasi7;

import com.sda.iasi7.entity.Address;
import com.sda.iasi7.entity.Phone;
import com.sda.iasi7.entity.User;
import com.sda.iasi7.entity.Vehicle;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
//    findByUsernameExample();
//    if (true) {
//      return;
//    }
    Scanner scanner = new Scanner(System.in);
    System.out.print("Username: ");
    String username = scanner.next();
    System.out.print("Password: ");
    String password = scanner.next();
    System.out.print("Email: ");
    String email = scanner.next();
    User user = new User(username, password, email);

    Address address = new Address();
    address.setCity("Iasi");
    address.setStreet("Moara de vant");
    address.setNumber(159);

    Phone phone1 = new Phone();
    phone1.setBrand("Iphone");
    phone1.setModel("10S");
    user.addPhone(phone1);
    phone1.setUser(user);

    Phone phone2 = new Phone();
    phone2.setBrand("Samsung");
    phone2.setModel("S de ala scump");
    user.addPhone(phone2);
    phone2.setUser(user);

    user.setAddress(address);
    address.setUser(user);

    Vehicle vehicle1 = new Vehicle();
    vehicle1.setMake("ford");
    vehicle1.setModel("fiesta");

    Vehicle vehicle3 = new Vehicle();
    vehicle3.setMake("audi");
    vehicle3.setModel("s7");

    Vehicle vehicle2 = new Vehicle();
    vehicle2.setMake("bmw");
    vehicle2.setModel("m5");

    Vehicle vehicle4 = new Vehicle();
    vehicle4.setMake("vw");
    vehicle4.setModel("passat");

    User hardcodedUser = new User();
    hardcodedUser.setUsername("boby cel viteaz");
    hardcodedUser.addVehicle(vehicle1, vehicle2, vehicle3);

    user.addVehicle(vehicle4, vehicle2, vehicle3);

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    session.save(user);
    session.save(hardcodedUser);

    session.getTransaction().commit();
    session.close();
  }

  public static void findByUsernameExample() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Username: ");
    String username = scanner.next();

    Session session = HibernateUtil.getSession();

    Query<User> query = session.createQuery("select u from User u where u.username = :username");
    query.setParameter("username", username);
    List<User> resultList = query.getResultList();

    System.out.println(resultList);
  }

}
