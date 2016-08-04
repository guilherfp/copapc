package copapc.infra.gol;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import copapc.domain.gol.Gol;
import copapc.domain.gol.GolRepository;
import copapc.shared.HibernateRepository;

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
    return ObjectUtils.defaultIfNull(criteria.list(), Collections.emptyList());
  }

  @Override
  public void salvar(Gol gol) {
    getSession().save("Gol", gol);
  }

}
