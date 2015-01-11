package copapc.service.classificacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.resumoclassificacao.Classificacao;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;
import copapc.service.jogo.JogoService;

public class ClassificacaoService {

  private final JogoRepository jogoRepository;
  private final TimeRepository timeRepository;
  private final JogoService jogoService;

  public ClassificacaoService(JogoRepository jogoRepository, TimeRepository timeRepository, JogoService jogoService) {
    this.jogoRepository = jogoRepository;
    this.timeRepository = timeRepository;
    this.jogoService = jogoService;
  }

  public List<Classificacao> classificacaoFase1PorGrupo(final char grupo) {
    List<Time> times = timeRepository.timesPorGrupo(grupo);
    if (times == null) {
      times = new ArrayList<>();
    }
    return classificar(times.stream().map(t -> classificacao(t, 1)).collect(Collectors.toList()));
  }

  public List<Classificacao> classificacaoGrupoAFase2() {
    final List<Time> times = timesFase2GrupoA();
    return classificar(times.stream().map(t -> classificacao(t, 2)).collect(Collectors.toList()));
  }

  public List<Classificacao> classificacaoGrupoBFase2() {
    final List<Time> times = timesFase2GrupoB();
    return classificar(times.stream().map(t -> classificacao(t, 2)).collect(Collectors.toList()));
  }

  private List<Time> timesFase2GrupoA() {
    final List<Time> times = new ArrayList<>(4);
    if (jogoService.faseFinalizada(1)) {
      final List<Classificacao> classificacoesGrupoAFase1 = classificacaoFase1PorGrupo('A');
      final List<Classificacao> classificacoesGrupoBFase1 = classificacaoFase1PorGrupo('B');
      times.add(posicao(classificacoesGrupoAFase1, 1).getTime());
      times.add(posicao(classificacoesGrupoBFase1, 1).getTime());
      times.add(posicao(classificacoesGrupoAFase1, 3).getTime());
      times.add(posicao(classificacoesGrupoBFase1, 3).getTime());
    }
    return times;
  }

  private List<Time> timesFase2GrupoB() {
    final List<Time> times = new ArrayList<>(4);
    if (jogoService.faseFinalizada(1)) {
      final List<Classificacao> classificacoesGrupoAFase1 = classificacaoFase1PorGrupo('A');
      final List<Classificacao> classificacoesGrupoBFase1 = classificacaoFase1PorGrupo('B');
      times.add(posicao(classificacoesGrupoAFase1, 2).getTime());
      times.add(posicao(classificacoesGrupoBFase1, 2).getTime());
      times.add(posicao(classificacoesGrupoAFase1, 4).getTime());
      times.add(posicao(classificacoesGrupoBFase1, 4).getTime());
    }
    return times;
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

  private Classificacao classificacao(Time time, int fase) {
    final Predicate<? super Jogo> mesmaFase = f -> f.getFase() == fase;
    final List<Jogo> jogos = jogoRepository.jogos(time).stream().filter(mesmaFase).collect(Collectors.toList());
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
