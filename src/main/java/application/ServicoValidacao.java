package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServicoValidacao {
    public static int lerInteiros(Scanner sc, String texto) {
        while (true) {
            try {
                System.out.print(texto);
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            }catch (InputMismatchException e){
                System.out.print("Error inteiro: " + e);
                sc.nextLine();
            }
        }
    }
    public static double lerDouble(Scanner sc,String texto){
        while (true){
            try {
                System.out.print(texto);
                double numero = sc.nextDouble();
                sc.nextLine();
                return numero;
            }catch (InputMismatchException e){
                System.out.print("Error double: " + e);
                sc.nextLine();
            }
        }
    }
    public static String lerString(Scanner sc,String texto){
        while (true){
            try {
                System.out.print(texto);
                String insert = sc.nextLine();
                return insert;
            }catch (InputMismatchException e){
                System.out.print("Error texto: " +e);
            }
        }
    }
    public static LocalDate lerData(Scanner sc, String texto){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true){
            try {
                System.out.println(texto);
                String dataT = sc.nextLine();
                LocalDate data = LocalDate.parse(dataT, fmt);
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("erro: " + e);
            }
        }
    }
    public static long lerLong(Scanner sc,String texto){
        while (true){
            try {
                System.out.print(texto);
                long numero = sc.nextLong();
                sc.nextLine();
                return numero;
            }catch (InputMismatchException e){
                System.out.print("Error Long: " + e);
                sc.nextLine();
            }
        }
    }
}
