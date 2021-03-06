package model.observer;

public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void clearObserver();

    void sendNotification();
}
