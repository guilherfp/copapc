package copapc.model.jogo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import copapc.model.campeonato.Campeonato;
import copapc.model.jogador.Jogador;
import copapc.model.time.Time;

public class JogoTest {

  private Jogo jogo;
  private Time mandante = new Time(1);
  private Time visitante = new Time(2);
  private List<Jogador> jogadoresMandante = new ArrayList<>();
  private List<Jogador> jogadoresVisitante = new ArrayList<>();
  private Campeonato campeonato = new Campeonato("CPC");

  @Before
  public void setUp() throws Exception {
    jogo = new Jogo(mandante, visitante, campeonato);
    for (int i = 0; i < 5; i++) {
      final Jogador jogadorMandante = new Jogador("Nome " + i, i + "@email.com");
      jogadoresMandante.add(jogadorMandante);
      mandante.adicionarJogador(jogadorMandante);
      final Jogador jogadorVisitante = new Jogador("Nome " + (i * 2), (i * 2) + "@email.com");
      jogadoresVisitante.add(jogadorVisitante);
      visitante.adicionarJogador(jogadorVisitante);
    }
  }

  @Test
  public void testAdicionarGol() throws Exception {
    assertEquals(0, jogo.getTotalDeGols());
    jogo.adicionarGol(jogadoresMandante.get(0));
    assertEquals(1, jogo.getTotalDeGols());
    assertEquals(mandante, jogo.getVencedor());
    assertEquals(1, jogo.getTotalDeGolsDoMandante());
    assertEquals(0, jogo.getTotalDeGolsDoVisitante());

    jogo.adicionarGol(jogadoresVisitante.get(0));
    assertEquals(2, jogo.getTotalDeGols());
    assertEquals(null, jogo.getVencedor());
    assertEquals(1, jogo.getTotalDeGolsDoMandante());
    assertEquals(1, jogo.getTotalDeGolsDoVisitante());

    jogo.adicionarGol(jogadoresVisitante.get(0));
    assertEquals(3, jogo.getTotalDeGols());
    assertEquals(visitante, jogo.getVencedor());
    assertEquals(1, jogo.getTotalDeGolsDoMandante());
    assertEquals(2, jogo.getTotalDeGolsDoVisitante());
  }
}
