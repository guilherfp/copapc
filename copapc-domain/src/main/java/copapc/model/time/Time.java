package copapc.model.time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import copapc.model.jogador.Jogador;
import copapc.shared.Entity;
import copapc.util.UrlUtil;

public class Time extends Entity implements Comparable<Time> {
  private static final long serialVersionUID = 1L;

  private int numero;
  private String nome;
  private Set<Jogador> jogadores = new HashSet<>();
  private Jogador responsavel;
  private String url;
  private String sigla;
  private Time time;

  Time() {}

  public Time(int numero, String nome) {
    setNome(nome);
    Validate.isTrue(numero > 0, "Número do time inválido");
    this.numero = numero;
  }

  public Time(int numero) {
    this(numero, "Time " + numero);
  }

  public int getNumero() {
    return numero;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    Validate.notBlank(nome, "Nome inválido");
    this.nome = nome;
    url = UrlUtil.formatURL(nome);
  }

  public Collection<Jogador> getJogadores() {
    return new ArrayList<>(jogadores);
  }

  public void adicionarJogador(Jogador jogador) {
    Validate.notNull(jogador, "Jogador inválido");
    jogadores.add(jogador);
    jogador.setTime(this);
  }

  public Jogador getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(Jogador responsavel) {
    Validate.notNull(responsavel, "Responsável inválido");
    this.responsavel = responsavel;
  }

  public Time getTime() {
    return time;
  }

  public String getUrl() {
    return url;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    Validate.notNull(sigla, "Sigla inválida");
    this.sigla = sigla;
  }

  public boolean isPossuiArtilheiro() {
    return artilheiro().get().getTotalDeGols() > 0;
  }

  public Jogador getArtilheiro() {
    return artilheiro().get();
  }

  private Optional<Jogador> artilheiro() {
    return getJogadores().stream().max(Comparator.comparingInt(Jogador::getTotalDeGols));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((nome == null) ? 0 : nome.hashCode());
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
    Time other = (Time) obj;
    if (nome == null) {
      if (other.nome != null) {
        return false;
      }
    } else if (!nome.equals(other.nome)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s (%S)", nome, sigla);
  }

  @Override
  public int compareTo(Time o) {
    return Integer.compare(numero, o.numero);
  }

}
