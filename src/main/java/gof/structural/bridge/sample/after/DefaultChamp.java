package gof.structural.bridge.sample.after;

import gof.structural.bridge.sample.Champ;

public class DefaultChamp implements Champ {

    private Skin skin;
    private String name;

    public DefaultChamp(Skin skin, String name) {
        this.skin = skin;
        this.name = name;
    }

    @Override
    public void move() {
        System.out.println(skin.getName() + " move___ " + name);
    }

    @Override
    public void skillQ() {
        System.out.println(skin.getName() + " skillQ___ " + name);
    }

    @Override
    public void skillW() {
        System.out.println(skin.getName() + " skillW___ " + name);
    }

    @Override
    public void skillE() {
        System.out.println(skin.getName() + " skillE___ " + name);
    }

    @Override
    public void skillR() {
        System.out.println(skin.getName() + " skillR___ " + name);
    }
}
