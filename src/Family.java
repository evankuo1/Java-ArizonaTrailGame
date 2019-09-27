import java.io.Serializable;

public class Family implements Serializable{
    private final int maxMembers = 5; // could change
    private final int daily_health_dec = 10;
    private int numMembers = 0;
    private Person[] members = new Person[maxMembers];
    private int numDead;
    private String famHealth;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

    /***
     * Constructor
     */
    public Family(){
        this.numMembers = 0;
        this.numDead = 0;
        this.famHealth = "Good";
    }

    /***
     * This method adds members to the family.
     * @param member A member of the family
     */
    public void addMember(Person member){
    	if (numMembers < maxMembers) {
	        members[numMembers] = member;
	        numMembers++;
    	}
    }

    /***
     * This method will return a person object associated with a given name.
     * @param name A name associated with each Person object.
     * @return A Person object with the given name.
     */
    // returns null if name not found
    //		probably should throw exception
    public Person getMember(String name) {
    	for (Person p : members) {
    		if (p.getName().equals(name)) {
    			return p;
    		}
    	}

    	return null;
    }

    /***
     *
     * @return returns an array of people.
     */
    public Person[] getFamilyList() {
    	return members;
    }

    /***
     *
     * @return Returns the number of people in the family.
     */
    public int getNumMembers(){
        return numMembers;
    }

    /***
     *
     * @return Returns a string of family member names.
     */
    public String getNames(){
        String memberNames = "";
        for(int i = 0; i < 5; i++){
            memberNames += members[i].getName() + ", ";
        }

        //remove the trailing comma and space
        memberNames = memberNames.substring(0, memberNames.length()-2);
        return memberNames;
    }

    /***
     * Calculates the current health of the entire family.
     */
    public void calcFamHealth(){
        int totalHealth = 0;
        for(int i = 0; i < 5; i++){
            totalHealth += members[i].getHealth();
        }
        if(totalHealth >= 400){
            famHealth = "Good";
        } else if(totalHealth >= 250){
            famHealth = "Fair";
        } else {
            famHealth = "Poor";
        }
        //System.out.println("totalHealth: " + totalHealth);
    }

    /***
     *
     * @return Returns the status of the family's health.
     */
    public String getFamilyStatus(){
        // int totalHealth = 0;
        // //String healthStr = "";
        // for(int i = 0; i < 5; i++){
        //     totalHealth += members[i].getHealth();
        // }
        // if(totalHealth >= 400){
        //     famHealth = "Good";
        // } else if(totalHealth >= 250){
        //     famHealth = "Fair";
        // } else {
        //     famHealth = "Poor";
        // }
        return this.famHealth;
    }

    /***
     *
     * @param health Sets the family's health according to parameter.
     */
    public void setFamHealth(String health){
        this.famHealth = health;
    }

    /***
     * Increase number of dead family members.
     */
    public void incDead(){
        numDead++;
    }

    /***
     *
     * @return Returns the number of dead family members.
     */
    public int getNumDead(){
    	int count = 0;
        for (int i = 0; i < members.length; i++) {
        	if (members[i].isDead()) {
        		count++;
        	}
        }
        return count;
    }

    /***
     *
     * @param amount An integer amount to increase the health of every family member by.
     */
    public void incAllHealth(int amount){
        for(Person mem : members){
            mem.incHealthByAmount(amount);
        }
    }

    /***
     *
     * @param amount An integer amount to decrement the family's health by.
     */
    public void decAllHealth(int amount){
        for(Person mem : members){
            mem.decHealthByAmount(amount);
        }
    }

    /***
     * Used to decrement family health during a day cycle.
     */
    public void dailyHealthDecrement(){
        for(Person mem : members){
            mem.decHealthByAmount(daily_health_dec);
        }
    }

    /***
     *
     * @return Returns a string of dead member names.
     */
    public String checkDead(){
        String dead = "";
        for(Person mem : members){
            if(mem.getHealth() <= 0){
                this.numDead++;
                dead += mem.getName() + " has died. ";
            }
        }
        return dead;
    }
}
