package copapc.model.campeonato;

import static org.junit.Assert.assertEquals;

import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import copapc.model.campeonato.modelidade.Modalidade;
import copapc.model.campeonato.modelidade.PorGrupoEMataMata;
import copapc.model.jogador.Jogador;
import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

public class CampeonatoTest {

  private Campeonato campeonato;

  @Before
  public void setUp() throws Exception {
    campeonato = new Campeonato("Copa PC");
  }

  @Test
  public void testAdicionarTime() throws Exception {
    for (int i = 0; i < 10; i++) {
      final Time time = campeonato.adicionarTime();
      time.adicionarJogador(new Jogador("A", "a@email.com"));
    }
    assertEquals(10, campeonato.times().size());
  }

  @Test
  public void testGerarJogos() throws Exception {
    for (int i = 0; i < 10; i++) {
      final Time time = campeonato.adicionarTime();
      time.adicionarJogador(new Jogador("A", "a@email.com"));
    }
    Modalidade modalidade = new PorGrupoEMataMata(5, campeonato);
    campeonato.iniciarCampeonato(modalidade);
    for (Entry<Jogo, Fase> jogo : campeonato.jogos().entrySet()) {
      final Jogador jogador = jogo.getKey().mandante().jogadores().iterator().next();
      jogo.getKey().adicionarGol(jogador);
      System.out.println(jogo + " " + jogo.getValue());
    }
    assertEquals(25, campeonato.jogos().size());
  }
}
