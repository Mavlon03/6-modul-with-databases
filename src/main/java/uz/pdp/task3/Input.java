package uz.pdp.task3;

import java.util.Scanner;

public interface Input {

    static Integer num(String msg){
        System.out.print(msg);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()){
            return scanner.nextInt();
        }
        else {
            System.out.println("Incorrect order.");
            return num(msg);
        }
    }


    static String numStr(String msg){
        System.out.print(msg);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()){
            return scanner.nextLine();
        }
        else {
            System.out.println("Incorrect order.");
            return numStr(msg);

        }
    }



}
