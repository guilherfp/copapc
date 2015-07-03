package copapc.copa.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import copapc.copa.web.dto.CartaoDTO;
import copapc.copa.web.dto.GolDTO;
import copapc.copa.web.dto.JogadorDTO;
import copapc.copa.web.dto.JogoDTO;
import copapc.copa.web.service.JogoServiceApplication;

/**
 * @author Guilherme Pacheco
 */
@RestController
@RequestMapping("/jogos")
public class JogoRestService {

  @Autowired
  private JogoServiceApplication jogoServiceApplication;
  private static final Logger LOGGER = LoggerFactory.getLogger(JogoRestService.class);

  @RequestMapping(value = "/{numero}", method = RequestMethod.GET)
  public List<JogadorDTO> jogadoresDoJogo(@PathVariable("numero") int numero) {
    return jogoServiceApplication.jogadoresDoJogo(numero);
  }

  @RequestMapping(value = "/abertos", method = RequestMethod.GET)
  public List<JogoDTO> emAberto() {
    return jogoServiceApplication.jogosAbertos();
  }

  @RequestMapping(value = "/gol", method = RequestMethod.POST)
  public String adicionarGol(@RequestBody GolDTO golDTO) {
    try {
      LOGGER.info("{}, adicionando gol: {}", username(), golDTO);
      jogoServiceApplication.adicionarGol(golDTO);
      LOGGER.info("{}, adicionou gol: {}", username(), golDTO);
      return "Sucesso";
    } catch (Exception ex) {
      LOGGER.info(ex.getMessage());
      return ex.getMessage();
    }
  }

  @RequestMapping(value = "/cartao", method = RequestMethod.POST)
  public String aplicarCartao(@RequestBody CartaoDTO cartaoDTO) {
    try {
      LOGGER.info("{}, aplicando cartão: {}", username(), cartaoDTO);
      jogoServiceApplication.aplicarCartao(cartaoDTO);
      LOGGER.info("{}, aplicou cartão: {}", username(), cartaoDTO);
      return "Sucesso";
    } catch (Exception ex) {
      LOGGER.info(ex.getMessage());
      return ex.getMessage();
    }
  }

  @RequestMapping(value = "{jogo}/iniciar", method = RequestMethod.GET)
  public String iniciarJogo(@PathVariable("jogo") int numeroDoJogo) {
    try {
      LOGGER.info("{}, iniciando o jogo: {}", username(), numeroDoJogo);
      jogoServiceApplication.iniciarJogo(numeroDoJogo);
      LOGGER.info("{}, iniciou o jogo: {}", username(), numeroDoJogo);
      return "Sucesso";
    } catch (Exception ex) {
      LOGGER.info(ex.getMessage());
      return ex.getMessage();
    }
  }

  @RequestMapping(value = "{jogo}/segundoTempo", method = RequestMethod.GET)
  public String definirSegundoTempoDoJogo(@PathVariable("jogo") int numeroDoJogo) {
    try {
      LOGGER.info("{}, iniciando segundo tempo: {}", username(), numeroDoJogo);
      jogoServiceApplication.iniciarSegundoTempo(numeroDoJogo);
      LOGGER.info("{}, iniciou segundo tempo: {}", username(), numeroDoJogo);
      return "Sucesso";
    } catch (Exception ex) {
      LOGGER.info(ex.getMessage());
      return ex.getMessage();
    }
  }

  @RequestMapping(value = "{jogo}/encerrar", method = RequestMethod.GET)
  public String encerrarJogo(@PathVariable("jogo") int numeroDoJogo) {
    try {
      LOGGER.info("{}, encerrando jogo: {}", username(), numeroDoJogo);
      jogoServiceApplication.encerrarJogo(numeroDoJogo);
      LOGGER.info("{}, encerrou jogo: {}", username(), numeroDoJogo);
      return "Sucesso";
    } catch (Exception ex) {
      LOGGER.info(ex.getMessage());
      return ex.getMessage();
    }
  }

  private String username() {
    return getUser().getUsername();
  }

  private User getUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
