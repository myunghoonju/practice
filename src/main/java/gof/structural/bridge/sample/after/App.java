package gof.structural.bridge.sample.after;

import gof.structural.bridge.sample.Champ;

public class App {

    public static void main(String[] args) {
        Champ kda = new KdaChamp(new KdaSkin());
        kda.skillE();
    }
}
