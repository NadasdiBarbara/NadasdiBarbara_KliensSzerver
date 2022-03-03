package weather;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) throws IOException {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                System.out.println("Válassszon az alábbi közül: ");
                System.out.println("1. Összes adat kiiratása");
                System.out.println("2.Átlagos fok ma");
                System.out.println("3.Átlagos fok holnap");
                System.out.println("4.Megyék száma");
                System.out.println("5.19 fok van-e az adatok között?");

                menu = sc.nextInt();
                szervernek.writeInt(menu);
                szervernek.flush();
                System.out.println(szervertol.readUTF());
            } while (menu != -1);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
