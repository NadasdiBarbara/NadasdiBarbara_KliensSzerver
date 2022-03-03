package indianok;

import java.util.ArrayList;

public class Indian {
    private String nev,torzs,nem;
    private int eletkor;
    private ArrayList<String> tulajdonos;

    public Indian(String sor) {
        String [] adatok = sor.split(";");
        this.nev = adatok[0];
        this.torzs = adatok[1];
        this.nem = adatok[2].equals("n")? "nő": "férfi";
        this.eletkor = Integer.parseInt(adatok[3]);
        this.tulajdonos = new ArrayList<>();
        for (int i = 4; i < adatok.length; i++) {
            this.tulajdonos.add(adatok[i]);
        }
    }

    public String getNev() {
        return nev;
    }


    public String getTorzs() {
        return torzs;
    }


    public String getNem() {
        return nem;
    }


    public int getEletkor() {
        return eletkor;
    }


    public ArrayList<String> getTulajdonos() {
        return tulajdonos;
    }


    @Override
    public String toString() {
        String s = "";
        for (String item:this.tulajdonos
             ) {
            s+=item + " ";
        }
        return  nev +  " '"+torzs+
                ", "+nem +
                ", "+eletkor+s ;
    }
}
