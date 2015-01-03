package copapc.copa.web.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;

// @ManagedBean(name = "jogadorMB")
@Scope("request")
@Controller("jogadorMB")
public class JogadorManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private JogadorRepository jogadorRepository;

  private List<Jogador> jogadores;

  @Transactional
  public List<Jogador> getJogadores() {
    if (jogadores == null) {
      jogadores = jogadorRepository.jogadores();
    }
    return jogadores;
  }

}
