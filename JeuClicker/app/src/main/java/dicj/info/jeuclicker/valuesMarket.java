package dicj.info.jeuclicker;

/**
 * Created by lordfred23 on 2017-05-01.
 */

public class valuesMarket {
    static int critprice,trophyPrice;
    public valuesMarket(){
        critprice=10;
        trophyPrice=10;
    }

    public int getCritprice() {
        return critprice;
    }

    public int getTrophyPrice() {
        return trophyPrice;
    }

    public void setTrophyPrice(int trophyPrice) {
        this.trophyPrice = trophyPrice;
    }

    public void setCritprice(int critprice) {
            this.critprice = critprice;
    }
}
