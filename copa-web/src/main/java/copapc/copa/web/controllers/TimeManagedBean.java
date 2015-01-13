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
import copapc.service.time.TimeService;

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
  @Autowired
  private TimeService timeService;

  private Time time;
  private List<Jogo> jogos;
  private List<Time> times;
  private List<Time> timesPorGolsMarcados;
  private List<Time> timesPorGolsMenosSofridos;
  private List<Time> fairplay;

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

  @Transactional
  public List<Time> getTimesPorGolsMarcados() {
    if (timesPorGolsMarcados == null) {
      timesPorGolsMarcados = timeService.getTimesPorGolsMarcados();
    }
    return timesPorGolsMarcados;
  }

  @Transactional
  public List<Time> getTimesPorGolsMenosSofridos() {
    if (timesPorGolsMenosSofridos == null) {
      timesPorGolsMenosSofridos = timeService.getTimesPorGolsMenosSofridos();
    }
    return timesPorGolsMenosSofridos;
  }

  @Transactional
  public List<Time> getFairplay() {
    if (fairplay == null) {
      fairplay = timeService.getFairplayRaking();
    }
    return fairplay;
  }

  public int vermelho(Time time) {
    return timeService.cartoesVermelho(time);
  }

  public int amarelo(Time time) {
    return timeService.cartoesAmarelo(time);
  }

  public int golsMarcados(Time time) {
    return timeService.golsMarcados(time);
  }

  public int golsSofridos(Time time) {
    return timeService.golsSofridos(time);
  }

}
