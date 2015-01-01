package copapc.infrastructure.shared;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRepository {

  private final SessionFactory factory;

  public HibernateRepository(SessionFactory factory) {
    this.factory = factory;
  }

  protected Session getSession() {
    return factory.getCurrentSession();
  }

}
