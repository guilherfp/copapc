package copapc.infra.jogador;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.domain.jogador.Jogador;
import copapc.domain.jogador.JogadorRepository;
import copapc.domain.time.Time;
import copapc.shared.HibernateRepository;

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
    return nullSafe(query("from Jogador order by nome"));
  }

  @Override
  public List<Jogador> jogadoresDoTime(Time time) {
    Query query = query("from Jogador where time = :time order by nome");
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
    Query query = query("from Jogador where url = :url");
    query.setParameter("url", url);
    return (Jogador) query.uniqueResult();
  }

  @Override
  public Jogador comEmail(String email) {
    Query query = query("from Jogador where email = :email");
    query.setParameter("email", email);
    return (Jogador) query.uniqueResult();
  }

  private List<Jogador> nullSafe(Query query) {
    return ObjectUtils.defaultIfNull(query.list(), Collections.emptyList());
  }

}
