package copapc.model.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;

import copapc.model.gol.Gol;
import copapc.model.jogador.Cartao;
import copapc.model.jogador.Jogador;
import copapc.model.time.Time;
import copapc.shared.Entity;

public class Jogo extends Entity {
  private static final long serialVersionUID = 1L;

  private int numero;
  private DateTime inicio;
  private DateTime encerramento;
  private DateTime horario;
  private Time mandante;
  private Time visitante;
  private List<Gol> gols = new ArrayList<>();
  private List<CartaoDoJogo> cartoesDoJogo = new ArrayList<>();

  Jogo() {}

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

  public Gol adicionarGol(Gol gol) {
    Validate.isTrue(isEncerrado() == false, "Jogo já foi encerrado");
    gols.add(gol);
    return gol;
  }

  public Time getVencedor() {
    final int golsMandante = getTotalDeGolsDoMandante();
    final int golsVisitante = getTotalDeGolsDoVisitante();
    if (golsMandante > golsVisitante) {
      return mandante;
    } else if (golsVisitante > golsMandante) {
      return visitante;
    } else {
      return null;
    }
  }

  public boolean isVencedor(Time time) {
    return (estaNoJogo(time) == true) && time.equals(getVencedor());
  }

  public boolean isDerrotado(Time time) {
    return (estaNoJogo(time) == true) && (isEmpate() == false) && (time.equals(getVencedor()) == false);
  }

  public boolean isEmpate() {
    return getVencedor() == null;
  }

  public Time getMandante() {
    return mandante;
  }

  public void setMandante(Time mandante) {
    Validate.notNull(mandante, "Time mandante inválido");
    Validate.isTrue(mandante.equals(visitante) == false, "Time inválido");
    this.mandante = mandante;
  }

  public Time getVisitante() {
    return visitante;
  }

  public void setVisitante(Time visitante) {
    Validate.notNull(visitante, "Time visitante inválido");
    Validate.isTrue(visitante.equals(mandante) == false, "Time inválido");
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
      if ((g.isContra() == true) && g.getTime().equals(visitante)) {
        return true;
      } else {
        return (g.isContra() == false) && g.getTime().equals(mandante);
      }
    }).count();
  }

  public int getTotalDeGolsDoVisitante() {
    return (int) gols.stream().filter(g -> {
      if ((g.isContra() == true) && g.getTime().equals(mandante)) {
        return true;
      } else {
        return (g.isContra() == false) && g.getTime().equals(visitante);
      }
    }).count();
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
    if (estaNoJogo(time) == true) {
      if (mandante.equals(time)) {
        return getTotalDeGolsDoVisitante();
      } else {
        return getTotalDeGolsDoMandante();
      }
    } else {
      return 0;
    }
  }

  public boolean estaNoJogo(Time time) {
    return visitante.equals(time) || mandante.equals(time);
  }

  public List<Jogador> getJogadores() {
    final List<Jogador> jogadores = new ArrayList<>();
    jogadores.addAll(mandante.getJogadores());
    jogadores.addAll(visitante.getJogadores());
    return jogadores;
  }

  public void adicionarCartao(Jogador jogador, Cartao cartao) {
    Validate.isTrue(jogador.isSuspenso() == false, "Jogador está suspenso");
    if (cartoesDoJogador(jogador).contains(Cartao.AMARELO)) {
      jogador.setCartao(Cartao.VERMELHO);
    } else if (jogador.getCartao().equals(Cartao.AMARELO_1)) {
      jogador.setCartao(Cartao.AMARELO_2);
    } else if (cartao.equals(Cartao.VERMELHO)) {
      jogador.setCartao(Cartao.VERMELHO);
    } else {
      jogador.setCartao(Cartao.AMARELO_1);
    }
    cartoesDoJogo.add(new CartaoDoJogo(cartao, jogador));
  }

  public List<CartaoDoJogo> getCartoesDoJogo() {
    return cartoesDoJogo;
  }

  private List<Cartao> cartoesDoJogador(Jogador jogador) {
    final Predicate<? super CartaoDoJogo> mesmoJogador = c -> c.getJogador().equals(jogador);
    return cartoesDoJogo.stream().filter(mesmoJogador).map(CartaoDoJogo::getCartao).collect(Collectors.toList());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + numero;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
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
}
