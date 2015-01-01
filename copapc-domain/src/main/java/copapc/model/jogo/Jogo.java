package copapc.model.jogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDateTime;

import copapc.model.campeonato.Campeonato;
import copapc.model.jogador.Jogador;
import copapc.model.time.Time;

public class Jogo {

  private LocalDateTime inicio;
  private LocalDateTime encerramento;
  private Time mandante;
  private Time visitante;
  private LocalDateTime horario;
  private List<Gol> gols = new ArrayList<>();
  private final Campeonato campeonato;

  public Jogo(Time mandante, Time visitante, Campeonato campeonato) {
    visitante(visitante);
    mandante(mandante);
    Validate.notNull(campeonato);
    this.campeonato = campeonato;
  }

  public Campeonato campeonato() {
    return campeonato;
  }

  public void iniciar() {
    inicio = new LocalDateTime();
  }

  public void encerrar() {
    encerramento = new LocalDateTime();
  }

  public LocalDateTime inicio() {
    return inicio;
  }

  public LocalDateTime encerramento() {
    return encerramento;
  }

  private boolean encerrado() {
    return encerramento != null;
  }

  public Gol adicionarGol(Jogador jogador) {
    Validate.isTrue(encerrado() == false, "Jogo já foi encerrado");
    final Gol gol = new Gol(jogador, jogador.time(), this);
    gols.add(gol);
    return gol;
  }

  public Time vencedor() {
    int golsMandante = totalDeGolsDoMandante();
    int golsVisitante = totalDeGolsDoVisitante();
    if (golsMandante > golsVisitante) {
      return mandante;
    } else if (golsVisitante > golsMandante) {
      return visitante;
    } else {
      return null;
    }
  }

  public Time mandante() {
    return mandante;
  }

  public void mandante(Time mandante) {
    Validate.notNull(mandante, "Time mandante inválido");
    Validate.isTrue(mandante.equals(visitante) == false, "Time inválido");
    this.mandante = mandante;
  }

  public Time visitante() {
    return visitante;
  }

  public void visitante(Time visitante) {
    Validate.notNull(visitante, "Time visitante inválido");
    Validate.isTrue(visitante.equals(mandante) == false, "Time inválido");
    this.visitante = visitante;
  }

  public LocalDateTime horario() {
    return horario;
  }

  public void horario(LocalDateTime horario) {
    Validate.notNull(horario, "Horário do jogo inválido");
    this.horario = horario;
  }

  public int totalDeGolsDoMandante() {
    return (int) gols.stream().filter(j -> j.time().equals(mandante)).count();
  }

  public int totalDeGolsDoVisitante() {
    return (int) gols.stream().filter(j -> j.time().equals(visitante)).count();
  }

  public int totalDeGols() {
    return totalDeGolsDoMandante() + totalDeGolsDoVisitante();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((horario == null) ? 0 : horario.hashCode());
    result = (prime * result) + ((mandante == null) ? 0 : mandante.hashCode());
    result = (prime * result) + ((visitante == null) ? 0 : visitante.hashCode());
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
    if (horario == null) {
      if (other.horario != null) {
        return false;
      }
    } else if (!horario.equals(other.horario)) {
      return false;
    }
    if (mandante == null) {
      if (other.mandante != null) {
        return false;
      }
    } else if (!mandante.equals(other.mandante)) {
      return false;
    }
    if (visitante == null) {
      if (other.visitante != null) {
        return false;
      }
    } else if (!visitante.equals(other.visitante)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s X %s, horário: %s", mandante, visitante, horarioFormatado());
  }

  public String horarioFormatado() {
    if (horario != null) {
      return horario.toString("dd/MM/yy HH:mm");
    } else {
      return "Não de definido";
    }
  }

}
