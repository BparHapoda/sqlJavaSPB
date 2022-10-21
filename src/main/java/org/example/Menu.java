package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String name;
    private ArrayList <MenuPunkt> items = new ArrayList<>();
    private boolean exit;

    public Menu (String name){
        this.name=name;
        this.exit = false;
    }
    public Menu (){
        this.exit=false;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ArrayList<MenuPunkt> getItems() {
        return items;
    }
    public static Menu getMenu (){
        Menu menu = new Menu();
        menu.name="Главное меню";
        menu.getItems().add(new MenuPunkt("Вывести базу полностью",()->System.out.println("9")));
        menu.getItems().add(new MenuPunkt("Внести нового пользователя в базу данных",()->System.out.println("9")));
        menu.getItems().add(new MenuPunkt("Изменить данные пользователя",()->System.out.println("9")));
        menu.getItems().add(new MenuPunkt("Найти пользователя по ФИО",()->System.out.println("9")));
        menu.getItems().add(new MenuPunkt("Найти пользователя по дате рождения",()->System.out.println("9")));
        menu.getItems().add(new MenuPunkt("Выход",()-> menu.setExit(true)));
        return menu;
    }
    public void print (){
        System.out.println(name+" :");
        for (int i=0;i<items.size();i++){
            System.out.println((i+1)+". "+items.get(i).getName());
        }
    }
    public void run (){
        print();
        Scanner scanner = new Scanner(System.in);
        while (!exit){
            int input = scanner.nextInt();
            if (input>=1 & input <=items.size()){
                items.get(input-1).getAction().run();
            }
        }
    }
}
