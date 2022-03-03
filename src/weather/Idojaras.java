package weather;

public class Idojaras {


    private String megye;
    private  Elorejelzes mai, holnapi;

    public Idojaras(String sor) {
        String [] adatok = sor.split("\\t+",-1);

        this.megye = adatok[0].trim();
        this.mai = new Elorejelzes(adatok[1].trim(), adatok[2].trim());
        this.holnapi = new Elorejelzes(adatok[3].trim(), adatok[4].trim());
    }


    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public Elorejelzes getMai() {
        return mai;
    }

    public void setMai(Elorejelzes mai) {
        this.mai = mai;
    }

    public Elorejelzes getHolnapi() {
        return holnapi;
    }

    public void setHolnapi(Elorejelzes holnapi) {
        this.holnapi = holnapi;
    }

    @Override
    public String toString() {
        return  megye + "\n\t"  + "mai: "+ mai +  "\n\t"+"holnapi: " + holnapi ;
    }
}
