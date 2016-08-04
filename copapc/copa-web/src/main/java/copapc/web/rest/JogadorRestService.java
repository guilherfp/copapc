package copapc.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import copapc.domain.jogador.Jogador;
import copapc.domain.jogador.JogadorRepository;
import copapc.web.dto.JogadorDto;

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
