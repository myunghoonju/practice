package gof.structural.bridge.sample.before;

import gof.structural.bridge.sample.Champ;

public class PoolSkin implements Champ {

    @Override
    public void move() {
        System.out.println("pool move");
    }

    @Override
    public void skillQ() {
        System.out.println("pool skillQ");
    }

    @Override
    public void skillW() {
        System.out.println("pool skillW");
    }

    @Override
    public void skillE() {
        System.out.println("pool skillE");
    }

    @Override
    public void skillR() {
        System.out.println("pool skillR");
    }
}
