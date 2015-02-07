package copapc.service.jogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import copapc.model.gol.Gol;
import copapc.model.gol.GolRepository;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogador.Status;
import copapc.model.jogo.Cartao;
import copapc.model.jogo.CartaoDoJogo;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.TimeRepository;

public class JogoService {

  private final GolRepository golRepository;
  private final JogoRepository jogoRepository;
  private final TimeRepository timeRepository;
  private final JogadorRepository jogadorRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class);

  public JogoService(GolRepository golRepository, JogoRepository jogoRepository, TimeRepository timeRepository,
                     JogadorRepository jogadorRepository) {
    this.jogoRepository = jogoRepository;
    this.timeRepository = timeRepository;
    this.golRepository = golRepository;
    this.jogadorRepository = jogadorRepository;
  }

  private int checkMinuto(Jogo jogo, int minuto) {
    if (minuto <= 0) {
      return Minutes.minutesBetween(jogo.getInicio(), DateTime.now()).getMinutes();
    } else {
      return minuto;
    }
  }

  public void marcarGol(final Jogador jogador, final Jogo jogo, int minuto) {
    validarInicializacaoDoJogo(jogo);
    final Gol gol = Gol.gol(jogador, jogo, checkMinuto(jogo, minuto));
    jogo.adicionarGol(gol);
    jogador.adicionarGol(gol);
    golRepository.salvar(gol);
    LOGGER.info("Gol marcado: {}", gol);
  }

  public void marcarGolContra(final Jogador jogador, final Jogo jogo, int minuto) {
    validarInicializacaoDoJogo(jogo);
    final Gol gol = Gol.golContra(jogador, jogo, checkMinuto(jogo, minuto));
    jogo.adicionarGol(gol);
    jogador.adicionarGol(gol);
    golRepository.salvar(gol);
    LOGGER.info("Gol marcado: {}", gol);
  }

  public void iniciarJogo(Jogo jogo) {
    Validate.isTrue(jogo.isIniciado() == false, "Jogo já foi iniciado");
    Validate.isTrue(jogo.isEncerrado() == false, "Jogo já foi encerrado");
    jogo.iniciar();
    jogoRepository.atualizar(jogo);
    LOGGER.info("Jogo iniciado: {}", jogo);
  }

  public void validarInicializacaoDoJogo(Jogo jogo) {
    if (jogo.isIniciado() == false) {
      jogo.iniciarNoHorario();
    }
  }

  public void encerrarJogo(Jogo jogo) {
    Validate.isTrue(jogo.isEncerrado() == false, "Jogo já foi encerrado");
    Validate.isTrue(jogo.isIniciado(), "O Jogo não iniciado");
    jogo.encerrar();
    for (Jogador jogador : jogo.getJogadores()) {
      if (Status.AMARELO_3.equals(jogador.getStatus())) {
        jogador.setStatus(Status.SUSPENSO);
        jogadorRepository.atualizar(jogador);
      }
    }
    jogoRepository.atualizar(jogo);
    LOGGER.info("Jogo encerrado: {}", jogo);
  }

  public void adicionarCartao(Jogo jogo, Jogador jogador, Cartao cartao, int minuto) {
    validarInicializacaoDoJogo(jogo);
    Validate.isTrue(jogador.isSuspenso() == false, "Jogador está suspenso");
    if (jogo.getCartoesDoJogador(jogador).contains(Cartao.AMARELO)) {
      jogador.setStatus(Status.SUSPENSO);
      cartao = Cartao.VERMELHO;
    } else if (jogador.getStatus() == null) {
      jogador.setStatus(Status.AMARELO_1);
    } else if (Status.AMARELO_1.equals(jogador.getStatus())) {
      jogador.setStatus(Status.AMARELO_2);
    } else if (Status.AMARELO_2.equals(jogador.getStatus())) {
      jogador.setStatus(Status.AMARELO_3);
    } else if (cartao.equals(Cartao.VERMELHO)) {
      jogador.setStatus(Status.SUSPENSO);
    }
    final CartaoDoJogo cartaoDoJogo = new CartaoDoJogo(cartao, jogo, jogador, checkMinuto(jogo, minuto));
    jogoRepository.salvarCartao(cartaoDoJogo);
    LOGGER.info("Cartão marcado: {}", cartaoDoJogo);
  }

  public List<Rodada> getRodadas(int fase) {
    final List<Jogo> jogos = jogoRepository.jogosPorFase(fase);
    final int fases = getTamanhoDaRodada(fase, jogos);
    final List<Rodada> rodadas = new ArrayList<>();
    final int i = fases > jogos.size() ? jogos.size() : fases;
    for (int start = 0, end = i, rodada = 1; end <= jogos.size(); start += fases, end += fases, rodada++) {
      rodadas.add(new Rodada(rodada, jogos.subList(start, end)));
    }
    return rodadas;
  }

  private int getTamanhoDaRodada(int fase, final List<Jogo> jogos) {
    if (fase == 1) {
      return jogos.size() / timeRepository.quantidadeDeGrupos();
    } else {
      return 4;
    }
  }

  public boolean faseFinalizada(int fase) {
    final List<Jogo> jogos = jogoRepository.jogosPorFase(fase);
    return jogos.stream().allMatch(Jogo::isEncerrado);
  }

  public void segundoTempo(Jogo jogo) {
    Validate.isTrue(jogo.isEncerrado() == false, "Jogo já foi encerrado");
    Validate.isTrue(jogo.isIniciado(), "O Jogo não iniciado");
    jogo.iniciarSegundoTempo();
    jogoRepository.atualizar(jogo);
    LOGGER.info("Segundo tempo do jogo: {} foi iniciado!", jogo);
  }

}
