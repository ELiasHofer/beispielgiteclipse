import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Elias
 *Das ist der Server er wartet bis ein CLient eine Vernbindugn mit ihn aufnimmt und eine Adress wo sich das Dokument befindet bekommt.
 *Anschließend ladet er das Dokument und verschickt es dann Stück für Stück.
 *Der CLient muss es natüllich auch immer entgegenmehn sonst kann der Server nicht mehr weiter Schicken.
 */
public class server {

	public static void main(String[] args) throws Exception {
		// Socket wird Intitialisiert
		ServerSocket serversocket = new ServerSocket(5000);
		Socket client = serversocket.accept();

		// Die Adresse wird angegeben. Die localhost Adresse ist immer 127.0.0.1.
		InetAddress IA = InetAddress.getByName("localhost");
		
		//Adress wird vom Client empfangen
		Scanner in = new Scanner (client.getInputStream());
		String addres = in.nextLine();

		//Die Adresse wo sich die Datei befindet die Übertragen wird.
		File file = new File(addres);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		OutputStream os = client.getOutputStream();

		// Der Inhalt der Datei wird in ein Array eingelesen.
		byte[] contents;
		long fileLength = file.length();
		long current = 0;

		while (current != fileLength) {
			int size = 10000;
			if (fileLength - current >= size)
				current += size;
			else {
				size = (int) (fileLength - current);
				current = fileLength;
			}
			contents = new byte[size];
			bis.read(contents, 0, size);
			os.write(contents);
		}

		os.flush();
		//Socket und ServerSocket werden geschlossen.
		client.close();
		serversocket.close();
		in.close();
		System.out.println("File wurde erfolgreich übertragen!");
	}
}