package copapc.copa.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import copapc.copa.web.dto.JogadorDto;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;

/**
 * @author Guilherme Pacheco
 */
@RestController
@RequestMapping("/jogador")
public class JogadorRestService {

  @Autowired
  private JogadorRepository jogadorRepository;

  @Transactional
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<JogadorDto> todos() {
    List<Jogador> jogadores = jogadorRepository.jogadores();
    return jogadores.stream().map(JogadorDto::new).collect(Collectors.toList());
  }

}
