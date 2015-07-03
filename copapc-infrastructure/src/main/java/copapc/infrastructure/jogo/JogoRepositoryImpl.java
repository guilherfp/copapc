package copapc.infrastructure.jogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.jogo.CartaoDoJogo;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;

/**
 * @author Guilherme Pacheco
 */
@Repository
@SuppressWarnings("unchecked")
public class JogoRepositoryImpl extends HibernateRepository implements JogoRepository {

  @Autowired
  public JogoRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public List<Jogo> jogos() {
    Query query = getSession().createQuery("from Jogo order by horario");
    return nullSafe(query);
  }

  @Override
  public List<Jogo> jogos(Time time) {
    String queryString = "from Jogo where mandante = :time or visitante = :time order by horario";
    Query query = getSession().createQuery(queryString);
    query.setParameter("time", time);
    return nullSafe(query);
  }

  @Override
  public void atualizar(Jogo jogo) {
    getSession().update("Jogo", jogo);
  }

  @Override
  public List<Jogo> jogosEmAberto() {
    String queryString = "from Jogo where encerramento is null order by horario";
    Query query = getSession().createQuery(queryString);
    return nullSafe(query);
  }

  @Override
  public List<Jogo> ultimosEncerrados() {
    String queryString = "from Jogo where encerramento is not null order by horario desc";
    Query query = getSession().createQuery(queryString);
    return nullSafe(query);
  }

  @Override
  public Jogo jogoComNumero(int numero) {
    Query query = getSession().createQuery("from Jogo where numero = :numero");
    query.setParameter("numero", numero);
    return (Jogo) query.uniqueResult();
  }

  @Override
  public void salvarCartao(CartaoDoJogo cartaoDoJogo) {
    getSession().save("CartaoDoJogo", cartaoDoJogo);
  }

  @Override
  public List<Jogo> jogosPorFase(int fase) {
    Query query = getSession().createQuery("from Jogo where fase = :fase order by horario");
    query.setParameter("fase", fase);
    return nullSafe(query);
  }

  private List<Jogo> nullSafe(Query query) {
    return ObjectUtils.defaultIfNull(query.list(), new ArrayList<>());
  }
}
