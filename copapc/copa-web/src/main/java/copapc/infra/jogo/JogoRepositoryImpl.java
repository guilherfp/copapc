package copapc.infra.jogo;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.domain.jogo.CartaoDoJogo;
import copapc.domain.jogo.Jogo;
import copapc.domain.jogo.JogoRepository;
import copapc.domain.time.Time;
import copapc.shared.HibernateRepository;

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
    return nullSafe(query("from Jogo order by horario"));
  }

  @Override
  public List<Jogo> jogos(Time time) {
    Query query = query("from Jogo where mandante = :time or visitante = :time order by horario");
    query.setParameter("time", time);
    return nullSafe(query);
  }

  @Override
  public void atualizar(Jogo jogo) {
    getSession().update("Jogo", jogo);
  }

  @Override
  public List<Jogo> jogosEmAberto() {
    return nullSafe(query("from Jogo where encerramento is null order by horario"));
  }

  @Override
  public List<Jogo> ultimosEncerrados() {
    return nullSafe(query("from Jogo where encerramento is not null order by horario desc"));
  }

  @Override
  public Jogo jogo(int numero) {
    Query query = query("from Jogo where numero = :numero");
    query.setParameter("numero", numero);
    return (Jogo) query.uniqueResult();
  }

  @Override
  public void salvarCartao(CartaoDoJogo cartaoDoJogo) {
    getSession().save("CartaoDoJogo", cartaoDoJogo);
  }

  @Override
  public List<Jogo> jogosPorFase(int fase) {
    Query query = query("from Jogo where fase = :fase order by horario");
    query.setParameter("fase", fase);
    return nullSafe(query);
  }

  private List<Jogo> nullSafe(Query query) {
    return ObjectUtils.defaultIfNull(query.list(), Collections.emptyList());
  }

}
