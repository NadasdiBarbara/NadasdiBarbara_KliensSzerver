package weather;

public class Elorejelzes {
    private String szovegesElorejelzes;
    private int min,max;

    public Elorejelzes(String elo, String minMAx) {

        this.szovegesElorejelzes = elo;
        String []st= minMAx.split("/");
        this.min = Integer.parseInt(st[0]);
        this.max = Integer.parseInt(st[1]);

    }

    public String getSzovegesElorejelzes() {
        return szovegesElorejelzes;
    }

    public void setSzovegesElorejelzes(String szovegesElorejelzes) {
        this.szovegesElorejelzes = szovegesElorejelzes;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return szovegesElorejelzes +
                ", min=" + min +
                ", max=" + max ;
    }
}
