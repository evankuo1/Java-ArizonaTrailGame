/**
 * Season class
 * 
 * create seasons using constants
 * 
 * @author John Yang
 *
 */
public class Season {
	// temperature modifiers may need adjusting
	public static final Season SPRING = new Season("Spring", 0, 2, 4, 15);
	public static final Season SUMMER = new Season("Summer", 1, 5, 7, 30);
	public static final Season FALL = new Season("Fall", 2, 8, 10, 0);
	public static final Season AUTUMN = new Season("Autumn", 2, 8, 10, 0);
	public static final Season WINTER = new Season("Winter", 3, 11, 1, -15);
	
	private String name;
	private int num;
	private int start;
	private int end;
	
	private int tempModifier;
	
	private Season(String seasonName, int seasonNum, int startMonth, int endMonth, int tempMod) {
		name = seasonName;
		num = seasonNum;
		start = startMonth;
		end = endMonth;
		tempModifier = tempMod;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSeasonInt() {
		return num;
	}
	
	public int getStartMonth() {
		return start;
	}
	
	public int getEndMonth() {
		return end;
	}
	
	public int getTempMod() {
		return tempModifier;
	}
}