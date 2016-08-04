package copapc.web.controllers;

import java.util.List;
import java.util.function.ToIntFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.domain.jogo.Jogo;
import copapc.domain.jogo.JogoRepository;
import copapc.domain.time.Time;
import copapc.domain.time.TimeRepository;
import copapc.domain.time.TimeService;
import copapc.shared.NumUtils;
import copapc.web.shared.Lazy;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("timeMB")
public class TimeBean extends AbstractBean {
  private static final long serialVersionUID = 1L;

  private static final String PARAM_TIME = "time";

  @Autowired
  private TimeRepository timeRepository;
  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private TimeService timeService;

  private final Lazy<Time> time = Lazy.empty();
  private final Lazy<List<Jogo>> jogos = Lazy.empty();
  private final Lazy<List<Time>> times = Lazy.empty();
  private final Lazy<List<Time>> timesPorGolsMarcados = Lazy.empty();
  private final Lazy<List<Time>> timesPorGolsMenosSofridos = Lazy.empty();
  private final Lazy<List<Time>> fairplay = Lazy.empty();
  private final Lazy<Integer> totalDeJogos = Lazy.empty();

  @Transactional
  public Time getTime() {
    return time.get(() -> timeRepository.comUrl(urlValue(PARAM_TIME)));
  }

  public String getImageTimeUrl() {
    return getResource(String.format("/resources/face/%s.png", getTime().getUrl()));
  }

  @Transactional
  public List<Jogo> getJogos() {
    return jogos.get(() -> jogoRepository.jogos(getTime()));
  }

  @Transactional
  public List<Time> getTimes() {
    return times.get(() -> timeRepository.times());
  }

  @Transactional
  public List<Time> getTimesPorGolsMarcados() {
    return timesPorGolsMarcados.get(() -> timeService.timesPorGolsMarcados());
  }

  @Transactional
  public List<Time> getTimesPorGolsMenosSofridos() {
    return timesPorGolsMenosSofridos.get(() -> timeService.timesPorGolsMenosSofridos());
  }

  public int getTotalDeGolsMarcados() {
    return getTimesPorGolsMarcados().stream().mapToInt(this::golsMarcados).sum();
  }

  public int getTotalDeGolsSofridos() {
    return getTimesPorGolsMenosSofridos().stream().mapToInt(this::golsSofridos).sum();
  }

  @Transactional
  public List<Time> getFairplay() {
    return fairplay.get(() -> timeService.fairplayRaking());
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

  public double mediaGolsSofridos(Time time) {
    return timeService.mediaDeGolsSofridos(time);
  }

  @Transactional
  public double getMediaDeGolsSofridos() {
    List<Time> times = getTimesPorGolsMenosSofridos();
    double mediaGols = mediaDeGols(times, this::golsSofridos);
    return NumUtils.media(totalDeJogos(), mediaGols);
  }

  private int totalDeJogos() {
    return totalDeJogos.get(() -> jogoRepository.ultimosEncerrados().size());
  }

  private double mediaDeGols(List<Time> times, ToIntFunction<Time> mapper) {
    return times.stream().mapToInt(mapper).average().getAsDouble();
  }

  public double mediaGolsMarcados(Time time) {
    return timeService.mediaDeGolsMarcados(time);
  }

  @Transactional
  public double getMediaDeGolsMarcados() {
    List<Time> times = getTimesPorGolsMarcados();
    double mediaGols = mediaDeGols(times, this::golsMarcados);
    return NumUtils.media(totalDeJogos(), mediaGols);
  }

}
