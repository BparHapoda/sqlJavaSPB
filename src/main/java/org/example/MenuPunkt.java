package org.example;

public class MenuPunkt {
    private String name;
    private MenuAction action;

    public MenuPunkt (String name,MenuAction action){
        this.name=name;
        this.action=action;
    }

    public String getName() {
        return name;
    }

    public MenuAction getAction() {
        return action;
    }
}
@FunctionalInterface
interface MenuAction {
    void run ();
}
