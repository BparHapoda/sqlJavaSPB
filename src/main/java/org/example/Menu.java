package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        menu.getItems().add(new MenuPunkt("Вывести базу полностью", Menu::baseOutput));
        menu.getItems().add(new MenuPunkt("Внести нового пользователя в базу данных",()->System.out.println("2")));
        menu.getItems().add(new MenuPunkt("Изменить данные пользователя",()->System.out.println("3")));
        menu.getItems().add(new MenuPunkt("Найти пользователя по ФИО",()->System.out.println("4")));
        menu.getItems().add(new MenuPunkt("Найти пользователя по дате рождения",()->System.out.println("5")));
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

        Scanner scanner = new Scanner(System.in);
        String  input;
        int a;
        while (!exit){
            print();
            input = scanner.nextLine();
            try{
            a=Integer.parseInt(input);}
            catch (NumberFormatException e){continue;}

            if (a>=1 & a <=items.size()){
                items.get(a-1).getAction().run();
            }
        }
    }
    public static void baseOutput (){
        String query = "SELECT * FROM customers";
        try {
            System.out.println(DataBaseQuery.selectQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertBase (){
        Scanner scanner= new Scanner(System.in);
      System.out.println("Введите ФИО (если отчества нет,напишите - нет:");
      String fio =scanner.nextLine();
      System.out.println("Введите дату в формате (YYYY-MM-DD) :");
      String date = scanner.nextLine();
    }
}
