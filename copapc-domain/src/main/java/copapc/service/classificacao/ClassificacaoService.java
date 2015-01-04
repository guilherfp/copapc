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

  public List<Classificacao> classificacaoFase1(final char grupo) {
    List<Time> times = timeRepository.timesPorGrupo(grupo);
    if (times == null) {
      times = new ArrayList<>();
    }
    return classificar(times.stream().map(this::classificacao).collect(Collectors.toList()));
  }

  public List<Classificacao> classificacaoFase2GrupoA() {
    final List<Classificacao> classificacoesFase1GrupoA = classificacaoFase1('A');
    final List<Classificacao> classificacoesFase1GrupoB = classificacaoFase1('B');
    final List<Classificacao> classificacoesFase2 = new ArrayList<>(4);
    classificacoesFase2.add(posicao(classificacoesFase1GrupoA, 1));
    classificacoesFase2.add(posicao(classificacoesFase1GrupoB, 1));
    classificacoesFase2.add(posicao(classificacoesFase1GrupoA, 3));
    classificacoesFase2.add(posicao(classificacoesFase1GrupoB, 3));
    return classificacoesFase2;
  }

  public List<Classificacao> classificacaoFase2GrupoB() {
    final List<Classificacao> classificacoesFase1GrupoA = classificacaoFase1('A');
    final List<Classificacao> classificacoesFase1GrupoB = classificacaoFase1('B');
    final List<Classificacao> classificacoesFase2 = new ArrayList<>(4);
    classificacoesFase2.add(posicao(classificacoesFase1GrupoA, 2));
    classificacoesFase2.add(posicao(classificacoesFase1GrupoB, 2));
    classificacoesFase2.add(posicao(classificacoesFase1GrupoA, 4));
    classificacoesFase2.add(posicao(classificacoesFase1GrupoB, 4));
    return classificacoesFase2;
  }

  private Classificacao posicao(final List<Classificacao> classificacoes, final int posicao) {
    return classificacoes.stream().filter(c -> c.getPosicao() == posicao).findFirst().get();
  }

  private List<Classificacao> classificar(final List<Classificacao> classificacoes) {
    classificacoes.sort(Comparator.comparingInt(Classificacao::getSaldoDeGols).reversed());
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
    return new Classificacao(time, vitorias, empates, derrotas, golsPros, golsContra);
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
