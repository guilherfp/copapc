package copapc.domain.time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import copapc.domain.jogador.Jogador;
import copapc.shared.Entity;
import copapc.shared.UrlUtil;

/**
 * @author Guilherme Pacheco
 */
public class Time extends Entity implements Comparable<Time> {

  private int numero;
  private String nome;
  private Set<Jogador> jogadores;
  private Jogador responsavel;
  private String url;
  private String sigla;
  private char grupo;

  Time() {
    jogadores = new HashSet<>();
    grupo = ' ';
  }

  public Time(int numero, String nome) {
    this();
    Validate.isTrue(numero > 0, "Número do time inválido");
    this.numero = numero;
    setNome(nome);
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
    url = UrlUtil.formatURL(nome);
    this.nome = nome;
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
    return getArtilheiros().size() > 0;
  }

  public List<Jogador> getArtilheiros() {
    List<Jogador> artilheiros = new ArrayList<>(3);
    int totalDeGols = artilheiro().get().getTotalDeGols();
    for (Jogador jogador : getJogadores()) {
      if (jogador.getTotalDeGols() > 0 && jogador.getTotalDeGols() >= totalDeGols) {
        artilheiros.add(jogador);
      }
    }
    return artilheiros;
  }

  private Optional<Jogador> artilheiro() {
    return getJogadores().stream().max(Comparator.comparingInt(Jogador::getTotalDeGols));
  }

  public char getGrupo() {
    return grupo;
  }

  public void setGrupo(char grupo) {
    this.grupo = grupo;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (nome == null ? 0 : nome.hashCode());
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
