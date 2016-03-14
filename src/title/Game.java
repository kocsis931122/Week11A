package title;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game extends Product implements Buyable, Serializable {
	boolean preOrdered;
	List<Person> staff = new ArrayList<Person>();
	int price;

	public Game(String title, Person person, boolean preOrdered, List<Person> staff, int price) {
		super(title, person);
		this.preOrdered = preOrdered;
		this.staff = staff;
		this.price = price;
	}

	public boolean isPreOrdered() {
		return preOrdered;
	}

	public void setPreOrdered(boolean preOrdered) {
		this.preOrdered = preOrdered;
	}

	public List<Person> getStaff() {
		return staff;
	}

	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	public int getPrice() {
		if (preOrdered) {
			price = (int) (price * 0.8);
		}
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getInvestment() {
		long sumSalary = 0;
		for (Person person : staff) {
			sumSalary += person.getSalary();
		}
		return sumSalary;
	}

	public String toString() {
		return "\nId: " + id + "\nTitle: " + title + "\nPerson: " + person + "\nPreordered: " + preOrdered + "\nStaff: "
				+ staff + "\nPrice: " + price + "\nInvestment: " + getInvestment();
	}

}
