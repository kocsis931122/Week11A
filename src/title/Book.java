package title;

import java.io.Serializable;

public class Book extends Product implements Serializable {
	Person author;

	public Book(String title, Person person, Person author) {
		super(title, person);
		this.author = author;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public long getInvestment() {
		return author.getSalary();
	}

	public String toString() {
		return "Id: " + id + "\nTitle: " + title + "\nPerson: " + person + "\nAuthor: " + author.firstName + " "
				+ author.lastName + "\nSalary: " + author.salary;
	}
}
