package copapc.service.classificacao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.resumoclassificacao.Classificacao;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;
import copapc.service.jogo.JogoService;

/**
 * @author Guilherme Pacheco
 */
@Service
public class ClassificacaoService {

  private final JogoRepository jogoRepository;
  private final TimeRepository timeRepository;
  private final JogoService jogoService;
  private Predicate<Jogo> jogoEncerrado = j -> j.isEncerrado();

  @Autowired
  public ClassificacaoService(JogoRepository jogoRepository, TimeRepository timeRepository,
                              JogoService jogoService)
  {
    this.jogoRepository = jogoRepository;
    this.timeRepository = timeRepository;
    this.jogoService = jogoService;
  }

  public List<Classificacao> classificacaoFase1PorGrupo(char grupo) {
    List<Time> times = timeRepository.timesPorGrupo(grupo);
    return classificar(times.stream().map(t -> classificacao(t, 1)).collect(Collectors.toList()));
  }

  public List<Classificacao> classificacaoGrupoAFase2() {
    List<Time> times = timesFase2GrupoA();
    if (jogoService.faseFinalizada(1)) {
      return classificar(times.stream().map(t -> classificacao(t, 2)).collect(Collectors.toList()));
    } else {
      return Collections.emptyList();
    }
  }

  public List<Classificacao> classificacaoGrupoBFase2() {
    List<Time> times = timesFase2GrupoB();
    if (jogoService.faseFinalizada(1)) {
      return classificar(times.stream().map(t -> classificacao(t, 2)).collect(Collectors.toList()));
    } else {
      return Collections.emptyList();
    }
  }

  private List<Time> timesFase2GrupoA() {
    List<Classificacao> fase1GrupoA = classificacaoFase1PorGrupo('A');
    int endIndex = fase1GrupoA.size() >= 4 ? 4 : 0;
    return fase1GrupoA.subList(0, endIndex).stream().map(Classificacao::getTime).collect(
      Collectors.toList());
  }

  private List<Time> timesFase2GrupoB() {
    List<Classificacao> fase1GrupoB = classificacaoFase1PorGrupo('B');
    int endIndex = fase1GrupoB.size() >= 4 ? 4 : 0;
    return fase1GrupoB.subList(0, endIndex).stream().map(Classificacao::getTime).collect(
      Collectors.toList());
  }

  private List<Classificacao> classificar(List<Classificacao> classificacoes) {
    classificacoes.sort(Comparator.comparingInt(Classificacao::getSaldoDeGols).reversed());
    classificacoes.sort(Comparator.comparingInt(Classificacao::getVitorias).reversed());
    classificacoes.sort(Comparator.comparingInt(Classificacao::getPontos).reversed());
    int posicao = 1;
    for (Classificacao classificacao : classificacoes) {
      classificacao.setPosicao(posicao++);
    }
    return classificacoes;
  }

  private Classificacao classificacao(Time time, int fase) {
    Predicate<Jogo> mesmaFase = f -> f.getFase() == fase;
    List<Jogo> jogosDoTime = jogoRepository.jogos(time);
    List<Jogo> jogos = jogosDoTime.stream().filter(mesmaFase).collect(Collectors.toList());
    int vitorias = vitorias(time, jogos);
    int empates = empates(time, jogos);
    int derrotas = derrotas(time, jogos);
    int golsPros = golsPros(time, jogos);
    int golsContra = golsContra(time, jogos);
    return new Classificacao(time, vitorias, empates, derrotas, golsPros, golsContra);
  }

  private int vitorias(Time time, List<Jogo> jogos) {
    return (int) jogos.stream().filter(jogoEncerrado).filter(j -> j.isVencedor(time)).count();
  }

  private int empates(Time time, List<Jogo> jogos) {
    return (int) jogos.stream().filter(jogoEncerrado).filter(Jogo::isEmpate).count();
  }

  private int derrotas(Time time, List<Jogo> jogos) {
    return (int) jogos.stream().filter(jogoEncerrado).filter(j -> j.isDerrotado(time)).count();
  }

  private int golsPros(Time time, List<Jogo> jogos) {
    return jogos.stream().mapToInt(j -> j.getGols(time)).sum();
  }

  private int golsContra(Time time, List<Jogo> jogos) {
    return jogos.stream().mapToInt(j -> j.getGolsContra(time)).sum();
  }

  public Jogo getPrimeiroJogoSemiFinal() {
    List<Jogo> jogos = jogoRepository.jogosPorFase(3);
    return jogos.stream().min(Comparator.comparingInt(Jogo::getNumero)).orElse(null);
  }

  public Jogo getSegundoJogoSemiFinal() {
    List<Jogo> jogos = jogoRepository.jogosPorFase(3);
    return jogos.stream().max(Comparator.comparingInt(Jogo::getNumero)).orElse(null);
  }

  public Jogo getPrimeiroJogoFinal() {
    List<Jogo> jogos = jogoRepository.jogosPorFase(4);
    return jogos.stream().min(Comparator.comparingInt(Jogo::getNumero)).orElse(null);
  }

  public Jogo getSegundoJogoFinal() {
    List<Jogo> jogos = jogoRepository.jogosPorFase(4);
    return jogos.stream().max(Comparator.comparingInt(Jogo::getNumero)).orElse(null);
  }
}
