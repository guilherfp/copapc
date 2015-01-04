package copapc.service.classificacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.resumoclassificacao.Classificacao;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

public class ClassificacaoService {

  private final JogoRepository jogoRepository;
  private final TimeRepository timeRepository;

  public ClassificacaoService(JogoRepository jogoRepository, TimeRepository timeRepository) {
    this.jogoRepository = jogoRepository;
    this.timeRepository = timeRepository;
  }

  public List<Classificacao> classificacaoGeral() {
    final List<Time> times = timeRepository.times();
    return classificar(times.stream().map(this::classificacao).collect(Collectors.toList()));
  }

  public List<Classificacao> classificacao(final int fase, final char grupo) {
    List<Time> times = timeRepository.timesPorGrupo(fase, grupo);
    if (times == null) {
      times = new ArrayList<>();
    }
    return classificar(times.stream().map(this::classificacao).collect(Collectors.toList()));
  }

  private List<Classificacao> classificar(final List<Classificacao> classificacoes) {
    classificacoes.sort(Comparator.comparingInt(Classificacao::getGolsPros).reversed());
    classificacoes.sort(Comparator.comparingInt(Classificacao::getPontos).reversed());
    int posicao = 1;
    for (Classificacao classificacao : classificacoes) {
      classificacao.setPosicao(posicao++);
    }
    return classificacoes;
  }

  private Classificacao classificacao(Time time) {
    final List<Jogo> jogos = jogoRepository.jogos(time);
    final int vitorias = vitorias(time, jogos);
    final int empates = empates(time, jogos);
    final int derrotas = derrotas(time, jogos);
    final int golsPros = golsPros(time, jogos);
    final int golsContra = golsContra(time, jogos);
    return new Classificacao(0, time.getNome(), vitorias, empates, derrotas, golsPros, golsContra);
  }

  private int vitorias(final Time time, final List<Jogo> jogos) {
    int vitorias = 0;
    for (Jogo jogo : jogos) {
      if (jogo.isEncerrado() && jogo.isVencedor(time)) {
        vitorias++;
      }
    }
    return vitorias;
  }

  private int empates(final Time time, final List<Jogo> jogos) {
    int empates = 0;
    for (Jogo jogo : jogos) {
      if (jogo.isEncerrado() && jogo.isEmpate()) {
        empates++;
      }
    }
    return empates;
  }

  private int derrotas(final Time time, final List<Jogo> jogos) {
    int derrotas = 0;
    for (Jogo jogo : jogos) {
      if (jogo.isEncerrado() && jogo.isDerrotado(time)) {
        derrotas++;
      }
    }
    return derrotas;
  }

  private int golsPros(final Time time, final List<Jogo> jogos) {
    return jogos.stream().mapToInt(j -> j.getGols(time)).sum();
  }

  private int golsContra(final Time time, final List<Jogo> jogos) {
    return jogos.stream().mapToInt(j -> j.getGolsContra(time)).sum();
  }
}
