package copapc.infrastructure.gol;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.infrastructure.shared.HibernateRepository;
import copapc.model.gol.Gol;
import copapc.model.gol.GolRepository;

/**
 * @author Guilherme Pacheco
 */
@Repository
@SuppressWarnings("unchecked")
public class GolRepositoryImpl extends HibernateRepository implements GolRepository {

  @Autowired
  public GolRepositoryImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public List<Gol> gols() {
    Criteria criteria = getSession().createCriteria("Gol");
    return ObjectUtils.defaultIfNull(criteria.list(), new ArrayList<>());
  }

  @Override
  public void salvar(Gol gol) {
    getSession().save("Gol", gol);
  }

}
