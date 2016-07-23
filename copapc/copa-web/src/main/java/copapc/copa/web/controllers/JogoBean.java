package copapc.copa.web.controllers;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import copapc.copa.web.shared.Lazy;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogo.JogoService;
import copapc.service.jogo.Rodada;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("jogoMB")
public class JogoBean extends AbstractBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String URL_JOGO = "jogo";

  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  private final Lazy<List<Rodada>> rodadasFase1 = Lazy.empty();
  private final Lazy<List<Rodada>> rodadasFase2 = Lazy.empty();
  private final Lazy<List<Jogo>> jogos = Lazy.empty();
  private final Lazy<Jogo> jogo = Lazy.empty();

  private int numeroJogo() {
    return NumberUtils.toInt(urlValue(URL_JOGO), -1);
  }

  public List<Rodada> getRodadasFase1() {
    return rodadasFase1.get(() -> jogoService.rodadas(1));
  }

  public List<Rodada> getRodadasFase2() {
    return rodadasFase2.get(() -> jogoService.rodadas(2));
  }

  public boolean isShowFase2() {
    return !getRodadasFase2().isEmpty();
  }

  public List<Jogo> getJogos() {
    return jogos.get(() -> jogoRepository.jogos());
  }

  public Jogo getJogo() {
    return jogo.get(() -> jogoRepository.jogo(numeroJogo()));
  }

  public boolean isShowPrimeiroTempo() {
    return !getJogo().isSegundoTempo() && !getJogo().isEncerrado();
  }

  public boolean isShowSegundoTempo() {
    return getJogo().isSegundoTempo() && !getJogo().isEncerrado();
  }

}
