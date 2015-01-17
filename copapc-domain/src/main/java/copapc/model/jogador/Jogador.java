package copapc.model.jogador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

import copapc.model.gol.Gol;
import copapc.model.jogo.CartaoDoJogo;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;
import copapc.shared.Entity;
import copapc.util.DomainUtils;
import copapc.util.UrlUtil;

public class Jogador extends Entity implements Comparable<Jogador> {
  private static final long serialVersionUID = 1L;

  private String nome;
  private String email;
  private Posicao posicao;
  private Time time;
  private List<Gol> gols = new ArrayList<>();
  private Cartao cartao = Cartao.SEM_CARTAO;
  private List<CartaoDoJogo> cartoesPorJogos = new ArrayList<>();
  private String url;

  Jogador() {}

  public Jogador(String nome, String email) {
    setEmail(email);
    setNome(nome);
  }

  public String getNome() {
    return nome;
  }

  public List<CartaoDoJogo> getCartoesPorJogos() {
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
    return (int) gols.stream().filter(g -> g.isContra() == false).count();
  }

  public List<Gol> getGols() {
    return gols;
  }

  public List<Jogo> getGolsPorJogo() {
    return gols.stream().map(Gol::getJogo).collect(Collectors.toList());
  }

  public Map<Jogo, Integer> getGolsAFavorPorJogo() {
    final Map<Jogo, Integer> golsPorJogo = new HashMap<>();
    for (Gol gol : getGols()) {
      final int aFavor = gol.isContra() == false ? 1 : 0;
      golsPorJogo.put(gol.getJogo(), golsPorJogo.getOrDefault(gol.getJogo(), 0) + aFavor);
    }
    return golsPorJogo;
  }

  public int getGolsContra(Jogo jogo) {
    int gols = 0;
    for (Gol gol : getGols()) {
      if (gol.getJogo().getNumero() == jogo.getNumero()) {
        if (gol.isContra() == true) {
          gols++;
        }
      }
    }
    return gols;
  }

  public Cartao getCartao() {
    return DomainUtils.nullSafe(cartao, Cartao.SEM_CARTAO);
  }

  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }

  public boolean isPossuiCartao() {
    return (cartao != null) && (cartao != Cartao.SEM_CARTAO);
  }

  public String getUrl() {
    return url;
  }

  public boolean isSuspenso() {
    return Cartao.VERMELHO.equals(cartao);
  }

  public double getAproveitamento(JogoRepository jogoRepository) {
    final double totalDeGols = getTotalDeGols();
    final long totalDePartidasJogadas = getTotalDePartidasJogadas(jogoRepository);
    if (totalDePartidasJogadas > 0) {
      return totalDeGols / totalDePartidasJogadas;
    } else {
      return 0;
    }
  }

  private long getTotalDePartidasJogadas(JogoRepository jogoRepository) {
    final List<Jogo> jogos = jogoRepository.jogos(time);
    return jogos.stream().filter(Jogo::isEncerrado).count();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((email == null) ? 0 : email.hashCode());
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
