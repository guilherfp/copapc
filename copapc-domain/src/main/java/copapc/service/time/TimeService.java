package copapc.service.time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import copapc.model.jogo.Cartao;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

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

  public List<Time> getTimesPorGolsMarcados() {
    List<Time> times = timeRepository.times();
    times.sort(comparePorGolsMarcados.reversed());
    return times;
  }

  public List<Time> getTimesPorGolsMenosSofridos() {
    List<Time> times = timeRepository.times();
    times.sort(comparePorGolsSofridos);
    return times;
  }

  private final Comparator<Time> comparePorGolsMarcados = (o1, o2) -> Integer.compare(
    golsMarcados(o1), golsMarcados(o2));

  private final Comparator<Time> comparePorGolsSofridos = (o1, o2) -> Integer.compare(
    golsSofridos(o1), golsSofridos(o2));

  public List<Time> getFairplayRaking() {
    List<Time> times = timeRepository.times();
    times.sort(comparePesoCartao);
    return times;
  }

  private final Comparator<Time> comparePesoCartao = (Time o1, Time o2) -> {
    int peso1 = cartoes(o1).stream().mapToInt(this::peso).sum();
    int peso2 = cartoes(o2).stream().mapToInt(this::peso).sum();
    return Integer.compare(peso1, peso2);
  };

  public List<Cartao> cartoes(Time time) {
    List<Cartao> cartoes = new ArrayList<>();
    List<Jogo> jogos1 = jogoRepository.jogos(time);
    jogos1.stream().map(j -> j.getCartoesDoTime(time)).forEach(cartoes::addAll);
    return cartoes;
  }

  public int cartoesVermelho(Time time) {
    List<Cartao> cartoes = new ArrayList<>();
    List<Jogo> jogos1 = jogoRepository.jogos(time);
    jogos1.stream().map(j -> j.getCartoesDoTime(time)).forEach(cartoes::addAll);
    cartoes.removeIf(c -> c != Cartao.VERMELHO);
    return cartoes.size();
  }

  public int cartoesAmarelo(Time time) {
    List<Cartao> cartoes = new ArrayList<>();
    List<Jogo> jogos1 = jogoRepository.jogos(time);
    jogos1.stream().map(j -> j.getCartoesDoTime(time)).forEach(cartoes::addAll);
    cartoes.removeIf(c -> c != Cartao.AMARELO);
    return cartoes.size();
  }

  public int golsMarcados(Time time) {
    List<Jogo> jogos = jogoRepository.jogos(time);
    return jogos.stream().mapToInt(j -> j.getGols(time)).sum();
  }

  public int golsSofridos(Time time) {
    List<Jogo> jogos = jogoRepository.jogos(time);
    return jogos.stream().mapToInt(j -> j.getGolsContra(time)).sum();
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
