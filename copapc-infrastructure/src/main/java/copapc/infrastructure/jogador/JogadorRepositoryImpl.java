package copapc.infrastructure.jogador;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.time.Time;

@Repository
public class JogadorRepositoryImpl extends HibernateRepository implements JogadorRepository {

  public JogadorRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogador> jogadores() {
    final Query query = getSession().createQuery("from Jogador order by nome");
    return query.list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Jogador> jogadoresDoTime(Time time) {
    final Query query = getSession().createQuery("from Jogador where time = :time order by nome");
    query.setParameter("time", time);
    return query.list();
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
    final Query query = getSession().createQuery("from Jogador where url = :url");
    query.setParameter("url", url);
    return (Jogador) query.uniqueResult();
  }

}
