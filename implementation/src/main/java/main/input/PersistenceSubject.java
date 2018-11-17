package main.input;

import persistence.PersistenceObserver;

public interface PersistenceSubject {

  void registerObserver(PersistenceObserver o);

  void removeObserver(PersistenceObserver o);

  void notifyObservers();

}
