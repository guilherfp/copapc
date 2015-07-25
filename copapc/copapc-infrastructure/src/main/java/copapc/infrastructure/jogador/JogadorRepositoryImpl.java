package copapc.infrastructure.jogador;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.time.Time;

/**
 * @author Guilherme Pacheco
 */
@Repository
@SuppressWarnings("unchecked")
public class JogadorRepositoryImpl extends HibernateRepository implements JogadorRepository {

  @Autowired
  public JogadorRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public List<Jogador> jogadores() {
    Query query = getSession().createQuery("from Jogador order by nome");
    return nullSafe(query);
  }

  @Override
  public List<Jogador> jogadoresDoTime(Time time) {
    Query query = getSession().createQuery("from Jogador where time = :time order by nome");
    query.setParameter("time", time);
    return nullSafe(query);
  }

  @Override
  public void atualizar(Jogador jogador) {
    getSession().update("Jogador", jogador);
  }

  @Override
  public void salvar(Jogador jogador) {
    getSession().save("Jogador", jogador);
  }

  @Override
  public Jogador comUrl(String url) {
    Query query = getSession().createQuery("from Jogador where url = :url");
    query.setParameter("url", url);
    return (Jogador) query.uniqueResult();
  }

  @Override
  public Jogador comEmail(String email) {
    Query query = getSession().createQuery("from Jogador where email = :email");
    query.setParameter("email", email);
    return (Jogador) query.uniqueResult();
  }

  private List<Jogador> nullSafe(Query query) {
    return ObjectUtils.defaultIfNull(query.list(), new ArrayList<>());
  }

}
