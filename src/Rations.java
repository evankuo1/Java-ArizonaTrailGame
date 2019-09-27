import java.io.Serializable;

public class Rations implements Serializable{
    public static final Rations FILLING = new Rations("Filling", 1);
    public static final Rations MEAGER = new Rations("Meager", 2);
    public static final Rations BARE = new Rations("Bare", 3);

    private String rationName;
    private int rationType;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;
    //private int amountConsumed;

    public Rations(String rationName, int rationType){
        this.rationName = rationName;
        this.rationType = rationType;
    }

    public static Rations makeRation(int rationType){
        switch(rationType) {
            case(1):{
                return Rations.FILLING;
            }
            case(2):{
                return Rations.MEAGER;
            }
            case(3):{
                return Rations.BARE;
            }
            default:
                return null;
        }
    }

    public static Rations makeRation(String rationName){
        switch(rationName) {
            case("Filling"):{
                return Rations.FILLING;
            }
            case("Meager"):{
                return Rations.MEAGER;
            }
            case("Bare"):{
                return Rations.BARE;
            }
            default:
                return null;
        } 
    }

    public String getRationName(){
        return this.rationName;
    }

    public void setRations(int type){
        this.rationType = rationType;
    }

    public int getRationType(){
        return this.rationType;
    }

    public int getAmountConsumed(){
        if(rationType == 1){
            return 30;
        } else if(rationType ==2){
            return 20;
        } else {
            return 10;
        }
    }
}
