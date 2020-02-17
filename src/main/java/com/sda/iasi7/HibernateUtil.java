package com.sda.iasi7;

import com.sda.iasi7.entity.Address;
import com.sda.iasi7.entity.Phone;
import com.sda.iasi7.entity.User;
import com.sda.iasi7.entity.Vehicle;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  private static SessionFactory sessionFactory = null;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      Configuration configuration = new Configuration();
      Properties settings = new Properties();
      settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
      settings.put(Environment.URL, "jdbc:mysql://localhost:3306/iasi7examples?useSSL=false");
      settings.put(Environment.USER, "bogdan");
      settings.put(Environment.PASS, "password");
      settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
      settings.put(Environment.SHOW_SQL, "true");
      settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
      settings.put(Environment.HBM2DDL_AUTO, "update");
      configuration.setProperties(settings);

      configuration.addAnnotatedClass(User.class);
      configuration.addAnnotatedClass(Address.class);
      configuration.addAnnotatedClass(Phone.class);
      configuration.addAnnotatedClass(Vehicle.class);

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties()).build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    return sessionFactory;
  }

  public static Session getSession() {
    return getSessionFactory().openSession();
  }
}
