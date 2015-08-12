package copapc.model.jogo;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;

import copapc.model.gol.Gol;
import copapc.model.jogador.Jogador;
import copapc.model.time.Time;
import copapc.shared.Entity;

/**
 * @author Guilherme Pacheco
 */
public class Jogo extends Entity implements Comparable<Jogo> {
  private static final long serialVersionUID = 1L;

  private int fase;
  private int numero;
  private DateTime inicio;
  private DateTime encerramento;
  private DateTime horario;
  private Time mandante;
  private Time visitante;
  private List<Gol> gols = new ArrayList<>();
  private List<CartaoDoJogo> cartoesDoJogo = new ArrayList<>();
  private boolean segundoTempo = false;

  Jogo() {
    super();
  }

  public Jogo(int numero, Time mandante, Time visitante) {
    setVisitante(visitante);
    setMandante(mandante);
    this.numero = numero;
  }

  public int getNumero() {
    return numero;
  }

  public void iniciar() {
    inicio = DateTime.now();
  }

  public void iniciarNoHorario() {
    inicio = horario;
  }

  public void encerrar() {
    encerramento = DateTime.now();
  }

  public DateTime getInicio() {
    return inicio;
  }

  public DateTime getEncerramento() {
    return encerramento;
  }

  public boolean isEncerrado() {
    return encerramento != null;
  }

  public boolean isIniciado() {
    return inicio != null;
  }

  public boolean isAndamento() {
    return inicio != null && encerramento == null;
  }

  public int getFase() {
    return fase;
  }

  public void setFase(int fase) {
    this.fase = fase;
  }

  public void iniciarSegundoTempo() {
    segundoTempo = true;
  }

  public boolean isSegundoTempo() {
    return segundoTempo;
  }

  public Gol adicionarGol(Gol gol) {
    Validate.isTrue(!isEncerrado(), "Jogo já foi encerrado");
    gols.add(gol);
    return gol;
  }

  public Time getVencedor() {
    int golsMandante = getTotalDeGolsDoMandante();
    int golsVisitante = getTotalDeGolsDoVisitante();
    if (golsMandante > golsVisitante) {
      return mandante;
    } else if (golsVisitante > golsMandante) {
      return visitante;
    } else {
      return null;
    }
  }

  public boolean isVencedor(Time time) {
    return estaNoJogo(time) && time.equals(getVencedor());
  }

  public boolean isDerrotado(Time time) {
    return estaNoJogo(time) && !isEmpate() && !time.equals(getVencedor());
  }

  public boolean isEmpate() {
    return getVencedor() == null;
  }

  public Time getMandante() {
    return mandante;
  }

  public void setMandante(Time mandante) {
    Validate.notNull(mandante, "Time mandante inválido");
    Validate.isTrue(!mandante.equals(visitante), "Time inválido");
    this.mandante = mandante;
  }

  public Time getVisitante() {
    return visitante;
  }

  public void setVisitante(Time visitante) {
    Validate.notNull(visitante, "Time visitante inválido");
    Validate.isTrue(!visitante.equals(mandante), "Time inválido");
    this.visitante = visitante;
  }

  public DateTime getHorario() {
    return horario;
  }

  public void setHorario(DateTime horario) {
    Validate.notNull(horario, "Horário do jogo inválido");
    this.horario = horario;
  }

  public int getTotalDeGolsDoMandante() {
    return (int) gols.stream().filter(g -> {
      if (g.isContra() && g.getTime().equals(visitante)) {
        return true;
      } else {
        return !g.isContra() && g.getTime().equals(mandante);
      }
    }).count();
  }

  public int getTotalDeGolsDoVisitante() {
    return (int) gols.stream().filter(g -> {
      if (g.isContra() && g.getTime().equals(mandante)) {
        return true;
      } else {
        return !g.isContra() && g.getTime().equals(visitante);
      }
    }).count();
  }

  public List<Gol> getGols() {
    Comparator<Gol> sortPorMinuto = Comparator.comparingInt(Gol::getMinuto);
    return gols.stream().sorted(sortPorMinuto).collect(Collectors.toList());
  }

  public int getTotalDeGols() {
    return gols.size();
  }

  public int getGols(Time time) {
    if (mandante.equals(time)) {
      return getTotalDeGolsDoMandante();
    } else if (visitante.equals(time)) {
      return getTotalDeGolsDoVisitante();
    } else {
      return 0;
    }
  }

  public int getGolsContra(Time time) {
    if (estaNoJogo(time)) {
      if (mandante.equals(time)) {
        return getTotalDeGolsDoVisitante();
      } else {
        return getTotalDeGolsDoMandante();
      }
    } else {
      return 0;
    }
  }

  public Entry<Integer, Integer> getPlacar(Gol gol) {
    int golsMandante = 0;
    int golsVisitante = 0;
    final List<Gol> gols = golsAPartirDoMinuto(gol.getMinuto());
    for (Gol golMarcado : gols) {
      if (golMarcado.isAFavorDe(mandante)) {
        golsMandante++;
      } else if (golMarcado.isAFavorDe(visitante)) {
        golsVisitante++;
      }
    }
    return new AbstractMap.SimpleEntry<>(golsMandante, golsVisitante);
  }

  private List<Gol> golsAPartirDoMinuto(int minuto) {
    Predicate<Gol> minutoInferiorOuIgual = g -> g.getMinuto() <= minuto;
    return getGols().stream().filter(minutoInferiorOuIgual).collect(Collectors.toList());
  }

  public boolean estaNoJogo(Time time) {
    return visitante.equals(time) || mandante.equals(time);
  }

  public List<Jogador> getJogadores() {
    List<Jogador> jogadores = new ArrayList<>();
    jogadores.addAll(mandante.getJogadores());
    jogadores.addAll(visitante.getJogadores());
    return jogadores;
  }

  public List<CartaoDoJogo> getCartoesDoJogo() {
    cartoesDoJogo.sort(Comparator.comparingInt(CartaoDoJogo::getMinuto));
    return cartoesDoJogo;
  }

  public List<Cartao> getCartoesDoJogador(Jogador jogador) {
    Predicate<CartaoDoJogo> mesmoJogador = c -> c.getJogador().equals(jogador);
    Stream<CartaoDoJogo> cartoesDoJogador = cartoesDoJogo.stream().filter(mesmoJogador);
    return cartoesDoJogador.map(CartaoDoJogo::getCartao).collect(Collectors.toList());
  }

  public List<Cartao> getCartoesDoTime(Time time) {
    Predicate<CartaoDoJogo> mesmoTime = c -> c.getJogador().getTime().equals(time);
    Stream<CartaoDoJogo> cartoesDoTime = cartoesDoJogo.stream().filter(mesmoTime);
    return cartoesDoTime.map(CartaoDoJogo::getCartao).collect(Collectors.toList());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + numero;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Jogo other = (Jogo) obj;
    if (numero != other.numero) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s X %s, horário: %s", mandante, visitante, getHorarioFormatado());
  }

  public String getHorarioFormatado() {
    if (horario != null) {
      return horario.toString("E dd/MM/yy HH:mm");
    } else {
      return "Não de definido";
    }
  }

  @Override
  public int compareTo(Jogo o) {
    return getHorario().compareTo(o.getHorario());
  }
}
