package copapc.model.jogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDateTime;

import copapc.model.campeonato.Campeonato;
import copapc.model.jogador.Jogador;
import copapc.model.time.Time;
import copapc.shared.Entity;

public class Jogo extends Entity {

  private LocalDateTime inicio;
  private LocalDateTime encerramento;
  private Time mandante;
  private Time visitante;
  private LocalDateTime horario;
  private List<Gol> gols = new ArrayList<>();
  private final Campeonato campeonato;

  public Jogo(Time mandante, Time visitante, Campeonato campeonato) {
    setVisitante(visitante);
    setMandante(mandante);
    Validate.notNull(campeonato);
    this.campeonato = campeonato;
  }

  public Campeonato getCampeonato() {
    return campeonato;
  }

  public void iniciar() {
    inicio = new LocalDateTime();
  }

  public void encerrar() {
    encerramento = new LocalDateTime();
  }

  public LocalDateTime getInicio() {
    return inicio;
  }

  public LocalDateTime getEncerramento() {
    return encerramento;
  }

  private boolean isEncerrado() {
    return encerramento != null;
  }

  public Gol adicionarGol(Jogador jogador) {
    Validate.isTrue(isEncerrado() == false, "Jogo já foi encerrado");
    final Gol gol = new Gol(jogador, jogador.getTime(), this);
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

  public LocalDateTime getHorario() {
    return horario;
  }

  public void setHorario(LocalDateTime horario) {
    Validate.notNull(horario, "Horário do jogo inválido");
    this.horario = horario;
  }

  public int getTotalDeGolsDoMandante() {
    return (int) gols.stream().filter(j -> j.getTime().equals(mandante)).count();
  }

  public int getTotalDeGolsDoVisitante() {
    return (int) gols.stream().filter(j -> j.getTime().equals(visitante)).count();
  }

  public int getTotalDeGols() {
    return getTotalDeGolsDoMandante() + getTotalDeGolsDoVisitante();
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
