package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private String name;
    private ArrayList<MenuPunkt> items = new ArrayList<>();
    private boolean exit;

    public Menu(String name) {
        this.name = name;
        this.exit = false;
    }

    public Menu() {
        this.exit = false;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ArrayList<MenuPunkt> getItems() {
        return items;
    }

    public static Menu getMenu() {
        Menu menu = new Menu();
        menu.name = "Главное меню";
        menu.getItems().add(new MenuPunkt("Вывести базу полностью", Menu::baseOutput));
        menu.getItems().add(new MenuPunkt("Внести нового пользователя в базу данных", Menu::insertBase));
        menu.getItems().add(new MenuPunkt("Найти пользователя по ФИО", Menu::find));
        menu.getItems().add(new MenuPunkt("Найти пользователя по дате рождения", Menu::findBirthDay));
        menu.getItems().add(new MenuPunkt("Выход", () -> menu.setExit(true)));
        return menu;
    }

    public void print() {
        System.out.println(name + " :");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        String input;
        int a;
        while (!exit) {
            print();
            input = scanner.nextLine();
            try {
                a = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            if (a >= 1 & a <= items.size()) {
                items.get(a - 1).getAction().run();
            }
        }
    }

    public static void baseOutput() {
        String query = "SELECT * FROM customers";
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.print(query);
    }

    public static void find () {
        String fio = inputText("Введите ФИО пользователя,которого хотите найти : ");
        String query = "SELECT * FROM customers WHERE name ='"+fio+"'";
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.print(query);
    }
    public static void findBirthDay () {
        String birthday = inputText("Введите дату рождения пользователя,которого хотите найти (YYYY-MM-DD):  ");
        String query = "SELECT * FROM customers WHERE birthday ='"+birthday+"'";
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.print(query);
    }


    public static void insertBase() {
        String fioSplit [];
        do {
            String fio = inputText("Введите ФИО ");
            fioSplit = fio.split(" ");
        }while (fioSplit.length<3 & fioSplit[1].length()<2);

        String name = fioSplit [1];
        String surSurname =fioSplit [2];
        String surname =fioSplit [0];
        String end = surSurname.substring(surSurname.length()-2,surSurname.length());
        String pol;
        if(end.equals("ич")){pol ="М";}
        else {pol="Ж";}
        String birthday = inputText("Введите дату в формате (YYYY-MM-DD) :");

        try {
            DataBaseQuery.insert(name,surname,surSurname,pol,birthday);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String inputText(String text) {
        String string = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        while (string == "") {
            string = scanner.nextLine();
        }
        return string;

    }
}