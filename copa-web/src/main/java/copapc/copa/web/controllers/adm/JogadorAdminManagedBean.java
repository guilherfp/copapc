package copapc.copa.web.controllers.adm;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.copa.web.dto.JogadorDTO;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.service.jogador.JogadorService;

@Controller("jogadorADM")
// @ManagedBean(name = "jogadorADM")
public class JogadorAdminManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private JogadorRepository jogadorRepository;
  @Autowired
  private JogadorService jogadorService;

  private List<Jogador> jogadores;
  private JogadorDTO jogadorDTO;

  @Transactional
  public List<Jogador> getJogadores() {
    if (jogadores == null) {
      jogadores = jogadorRepository.jogadores();
    }
    return jogadores;
  }

  public JogadorDTO getJogador() {
    if (jogadorDTO == null) {
      // jogadorDTO = new JogadorDTO();
    }
    return jogadorDTO;
  }

  @Transactional
  public void salvar() {
    // jogadorRepository.salvar(jogadorDTO.toJogador());
    jogadorDTO = null;
    jogadores = null;
  }

}
