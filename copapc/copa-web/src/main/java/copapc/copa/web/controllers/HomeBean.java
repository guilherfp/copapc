package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.copa.web.shared.Lazy;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("homeMB")
public class HomeBean extends AbstractBean {
  private static final long serialVersionUID = 1L;

  private static final int TIME_EXIBICAO = 3;

  @Autowired
  private TimeRepository timeRepository;
  @Autowired
  private JogoRepository jogoRepository;

  private final Lazy<List<Jogo>> proximos = Lazy.empty();
  private final Lazy<List<Jogo>> ultimos = Lazy.empty();
  private final Lazy<List<Time>> times = Lazy.empty();

  @Transactional
  public List<Time> getTimes() {
    return times.get(() -> timeRepository.times());
  }

  public List<Jogo> getProximos() {
    return proximos.get(() -> jogoRepository.jogosEmAberto());
  }

  public List<Jogo> getUltimos() {
    return ultimos.get(() -> jogoRepository.ultimosEncerrados());
  }

  public int getProximosSize() {
    return size(getProximos());
  }

  public int getUltimosSize() {
    return size(getUltimos());
  }

  private int size(List<Jogo> list) {
    int size = list.size();
    return size <= TIME_EXIBICAO ? size : TIME_EXIBICAO;
  }

  public String getImageHome() {
    return getResource("/resources/images/banner_copapc.jpg");

  }

}
