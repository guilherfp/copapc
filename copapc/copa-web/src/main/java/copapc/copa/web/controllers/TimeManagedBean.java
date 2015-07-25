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
import copapc.service.time.TimeService;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("timeMB")
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
      time = timeRepository.comURL(getURLParameterValue(TIME));
    }
    return time;
  }

  public String getImageTimeUrl() {
    return getResource(String.format("/resources/face/%s.png", getTime().getUrl()));
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

  public int getTotalDeGolsMarcados() {
    return getTimesPorGolsMarcados().stream().mapToInt(this::golsMarcados).sum();
  }

  public int getTotalDeGolsSofridos() {
    return getTimesPorGolsMenosSofridos().stream().mapToInt(this::golsSofridos).sum();
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

  public int posicaoFairPlay(Time time) {
    return getFairplay().indexOf(time) + 1;
  }

  public int posicaoAtaque(Time time) {
    return getTimesPorGolsMarcados().indexOf(time) + 1;
  }

  public int posicaoDefesa(Time time) {
    return getTimesPorGolsMenosSofridos().indexOf(time) + 1;
  }

  public int vermelho(Time time) {
    return timeService.cartoesVermelho(time);
  }

  public int amarelo(Time time) {
    return timeService.cartoesAmarelo(time);
  }

  public int getTotalDeCartoesAmarelos() {
    return getFairplay().stream().mapToInt(this::amarelo).sum();
  }

  public int getTotalDeCartoesVermelhos() {
    return getFairplay().stream().mapToInt(this::vermelho).sum();
  }

  public int golsMarcados(Time time) {
    return timeService.golsMarcados(time);
  }

  public int golsSofridos(Time time) {
    return timeService.golsSofridos(time);
  }

}
