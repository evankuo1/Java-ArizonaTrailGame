import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelControllerTestSuite {

	@Test
	void test() {
		GameState state = new GameState(10);
		assertEquals(10,state.getCurrMonthInt());
		state.addMember("Georg");
		state.addMember("Georg");
		state.addMember("Georg");
		state.addMember("Georg");
		state.addMember("Man");
		state.setHealth("Georg", 500);
		state.incHealth("Georg");
		state.decHealth("Georg");
		state.travelOneDay();
		assertEquals(state.getHealth("Georg"),500);
		assertEquals(state.getNames(),"Georg, Georg, Georg, Georg, Man");
		state.setDead("Man");
		state.incDead();
		assertEquals(state.getNumDead(),1);
		assertEquals(state.getFamilyStatus(),"Good");
		Item rock = new Item("rock", 10, 10);
		state.addItem(rock);
		state.removeItem(rock);
		state.setMoney(1000);
		assertEquals(1000, state.getMoney());
		state.changeMoney(100);
		
		assertEquals(state.getCurrLocation(),state.getCurrLocation());
		assertEquals(state.getCurrYear(),1880);
		assertEquals(state.getCurrMonthStr(),"November");
		assertEquals(state.getcurrDate(),1);
		assertEquals(state.getCurrSeason(),Season.WINTER);
		
		state.incrDay();
		state.setRations("food");
		state.setRations(1);
		assertEquals(state.getRationsConsumed(),30);
		state.setDestination("Tucson");
		state.setTravelSpeed(10);
		state.setTravelSpeed(1);
		assertEquals(state.getSpeed(),0.5);
		assertEquals(state.getSpeedName(),"Slow");
		Location pointA = new Location("pointA",0,0);
		Location pointB = new Location("pointB",1,1);
		assertEquals(state.calcMiles(pointA, pointB),138.0);
		assertEquals(state.getTemperature(),56.5);
	}

}
