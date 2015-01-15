package copapc.infrastructure.jogo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.jogo.CartaoDoJogo;
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
    final Query query = getSession().createQuery("from Jogo order by horario");
    return query.list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogo> jogos(Time time) {
    final String queryString = "from Jogo where mandante = :time or visitante = :time order by horario";
    final Query query = getSession().createQuery(queryString);
    query.setParameter("time", time);
    return query.list();
  }

  @Override
  public void atualizar(Jogo jogo) {
    getSession().update("Jogo", jogo);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogo> jogosEmAberto() {
    final Query query = getSession().createQuery("from Jogo where encerramento is null order by horario");
    return query.list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogo> ultimosEncerrados() {
    final Query query = getSession().createQuery("from Jogo where encerramento is not null order by horario desc");
    return query.list();
  }

  @Override
  public Jogo jogoComNumero(int numero) {
    final Query query = getSession().createQuery("from Jogo where numero = :numero");
    query.setParameter("numero", numero);
    return (Jogo) query.uniqueResult();
  }

  @Override
  public void salvarCartao(CartaoDoJogo cartaoDoJogo) {
    getSession().save("CartaoDoJogo", cartaoDoJogo);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Jogo> jogosPorFase(int fase) {
    final Query query = getSession().createQuery("from Jogo where fase = :fase order by horario");
    query.setParameter("fase", fase);
    return query.list();
  }
}
