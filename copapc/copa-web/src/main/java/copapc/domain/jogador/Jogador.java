package copapc.domain.jogador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableInt;

import copapc.domain.gol.Gol;
import copapc.domain.jogo.CartaoDoJogo;
import copapc.domain.jogo.Jogo;
import copapc.domain.jogo.JogoRepository;
import copapc.domain.time.Time;
import copapc.shared.Entity;
import copapc.shared.UrlUtil;

/**
 * @author Guilherme Pacheco
 */
public class Jogador extends Entity implements Comparable<Jogador> {

  private String nome;
  private String email;
  private Posicao posicao;
  private Time time;
  private Status status;
  private List<Gol> gols;
  private List<CartaoDoJogo> cartoesPorJogos;
  private boolean suspenso;
  private String url;

  Jogador() {
    cartoesPorJogos = new ArrayList<>();
    gols = new ArrayList<>();
  }

  public Jogador(String nome, String email) {
    this();
    setEmail(email);
    setNome(nome);
  }

  public String getNome() {
    return nome;
  }

  public List<CartaoDoJogo> getCartoesPorJogos() {
    cartoesPorJogos.sort(Comparator.comparingInt(CartaoDoJogo::getMinuto));
    cartoesPorJogos.sort(new Comparator<CartaoDoJogo>() {
      @Override
      public int compare(CartaoDoJogo o1, CartaoDoJogo o2) {
        return o1.getJogo().getHorario().compareTo(o2.getJogo().getHorario());
      }
    });
    return cartoesPorJogos;
  }

  public void addCartao(CartaoDoJogo cartaoDoJogo) {
    cartoesPorJogos.add(cartaoDoJogo);
  }

  public void setNome(final String nome) {
    Validate.notBlank(nome, "Nome inválido");
    this.nome = nome;
    url = UrlUtil.formatURL(nome);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    Validate.notBlank(email, "Nome inválido");
    this.email = email;
  }

  public Posicao getPosicao() {
    return posicao;
  }

  public void setPosicao(Posicao posicao) {
    this.posicao = posicao;
  }

  public boolean isPossuiPosicao() {
    return posicao != null;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public boolean isPossuiTime() {
    return time != null;
  }

  public void adicionarGol(Gol gol) {
    Validate.notNull(gol);
    gols.add(gol);
  }

  public int getTotalDeGols() {
    return (int) gols.stream().filter(g -> !g.isContra()).count();
  }

  public List<Gol> getGols() {
    gols.sort(Comparator.comparingInt(Gol::getMinuto));
    gols.sort((o1, o2) -> o1.getJogo().getHorario().compareTo(o2.getJogo().getHorario()));
    return gols;
  }

  public boolean isPossuiGolAFavor() {
    return isPossuiGol() && gols.stream().noneMatch(Gol::isContra);
  }

  public boolean isPossuiGol() {
    return !gols.isEmpty();
  }

  public boolean isNaoPossuiGol() {
    return gols.isEmpty();
  }

  public List<Jogo> getGolsPorJogo() {
    return gols.stream().map(Gol::getJogo).collect(Collectors.toList());
  }

  public Map<Jogo, Integer> getGolsAFavorPorJogo() {
    Map<Jogo, Integer> golsPorJogo = new TreeMap<>();
    for (Gol gol : getGols()) {
      int aFavor = !gol.isContra() ? 1 : 0;
      golsPorJogo.put(gol.getJogo(), golsPorJogo.getOrDefault(gol.getJogo(), 0) + aFavor);
    }
    return golsPorJogo;
  }

  public int getGolsContra(Jogo jogo) {
    MutableInt total = new MutableInt();
    Predicate<Gol> mesmoJogo = g -> g.getJogo().equals(jogo);
    getGols().stream().filter(mesmoJogo).filter(Gol::isContra).forEach(g -> total.increment());
    return total.intValue();
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public boolean isPossuiCartao() {
    return status != null;
  }

  public String getUrl() {
    return url;
  }

  public boolean isSuspenso() {
    return suspenso;
  }

  public void tirarSuspensao() {
    suspenso = false;
  }

  public void suspender() {
    suspenso = true;
  }

  public double getAproveitamento(JogoRepository jogoRepository) {
    double totalDeGols = getTotalDeGols();
    long totalDePartidasJogadas = getTotalDePartidasJogadas(jogoRepository);
    return totalDePartidasJogadas > 0 ? totalDeGols / totalDePartidasJogadas : 0;
  }

  private long getTotalDePartidasJogadas(JogoRepository jogoRepository) {
    return jogoRepository.jogos(time).stream().filter(Jogo::isEncerrado).count();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (email == null ? 0 : email.hashCode());
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
    Jogador other = (Jogador) obj;
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return nome;
  }

  @Override
  public int compareTo(Jogador o) {
    return nome.compareTo(o.nome);
  }

}
