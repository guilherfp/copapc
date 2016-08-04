package copapc.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.domain.jogador.Jogador;
import copapc.domain.jogador.JogadorRepository;
import copapc.web.shared.Lazy;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("jogadorMB")
public class JogadorBean extends AbstractBean {
  private static final long serialVersionUID = 1L;

  private static final String PARAM_JOGADOR = "jogador";

  @Autowired
  private JogadorRepository jogadorRepository;

  private final Lazy<List<Jogador>> jogadores = Lazy.empty();
  private final Lazy<Jogador> jogador = Lazy.empty();

  @Transactional
  public List<Jogador> getJogadores() {
    return jogadores.get(() -> jogadorRepository.jogadores());
  }

  @Transactional
  public Jogador getJogador() {
    return jogador.get(() -> jogadorRepository.comUrl(urlValue(PARAM_JOGADOR)));
  }

  public String getImageTimeUrl() {
    return getResource(String.format("/resources/fase/%s.png", getJogador().getTime().getUrl()));
  }

}
