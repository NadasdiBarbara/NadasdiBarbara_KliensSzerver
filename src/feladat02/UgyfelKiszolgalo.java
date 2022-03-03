package feladat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UgyfelKiszolgalo implements Runnable {

    Socket kapcsolat;
    public UgyfelKiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());
            while (true){
                int a = ugyfeltol.readInt();
                int b = ugyfeltol.readInt();
                int menu;
                //"1. kerület\n2. terület\n3.négyzet-e\n4.átló\n5.kilépés"
                do {
                    menu = ugyfeltol.readInt();
                    switch (menu){
                       case 1: ugyfelnek.writeUTF(kerulet(a,b));break;
                       case 2: ugyfelnek.writeUTF(terulet(a,b));break;
                       case 3: ugyfelnek.writeUTF(negyzete(a,b));break;
                       case 4: ugyfelnek.writeUTF(atlo(a,b));break;

                        case 5: ugyfelnek.writeUTF("A kilépést választotta");
                    }
                }while (menu != 5);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private String atlo(int a, int b) {
        return "A téglalap átlója: "+Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
    }

    private String negyzete(int a, int b) {
        return "A téglalap négyzet-e: "+ (a == b ? "igen": "nem");
    }

    private String terulet(int a, int b) {
        return "A téglalap területe: "+(a*b);
    }

    private String kerulet(int a, int b) {

        return "A téglalap kerülete: "+(2*(a+b));
    }
}
