package copapc.service.time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import copapc.model.jogador.Cartao;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

public class TimeService {

  private TimeRepository timeRepository;
  private JogoRepository jogoRepository;

  public TimeService(TimeRepository timeRepository, JogoRepository jogoRepository) {
    this.timeRepository = timeRepository;
    this.jogoRepository = jogoRepository;
  }

  public List<Time> getTimesPorGolsMarcados() {
    final List<Time> times = timeRepository.times();
    times.sort(comparePorGolsMarcados.reversed());
    return times;
  }

  public List<Time> getTimesPorGolsMenosSofridos() {
    final List<Time> times = timeRepository.times();
    times.sort(comparePorGolsSofridos);
    return times;
  }

  private final Comparator<Time> comparePorGolsMarcados = new Comparator<Time>() {
    @Override
    public int compare(Time o1, Time o2) {
      return Integer.compare(golsMarcados(o1), golsMarcados(o2));
    }
  };

  private final Comparator<Time> comparePorGolsSofridos = new Comparator<Time>() {
    @Override
    public int compare(Time o1, Time o2) {
      return Integer.compare(golsSofridos(o1), golsSofridos(o2));
    }
  };

  public List<Time> getFairplayRaking() {
    final List<Time> times = timeRepository.times();
    times.sort(comparePesoCartao);
    return times;
  }

  private final Comparator<Time> comparePesoCartao = new Comparator<Time>() {
    @Override
    public int compare(Time o1, Time o2) {
      final int peso1 = cartoes(o1).stream().mapToInt(TimeService.this::peso).sum();
      final int peso2 = cartoes(o2).stream().mapToInt(TimeService.this::peso).sum();
      return Integer.compare(peso1, peso2);
    }
  };

  public List<Cartao> cartoes(Time time) {
    final List<Cartao> cartoes = new ArrayList<>();
    final List<Jogo> jogos1 = jogoRepository.jogos(time);
    jogos1.stream().map(j -> j.getCartoesDoTime(time)).forEach(cartoes::addAll);
    return cartoes;
  }

  public int cartoesVermelho(Time time) {
    final List<Cartao> cartoes = new ArrayList<>();
    final List<Jogo> jogos1 = jogoRepository.jogos(time);
    jogos1.stream().map(j -> j.getCartoesDoTime(time)).forEach(cartoes::addAll);
    cartoes.removeIf(c -> c != Cartao.VERMELHO);
    return cartoes.size();
  }

  public int cartoesAmarelo(Time time) {
    final List<Cartao> cartoes = new ArrayList<>();
    final List<Jogo> jogos1 = jogoRepository.jogos(time);
    jogos1.stream().map(j -> j.getCartoesDoTime(time)).forEach(cartoes::addAll);
    cartoes.removeIf(c -> c != Cartao.AMARELO);
    return cartoes.size();
  }

  public int golsMarcados(Time time) {
    final List<Jogo> jogos = jogoRepository.jogos(time);
    return jogos.stream().mapToInt(j -> j.getGols(time)).sum();
  }

  public int golsSofridos(Time time) {
    final List<Jogo> jogos = jogoRepository.jogos(time);
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
