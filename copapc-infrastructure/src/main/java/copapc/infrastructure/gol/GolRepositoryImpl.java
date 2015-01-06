package copapc.infrastructure.gol;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.gol.Gol;
import copapc.model.gol.GolRepository;

@Repository
public class GolRepositoryImpl extends HibernateRepository implements GolRepository {

  public GolRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Gol> gols() {
    return getSession().createCriteria(Gol.class).list();
  }

  @Override
  public void salvar(Gol gol) {
    getSession().save("Gol", gol);
  }

}
