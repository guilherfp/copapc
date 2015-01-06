package copapc.copa.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import copapc.copa.web.dto.JogadorDTO;
import copapc.model.jogador.JogadorRepository;

@RestController
@RequestMapping("/jogador")
public class JogadorRestService {

  @Autowired
  private JogadorRepository jogadorRepository;

  @ResponseBody
  @Transactional
  @RequestMapping
  public List<JogadorDTO> todos() {
    return jogadorRepository.jogadores().stream().map(JogadorDTO::fromJogador).collect(Collectors.toList());
  }

}
