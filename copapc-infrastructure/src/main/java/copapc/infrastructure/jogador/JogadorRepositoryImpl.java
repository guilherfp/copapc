package copapc.infrastructure.jogador;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.time.Time;

public class JogadorRepositoryImpl extends HibernateRepository implements JogadorRepository {

  public JogadorRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogador> jogadores() {
    return getSession().createCriteria(Jogador.class).list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Jogador> jogadoresDoTime(Time time) {
    final Query query = getSession().createQuery("from Jogador where time = :time");
    query.setParameter("time", time);
    return query.list();
  }

  @Override
  public void atualizar(Jogador jogador) {
    getSession().update(jogador);
  }

}
