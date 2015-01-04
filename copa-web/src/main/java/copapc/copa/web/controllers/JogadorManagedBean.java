package copapc.copa.web.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;

@Scope("request")
@Controller("jogadorMB")
@ManagedBean(name = "jogadorMB")
public class JogadorManagedBean extends AbstractManagedBean {
  private static final long serialVersionUID = 1L;

  private static final String JOGADOR = "jogador";

  @Autowired
  private JogadorRepository jogadorRepository;

  private List<Jogador> jogadores;
  private Jogador jogador;

  @Transactional
  public List<Jogador> getJogadores() {
    if (jogadores == null) {
      jogadores = jogadorRepository.jogadores();
    }
    return jogadores;
  }

  @Transactional
  public Jogador getJogador() {
    if (jogador == null) {
      final String jogadorUrl = getURLParameterValue(JOGADOR);
      jogador = jogadorRepository.comUrl(jogadorUrl);
    }
    return jogador;
  }

}
