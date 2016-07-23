package copapc.infrastructure.time;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

/**
 * @author Guilherme Pacheco
 */
@Repository
@SuppressWarnings("unchecked")
public class TimeRepositoryImpl extends HibernateRepository implements TimeRepository {

  @Autowired
  public TimeRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public List<Time> times() {
    return nullSafe(query("from Time order by nome"));
  }

  @Override
  public Time comNumero(int numero) {
    Query query = query("from Time where numero = :numero");
    query.setParameter("numero", numero);
    return (Time) query.uniqueResult();
  }

  @Override
  public Time comUrl(String url) {
    Query query = query("from Time where url = :url");
    query.setParameter("url", url);
    return (Time) query.uniqueResult();
  }

  @Override
  public List<Time> timesPorGrupo(char grupo) {
    Query query = query("from Time where grupo = :grupo order by nome");
    query.setParameter("grupo", grupo);
    return nullSafe(query);
  }

  @Override
  public int quantidadeDeGrupos() {
    int grupos = (int) times().stream().map(Time::getGrupo).distinct().count();
    return grupos > 0 ? times().size() / grupos : 0;
  }

  private List<Time> nullSafe(Query query) {
    return ObjectUtils.defaultIfNull(query.list(), Collections.emptyList());
  }
}
