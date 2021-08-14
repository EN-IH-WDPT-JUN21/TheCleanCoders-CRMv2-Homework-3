package com.ironhack.TheCleanCodersCRMv2homework3.main;


import com.ironhack.TheCleanCodersCRMv2homework3.menu.Menu;
import com.ironhack.TheCleanCodersCRMv2homework3.menu.Printer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Menu menu = new Menu();
        Printer printer = new Printer();
        printer.welcomeMessage();
        menu.controlLoop();
    }
}
