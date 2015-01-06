package copapc.copa.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import copapc.copa.web.dto.GolDTO;
import copapc.copa.web.dto.JogadorDTO;
import copapc.copa.web.dto.JogoDTO;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogo.JogoService;

@RestController
@RequestMapping("/jogos")
public class JogoRestService {

  @Autowired
  private JogadorRepository jogadorRepository;
  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  @ResponseBody
  @Transactional
  @RequestMapping("/{numero}")
  public List<JogadorDTO> jogadoresDoJogo(@PathVariable("numero") int numero) {
    final Jogo jogo = jogoRepository.jogoComNumero(numero);
    if (jogo != null) {
      return jogo.getJogadores().stream().map(JogadorDTO::fromJogador).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  @ResponseBody
  @Transactional
  @RequestMapping("/abertos")
  public List<JogoDTO> emAberto() {
    return jogoRepository.jogosEmAberto().stream().map(JogoDTO::fromJogo).collect(Collectors.toList());
  }

  @Transactional(readOnly = false)
  @RequestMapping(value = "/gol", method = RequestMethod.POST)
  public void adicionarGol(@RequestBody GolDTO golDTO) {
    final Jogador jogador = jogadorRepository.comEmail(golDTO.getEmailDoJogador());
    Validate.notNull(jogador, "Jogador inválido");
    final Jogo jogo = jogoRepository.jogoComNumero(golDTO.getNumeroDoJogo());
    Validate.notNull(jogo, "Jogo inválido");
    if (golDTO.isContra() == false) {
      jogoService.marcarGol(jogador, jogo);
    } else {
      jogoService.marcarGolContra(jogador, jogo);
    }
  }

}
