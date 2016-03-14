package title;

import java.util.UUID;

public class IdGenerator {
	public static String generate(Product product) {
		String randomID = UUID.randomUUID().toString();
		String id = "";
		if (product instanceof Movie) {
			id += "MOV" + randomID;
		} else if (product instanceof Game) {
			id += "GAM" + randomID;
		} else if (product instanceof Book) {
			id += "BOO" + randomID;
		}
		return id;
	}
}
