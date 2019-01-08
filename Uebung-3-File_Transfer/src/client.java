import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
/**
 * @author Elias
 *Dies ist der Client der eine Verbindung mit den Server aufnimmt, den Server die Adresse des Dokuments, das übertragen wird, übermittelt und anschließen vom Server die Datei Stück für Stück bekommt.
 *Die Datei wird dann in ein neue Datei geschreiben in den Speicherort die der User vorgesehen hat. 
 */
public class client { 
    
    public static void main(String[] args) throws Exception{
        
        //Socket wird Intialisiert mit den Port und der LocalHost Adresse.
        Socket server = new Socket(InetAddress.getByName("localhost"), 5000);
        byte[] contents = new byte[10000];
        
        //Übertragung sowie Holung des Speicherortes sowie 
        PrintStream out = new PrintStream(server.getOutputStream());
        Scanner in = new Scanner(System.in);
        System.out.println("Bitte geben Sie die SpeicherAdresse ein");
        String sourceadd = in.nextLine();
        System.out.println("Bitte geben Sie die ZielAdresse ein");
        String destinationadd = in.nextLine();
        out.println(sourceadd);
        
        //Neuer Speicherort wird angegeben wo das Programm die übertragene Datei Speicehrn soll.
        FileOutputStream fos = new FileOutputStream(destinationadd);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = server.getInputStream();
        InputStream i = server.getInputStream();
        InputStream input = server.getInputStream();
        OutputStream output = server.getOutputStream();
        BufferedOutputStream text = new BufferedOutputStream(fos);
        
        int byteWrite = 0;
        int bytesRead = 0; 
        
        while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead); 
        //Socket, Scanner und so weiter weredn geschlossen.
        bos.flush(); 
        server.close(); 
        out.close();
        in.close();
        System.out.println("File wurde erfolgreich übertragen!");
    }
}