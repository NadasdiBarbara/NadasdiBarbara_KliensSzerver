package weather;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Ugyfelkiszolgalo implements Runnable{

    private HashMap<String, Idojaras> elorejelzések;
    private Socket kapcsolat;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        elorejelzések = new HashMap<>();
        this.kapcsolat = kapcsolat;
        Beolvas();
    }

    @Override
    public void run() {
        try {

            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());

            while (true){
                int menu;
                do {
                    menu = ugyfeltol.readInt();
                    switch (menu){
                        case 1:
                            ugyfelnek.writeUTF(osszesAdat());
                            ugyfelnek.flush();
                            break;
                        case 2:
                            ugyfelnek.writeUTF(atlaglMelegeMa());
                            ugyfelnek.flush();
                            break;
                        case 3:
                            ugyfelnek.writeUTF(atlaglMelegHolnap());
                            ugyfelnek.flush();
                            break;
                        case 4:
                            ugyfelnek.writeUTF(megyekSzama());
                            ugyfelnek.flush();
                            break;
                        case 5:
                            ugyfelnek.writeUTF(vanEIlyenHofok(19));
                            ugyfelnek.flush();
                            break;

                    }
                }while (menu!=0);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private String vanEIlyenHofok(int homerseklet) {
        String s = "";
        int fok = 0;

        while (fok < elorejelzések.entrySet().size() && !elorejelzések.containsValue(elorejelzések.values().contains(homerseklet))) {
            fok++;
        }
        if (fok < elorejelzések.entrySet().size()) {
            s += "Igen";

        } else {
            s += "Nem";
        }
        return s;
    }

    private String atlaglMelegHolnap() {
        double ossz = 0;
        int db = 0;

        for (Map.Entry<String, Idojaras> entry: elorejelzések.entrySet()){
            ossz += entry.getValue().getHolnapi().getMax();
            db++;
        }
        return String.format(" Átlagos fok holnap: %f ", (ossz/db));
    }

    private String megyekSzama() {
        return "A megyék száma: " + elorejelzések.size();
    }

    private String atlaglMelegeMa() {
        double ossz = 0;
        int db = 0;

        for (Map.Entry<String, Idojaras> entry: elorejelzések.entrySet()){
            ossz += entry.getValue().getMai().getMax();
            db++;
        }
        return String.format(" Átlagos fok ma: %f ", (ossz/db));
    }

    private String osszesAdat() {
        String s = "";
        for (Map.Entry<String, Idojaras> entry: elorejelzések.entrySet()){
            s += entry.getValue()+"\n";
        };
        return s;
    }

    public void Beolvas(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while (sor!= null){
                Idojaras i = new Idojaras(sor);
                String megye = i.getMegye();
                elorejelzések.put(megye,i);
                sor = br.readLine();
            }
            for (Map.Entry<String, Idojaras> entry: elorejelzések.entrySet()){
                System.out.println(entry.getValue());
            }

        }catch (FileNotFoundException e){
            System.out.println(e);
        }catch (IOException e){
            System.out.println(e);

        }
    }

}
