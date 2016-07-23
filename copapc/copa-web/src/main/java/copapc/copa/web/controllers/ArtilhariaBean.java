package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.copa.web.shared.Lazy;
import copapc.service.jogador.Artilheiro;
import copapc.service.jogador.JogadorService;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("artilhariaMB")
public class ArtilhariaBean extends AbstractBean {
  private static final long serialVersionUID = 1L;

  @Autowired
  private JogadorService jogadorService;

  private final Lazy<List<Artilheiro>> artilherios = Lazy.empty();

  @Transactional
  public List<Artilheiro> getArtilheiros() {
    return artilherios.get(() -> jogadorService.artilheiros());
  }

}
