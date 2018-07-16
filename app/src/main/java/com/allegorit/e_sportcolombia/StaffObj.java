package com.allegorit.e_sportcolombia;

public class StaffObj {
    private int game1, game2, game3, game4, pic;
    private String name,name2, age, since, rol;

    public StaffObj(int game1, int game2, int game3, int game4, int pic, String name, String name2, String age, String since, String rol) {
        this.game1 = game1;
        this.game2 = game2;
        this.game3 = game3;
        this.game4 = game4;
        this.pic = pic;
        this.name = name;
        this.name2 = name2;
        this.age = age;
        this.since = since;
        this.rol = rol;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getGame1() {
        return game1;
    }

    public void setGame1(int game1) {
        this.game1 = game1;
    }

    public int getGame2() {
        return game2;
    }

    public void setGame2(int game2) {
        this.game2 = game2;
    }

    public int getGame3() {
        return game3;
    }

    public void setGame3(int game3) {
        this.game3 = game3;
    }

    public int getGame4() {
        return game4;
    }

    public void setGame4(int game4) {
        this.game4 = game4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }
}
