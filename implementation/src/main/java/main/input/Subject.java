package main.input;

import persistency.Observer;

public interface Subject {

  void registerObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObservers();

}
