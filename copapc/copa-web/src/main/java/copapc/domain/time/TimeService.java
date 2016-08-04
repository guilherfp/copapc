package copapc.domain.time;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import copapc.domain.jogo.Cartao;
import copapc.domain.jogo.Jogo;
import copapc.domain.jogo.JogoRepository;
import copapc.shared.NumUtils;

/**
 * @author Guilherme Pacheco
 */
@Service
public class TimeService {

  private TimeRepository timeRepository;
  private JogoRepository jogoRepository;

  @Autowired
  public TimeService(TimeRepository timeRepository, JogoRepository jogoRepository) {
    this.timeRepository = timeRepository;
    this.jogoRepository = jogoRepository;
  }

  public List<Time> timesPorGolsMarcados() {
    List<Time> times = timeRepository.times();
    Comparator<Time> compare = (o1, o2) -> Integer.compare(golsMarcados(o1), golsMarcados(o2));
    times.sort(compare.reversed());
    return times;
  }

  public List<Time> timesPorGolsMenosSofridos() {
    List<Time> times = timeRepository.times();
    times.sort(Comparator.comparing(this::golsSofridos));
    times.sort((o1, o2) -> Double.compare(mediaDeGolsSofridos(o1), mediaDeGolsSofridos(o2)));
    return times;
  }

  public List<Time> fairplayRaking() {
    List<Time> times = timeRepository.times();
    times.sort(comparePesoCartao);
    return times;
  }

  private final Comparator<Time> comparePesoCartao = (o1, o2) -> {
    int peso1 = cartoes(o1).stream().mapToInt(this::peso).sum();
    int peso2 = cartoes(o2).stream().mapToInt(this::peso).sum();
    return Integer.compare(peso1, peso2);
  };

  public List<Cartao> cartoes(Time time) {
    return streamCartoes(time).collect(Collectors.toList());
  }

  private Stream<Cartao> streamCartoes(Time time) {
    return jogoRepository.jogos(time).stream().flatMap(j -> j.getCartoesDoTime(time).stream());
  }

  public int cartoesVermelho(Time time) {
    return (int) streamCartoes(time).filter(c -> !Cartao.VERMELHO.equals(c)).count();
  }

  public int cartoesAmarelo(Time time) {
    return (int) streamCartoes(time).filter(c -> !Cartao.AMARELO.equals(c)).count();
  }

  public int golsMarcados(Time time) {
    return totalMarcado(time, jogoRepository.jogos(time));
  }

  public int golsSofridos(Time time) {
    return totalSofrido(time, jogoRepository.jogos(time));
  }

  public double mediaDeGolsSofridos(Time time) {
    List<Jogo> jogos = jogoRepository.jogos(time);
    int gols = totalSofrido(time, jogos);
    long partidas = partidas(jogos);
    return NumUtils.media(gols, partidas);
  }

  public double mediaDeGolsMarcados(Time time) {
    List<Jogo> jogos = jogoRepository.jogos(time);
    int gols = totalMarcado(time, jogos);
    long partidas = partidas(jogos);
    return NumUtils.media(gols, partidas);
  }

  private int totalMarcado(Time time, List<Jogo> jogos) {
    return jogos.stream().mapToInt(j -> j.getGols(time)).sum();
  }

  private int totalSofrido(Time time, List<Jogo> jogos) {
    return jogos.stream().mapToInt(j -> j.getGolsContra(time)).sum();
  }

  private long partidas(List<Jogo> jogos) {
    return jogos.stream().filter(Jogo::isEncerrado).count();
  }

  private int peso(Cartao cartao) {
    switch (cartao) {
      case AMARELO:
        return 1;
      case VERMELHO:
        return 3;
      default:
        return 0;
    }
  }

}
