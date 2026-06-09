package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverEverydayExample {

	public static void main(String[] args) {
		TwitterStream stream = new TwitterStream();

		Client client1 = new Client("Giri");
		Client client2 = new Client("Raj");

		stream.addObserver(client1);
		stream.addObserver(client2);

		stream.someoneTweeted();
	}
}

interface TweetObserver {
	void update();
}

// Concrete Subject
class TwitterStream {
	private final List<TweetObserver> observers = new ArrayList<>();

	public void addObserver(TweetObserver observer) {
		observers.add(observer);
	}

	public void someoneTweeted() {
		observers.forEach(TweetObserver::update);
	}
}

// Concrete Observer
class Client implements TweetObserver {

	private String name;

	public Client(String name) {
		this.name = name;
	}

	@Override
	public void update() {
		System.out.println("Updating " + name + "'s feed. someone tweeted");
	}

}