package copapc.model.time;

import java.util.List;

public interface TimeRepository {

  List<Time> times();

  List<Time> timesPorGrupo(int fase, char grupo);

  Time comNumero(int numero);

  Time comURL(String url);

}
