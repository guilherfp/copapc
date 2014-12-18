package copapc.model.jogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDateTime;

import copapc.model.jogador.Jogador;
import copapc.model.time.Time;

public class Jogo {

  private Time mandante;
  private Time visitante;
  private LocalDateTime horario;
  private List<Jogador> jogadoresMarcaramQueGol = new ArrayList<>();

  public Jogo(Time mandante, Time visitante) {
    visitante(visitante);
    mandante(mandante);
  }

  public Time ganhador() {
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
    this.mandante = mandante;
  }

  public Time visitante() {
    return visitante;
  }

  public void visitante(Time visitante) {
    Validate.notNull(mandante, "Time visitante inválido");
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
    return (int) jogadoresMarcaramQueGol.stream().filter(j -> j.time().equals(mandante)).count();
  }

  public int totalDeGolsDoVisitante() {
    return (int) jogadoresMarcaramQueGol.stream().filter(j -> j.time().equals(visitante)).count();
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
    return String.format("%s X %s, horário: %s", mandante, visitante, horario.toString("dd/MM/yy HH:mm"));
  }

}
