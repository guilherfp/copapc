package copapc.model.time;

import java.util.List;

public interface TimeRepository {

  List<Time> times();

  Time comNumero(int numero);

  Time comURL(String url);

}
