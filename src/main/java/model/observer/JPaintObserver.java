package model.observer;

import java.util.ArrayList;
import java.util.List;

// Observer Pattern
public class JPaintObserver implements IObservable {
    List<IObserver> observerArrayList = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observerArrayList.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observerArrayList.remove(observer);
    }

    @Override
    public void clearObserver() {
        observerArrayList.clear();
    }

    @Override
    public void sendNotification() {
        for (IObserver obs : observerArrayList) {
            obs.update();
        }
    }
}
