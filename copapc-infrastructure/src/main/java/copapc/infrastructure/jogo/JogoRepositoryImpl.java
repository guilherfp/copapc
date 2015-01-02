package copapc.infrastructure.jogo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;

@Repository
public class JogoRepositoryImpl extends HibernateRepository implements JogoRepository {

  public JogoRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogo> jogos() {
    return getSession().createCriteria(Jogo.class).list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogo> jogos(Time time) {
    final Query query = getSession().createQuery("from Jogo where mandante = :time or visitante = :time");
    query.setParameter("time", time);
    return query.list();
  }

}
