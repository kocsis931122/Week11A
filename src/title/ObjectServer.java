package title;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {
	ServerMode mode = ServerMode.LOAD;
	static FileOutputStream fos;
	static ObjectOutputStream oos;

	public ObjectServer() {
		System.out.printf("Startig server.\nDefault server mode: " + mode.toString() + "\n");
		try {
			ServerSocket serverSocket = new ServerSocket(5111);
			System.out.println("Waiting for clients...");
			Socket socket = serverSocket.accept();

			ObjectOutputStream objectOutData = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInData = new ObjectInputStream(socket.getInputStream());
			System.out.println("Client connected");

			while (true) {
				if (objectInData.read() > -1) {
					Object objectRead = objectInData.readObject();
					if (objectRead instanceof Command && ((Command) objectRead) == Command.EXIT) {
						System.out.println("EXIT");
						break;
					} else if (objectRead instanceof Command && ((Command) objectRead) == Command.GET) {
						mode = ServerMode.LOAD;
						System.out.println("Load data from file to client.");
					} else if (objectRead instanceof Command && ((Command) objectRead) == Command.PUT) {
						mode = ServerMode.SAVE;
						System.out.println("Switch to save mode.");
						System.out.printf("Server mode: " + mode + "\n");

					} else if (mode == ServerMode.SAVE) {
						System.out.println("Save client's data to file.");
						// oos.writeObject(objectFromClient);
						save(objectRead);
					}

				}

			}
			serverSocket.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ServerMode getMode() {
		return mode;
	}

	public void setMode(ServerMode mode) {
		this.mode = mode;
	}

	public static List<Object> load() throws IOException, ClassNotFoundException

	{
		List<Object> Objects = new ArrayList<>();

		try {
			FileInputStream fis = new FileInputStream("kukac.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (true) {
				try {
					Objects.add(ois.readObject());
				} catch (Exception e) {
					break;
				}
			}
			ois.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Objects;
	}

	public static void save(Object o) {
		try {

			FileOutputStream fos = new FileOutputStream("kukac.ser", true);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ObjectServer();
	}
}
