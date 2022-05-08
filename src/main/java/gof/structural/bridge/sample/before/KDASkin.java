package gof.structural.bridge.sample.before;

import gof.structural.bridge.sample.Champ;

public class KDASkin implements Champ {

    @Override
    public void move() {
        System.out.println("kda move");
    }

    @Override
    public void skillQ() {
        System.out.println("kda skillQ");
    }

    @Override
    public void skillW() {
        System.out.println("kda skillW");
    }

    @Override
    public void skillE() {
        System.out.println("kda skillE");
    }

    @Override
    public void skillR() {
        System.out.println("kda skillR");
    }
}
