package copapc.infrastructure.shared;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Guilherme Pacheco
 */
public class HibernateRepository {

  private final SessionFactory factory;

  public HibernateRepository(SessionFactory factory) {
    this.factory = factory;
  }

  protected final Session getSession() {
    return factory.getCurrentSession();
  }

  protected final Query query(String queryString) {
    return getSession().createQuery(queryString);
  }

}
