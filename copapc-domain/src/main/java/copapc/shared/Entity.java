package copapc.shared;

import java.io.Serializable;

public abstract class Entity implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;

  final int id() {
    return id;
  }

}
