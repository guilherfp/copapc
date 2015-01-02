package copapc.infrastructure.time;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

@Repository
public class TimeRepositoryImpl extends HibernateRepository implements TimeRepository {

  public TimeRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Time> times() {
    return getSession().createCriteria(Time.class).list();
  }

  @Override
  public Time comNumero(int numero) {
    Query query = getSession().createQuery("from Time where numero = :numero");
    query.setParameter("numero", numero);
    return (Time) query.uniqueResult();
  }

  @Override
  public Time comURL(String url) {
    Query query = getSession().createQuery("from Time where url = :url");
    query.setParameter("url", url);
    return (Time) query.uniqueResult();
  }

}
