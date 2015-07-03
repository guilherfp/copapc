package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("homeMB")
public class HomeManagedBean extends AbstractManagedBean {
  private static final long serialVersionUID = 1L;

  @Autowired
  private TimeRepository timeRepository;
  @Autowired
  private JogoRepository jogoRepository;

  private List<Jogo> proximos;
  private List<Jogo> ultimos;
  private List<Time> times;

  @Transactional
  public List<Time> getTimes() {
    if (times == null) {
      times = timeRepository.times();
    }
    return times;
  }

  public List<Jogo> getProximos() {
    if (proximos == null) {
      proximos = jogoRepository.jogosEmAberto();
    }
    return proximos;
  }

  public List<Jogo> getUltimos() {
    if (ultimos == null) {
      ultimos = jogoRepository.ultimosEncerrados();
    }
    return ultimos;
  }

  public int getProximosSize() {
    int size = getProximos().size();
    return size <= 3 ? size : 3;
  }

  public int getUltimosSize() {
    int size = getUltimos().size();
    return size <= 3 ? size : 3;
  }

  public String getImageHome() {
    return getResource("/resources/images/banner_copapc.jpg");
  }

}
