package copapc.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import copapc.web.dto.CartaoDto;
import copapc.web.dto.GolDto;
import copapc.web.dto.JogadorDto;
import copapc.web.dto.JogoDto;

/**
 * @author Guilherme Pacheco
 */
@RestController
@RequestMapping("/jogos")
public class JogoResource {

  private static final String SUCESSO = "Sucesso";
  private static final Logger LOG = LoggerFactory.getLogger(JogoResource.class);

  @Autowired
  private JogoApplication jogoApplication;

  @RequestMapping(value = "/{numero}", method = RequestMethod.GET)
  public List<JogadorDto> jogadoresDoJogo(@PathVariable("numero") int numero) {
    return jogoApplication.jogadoresDoJogo(numero);
  }

  @RequestMapping(value = "/abertos", method = RequestMethod.GET)
  public List<JogoDto> jogoEmAberto() {
    return jogoApplication.jogosAbertos();
  }

  @RequestMapping(value = "/gol", method = RequestMethod.POST)
  public String adicionarGol(@RequestBody GolDto dto) {
    LOG.info("{}, adicionando gol: {}", username(), dto);
    jogoApplication.adicionarGol(dto);
    LOG.info("{}, adicionou gol: {}", username(), dto);
    return SUCESSO;
  }

  @RequestMapping(value = "/cartao", method = RequestMethod.POST)
  public String aplicarCartao(@RequestBody CartaoDto dto) {
    LOG.info("{}, aplicando cartão: {}", username(), dto);
    jogoApplication.aplicarCartao(dto);
    LOG.info("{}, aplicou cartão: {}", username(), dto);
    return SUCESSO;
  }

  @RequestMapping(value = "{jogo}/iniciar", method = RequestMethod.GET)
  public String iniciarJogo(@PathVariable("jogo") int jogo) {
    LOG.info("{}, iniciando o jogo: {}", username(), jogo);
    jogoApplication.iniciarJogo(jogo);
    LOG.info("{}, iniciou o jogo: {}", username(), jogo);
    return SUCESSO;
  }

  @RequestMapping(value = "{jogo}/segundoTempo", method = RequestMethod.GET)
  public String iniciarSegundoTempo(@PathVariable("jogo") int jogo) {
    LOG.info("{}, iniciando segundo tempo: {}", username(), jogo);
    jogoApplication.iniciarSegundoTempo(jogo);
    LOG.info("{}, iniciou segundo tempo: {}", username(), jogo);
    return SUCESSO;
  }

  @RequestMapping(value = "{jogo}/encerrar", method = RequestMethod.GET)
  public String encerrarJogo(@PathVariable("jogo") int jogo) {
    LOG.info("{}, encerrando jogo: {}", username(), jogo);
    jogoApplication.encerrarJogo(jogo);
    LOG.info("{}, encerrou jogo: {}", username(), jogo);
    return SUCESSO;
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(RuntimeException.class)
  public String error(RuntimeException exception) {
    LOG.info(exception.getMessage());
    return exception.getMessage();
  }

  private String username() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getUsername();
  }

}
