package copapc.copa.web.controllers;

import java.io.Serializable;
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
public class ArtilhariaManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private JogadorRepository jogadorRepository;

  @Transactional
  public List<Jogador> getArtilheiros() {
    return jogadorRepository.artilharia();
  }

}
