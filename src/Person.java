import java.io.Serializable;

public class Person implements Serializable{
    private String name;
    private int health;
    private boolean isDead;
    private String causeOfDeath = "";

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;
    //Could be used to differentiate between party member or shop owner/npc.
    //0 = party member, 1 = shop owner, 2 = others(bandits, randoms met on trail)
    //private int type;

    public Person(String name){
        this.name = name;
        health = 100;
        isDead = false;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void decHealth(){
        this.health--;
    }
    
    public void diedOf(String string) {
    	causeOfDeath = string;
    }
    
    public String getCauseOfDeath() {
    	return causeOfDeath;
    }

    public void incHealthByAmount(int amount){
        this.health += amount;
    }

    public void decHealthByAmount(int amount){
        this.health -= amount;
    }

    public void incHealth(){
        this.health++;
    }

    public int getHealth(){
        return health;
    }

    public void setDead(){
        this.isDead = true;
    }

    public boolean isDead(){
        return isDead;
    }
}
