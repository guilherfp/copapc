package copapc.infrastructure.time;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;
import copapc.util.DomainUtils;

@Repository
public class TimeRepositoryImpl extends HibernateRepository implements TimeRepository {

  public TimeRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Time> times() {
    final Query query = getSession().createQuery("from Time order by nome");
    return DomainUtils.nullSafe(query.list(), new ArrayList<>());
  }

  @Override
  public Time comNumero(int numero) {
    final Query query = getSession().createQuery("from Time where numero = :numero");
    query.setParameter("numero", numero);
    return (Time) query.uniqueResult();
  }

  @Override
  public Time comURL(String url) {
    final Query query = getSession().createQuery("from Time where url = :url");
    query.setParameter("url", url);
    return (Time) query.uniqueResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Time> timesPorGrupo(char grupo) {
    final Query query = getSession().createQuery("from Time where grupo = :grupo order by nome");
    query.setParameter("grupo", grupo);
    return DomainUtils.nullSafe(query.list(), new ArrayList<>());
  }

  @Override
  public int quantidadeDeGrupos() {
    final List<Time> times = times();
    final Set<Character> grupos = new HashSet<>();
    for (Time time : times) {
      grupos.add(time.getGrupo());
    }
    return times.size() / grupos.size();
  }
}
