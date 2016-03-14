package title;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RentManager {
	public static int buyableCalculator(List<Buyable> buyables) {
		int money = 0;
		for (Buyable buyable : buyables) {
			money += buyable.getPrice();
		}
		return money;
	}

	public static void main(String[] args) {
		// Person who rent everything
		Person pali = new Person("Nagy", "Pali", Gender.MALE, 100);

		// Book authors
		Person suzanneCollins = new Person("Suzanne", "Collins", Gender.FEMALE, 3000);
		Person jodiPicoult = new Person("Jodi", "Picoult", Gender.FEMALE, 5000);

		// Game1 staff

		Person gabeNewell = new Person("Gabe", "Newell", Gender.MALE, 15000);
		Person tonyCurran = new Person("Tony", "Curran", Gender.MALE, 2500);
		Person jonCurry = new Person("Jon", "Curray", Gender.MALE, 3000);
		Person jessCliffe = new Person("Jess", "Cliffe", Gender.MALE, 5500);
		// Game2 staff
		Person steveAckrich = new Person("Steve", "Ackrich", Gender.MALE, 8883);
		Person markRubin = new Person("Mark", "Rubin", Gender.MALE, 5432);

		// Movie1 cast
		Person sandraBullock = new Person("Sandra", "Bullock", Gender.FEMALE, 7555);
		Person timMcgraw = new Person("Tim", "McGraw", Gender.MALE, 4333);
		// Movie2 cast
		Person leonadroDicaprio = new Person("Leonardo", "DiCaprio", Gender.MALE, 9888);
		Person tomHardy = new Person("Tom", "Hardy", Gender.MALE, 5884);

		// Game1 staff in list
		List<Person> csGOStaff = new ArrayList<Person>();
		csGOStaff.add(gabeNewell);
		csGOStaff.add(tonyCurran);
		csGOStaff.add(jonCurry);
		csGOStaff.add(jessCliffe);
		// Game2 staff in list
		List<Person> callofduty2List = new ArrayList<Person>();
		callofduty2List.add(steveAckrich);
		callofduty2List.add(markRubin);
		// Movie1 cast in list
		List<Person> theRevenantList = new ArrayList<Person>();
		theRevenantList.add(leonadroDicaprio);
		theRevenantList.add(tomHardy);
		// Movie2 cast in list
		List<Person> theBlindSideList = new ArrayList<Person>();
		theBlindSideList.add(sandraBullock);
		theBlindSideList.add(timMcgraw);

		////////////////////
		Product theRevenant = new Movie("The Revenant", pali, Genre.DRAMA, 165, 8.9, theRevenantList, 15);
		theRevenant.setId(IdGenerator.generate(theRevenant));
		Product theBlindSide = new Movie("The Blind Side", pali, Genre.DRAMA, 111, 7.7, theBlindSideList, 12);
		theBlindSide.setId(IdGenerator.generate(theBlindSide));
		Product theHungerGames = new Book("The Hunger Games", pali, suzanneCollins);
		theHungerGames.setId(IdGenerator.generate(theHungerGames));
		Product mySistersKeeper = new Book("My Sister's Keeper", pali, jodiPicoult);
		mySistersKeeper.setId(IdGenerator.generate(mySistersKeeper));
		Product csgo = new Game("Counter Strike: Global Offensive", pali, true, csGOStaff, 10);
		csgo.setId(IdGenerator.generate(csgo));
		Product callofduty2 = new Game("Call of Duty 2", pali, false, callofduty2List, 6);
		callofduty2.setId(IdGenerator.generate(callofduty2));

		List<Buyable> buyables = new ArrayList<Buyable>();
		buyables.add((Game) csgo);
		buyables.add((Game) callofduty2);
		buyables.add((Movie) theBlindSide);
		buyables.add((Movie) theRevenant);

		// PRINT
		System.out.println(buyableCalculator(buyables));
		System.out.println(theRevenant);
		System.out.println(theHungerGames);
		System.out.println(csgo);
		System.out.println(callofduty2);

		try {
			Socket clientSocket = new Socket("localhost", 5111);
			System.out.println("Connected to Server\n");
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			Thread.sleep(1000);
			send(outToServer, Command.PUT);
			send(outToServer, pali);
			send(outToServer, Command.GET);
			send(outToServer, Command.EXIT);
			clientSocket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void send(ObjectOutputStream x, Object object) throws IOException {
		x.write(0);
		x.writeObject(object);
	}
}
