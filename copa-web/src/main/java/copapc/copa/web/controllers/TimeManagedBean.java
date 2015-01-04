package copapc.copa.web.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

@Scope("request")
@Controller("timeMB")
@ManagedBean(name = "timeMB")
public class TimeManagedBean extends AbstractManagedBean {
  private static final long serialVersionUID = 1L;

  private static final String TIME = "time";
  @Autowired
  private TimeRepository timeRepository;
  @Autowired
  private JogoRepository jogoRepository;

  private Time time;
  private List<Jogo> jogos;
  private List<Time> times;

  @Transactional
  public Time getTime() {
    if (time == null) {
      final String timeUrl = getURLParameterValue(TIME);
      time = timeRepository.comURL(timeUrl);
    }
    return time;
  }

  @Transactional
  public List<Jogo> getJogos() {
    if (jogos == null) {
      jogos = jogoRepository.jogos(getTime());
    }
    return jogos;
  }

  @Transactional
  public List<Time> getTimes() {
    if (times == null) {
      times = timeRepository.times();
    }
    return times;
  }

}
