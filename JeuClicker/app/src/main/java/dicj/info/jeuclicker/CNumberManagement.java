package dicj.info.jeuclicker;

/**
 * Created by lordfred23 on 2017-03-13.
 */

public class CNumberManagement {
    public CNumberManagement(){

    }
    public String[] GoldenNumber(int number,String legendary){
       String []tab = new String[2];

        if((number>=1000)){
            return tab;
        }
        else
        {
            return tab;
        }


    }
    public String Determination_Letter(String letter){
        String e;
        switch (letter){
            case "a" :e="K";
                break;
            case "K":e= "M";
                break;
            case "B":e= "T";
                break;
            default:e=letter;
        }
        return e;

    }
}
