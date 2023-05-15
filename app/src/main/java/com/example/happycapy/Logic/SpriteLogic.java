package com.example.happycapy.Logic;


import com.example.happycapy.Entities.Capybara;

public class SpriteLogic {
    private int lives;
    private String name;
    private String stringType;
    private String defaultName = "default";

    private boolean endless;

    public SpriteLogic(int lives, String name, String stringType) {
        this.lives = lives;
        this.name = name;
        this.stringType = stringType;
    }

    public SpriteLogic() {
        this(0, null, null);
    }

    public int getLives() {
        return lives;
    }

    public String getName() {
        return name;
    }

    public Capybara getType() {
        if (stringType.equals("brown")) {
            return Capybara.BROWNCAPY;
        }
        if (stringType.equals("pink")) {
            return Capybara.PINKCAPY;
        }
        if (stringType.equals("green")) {
            return Capybara.GREENCAPY;
        }
        return null;
    }
    public String getStringType() {
        return stringType;
    }
    public void setStringType(String stringType) {
        this.stringType = stringType;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    public void decreaseLives() {
        if (lives != 0) {
            lives--;
        }
    }

    public void setName(String name) {
        if (name == "" || name.isEmpty() || name == null) {
            this.name = defaultName;
        } else {
            this.name = name;
        }
    }

    public void setType(String stringType) {
        this.stringType = stringType;
    }

    public void setEndless(boolean value) {
        this.endless =  value;
    }


    public boolean getEndless() {
        return this.endless;
    }



}