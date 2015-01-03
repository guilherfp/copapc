package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;

@Scope("request")
@Controller("artilhariaMB")
// @ManagedBean(name = "artilhariaMB")
public class ArtilhariaManagedBean {

  @Autowired
  private JogadorRepository jogadorRepository;

  @Transactional
  public List<Jogador> artilheiros() {
    return jogadorRepository.artilharia();
  }

}
