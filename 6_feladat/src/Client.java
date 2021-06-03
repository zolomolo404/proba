

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client {

	
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
		
		BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		String phonenumber=null;
		String command=null;
		String serverRespone="test";
		while(true) {
			
			System.out.println("> ");
			
			
			
			command=keyboard.readLine();
			
		//	if(command=="quit") {
			//	break;
		//	}
			/*if(splittedrespone[0].equals("OK"))
			{
			
				command=splittedrespone[1] + " " + phonenumber + " " + prices[(int)(Math.random()*prices.length)];
				System.out.println("Client says: " + command);
				out.println(command);
				
			}else {
				phonenumber=command;
				System.out.println("Client says: " + command);
				out.println(command);
			}*/
			
			out.println(command);
			
			
			
			
			serverRespone = input.readLine();
			System.out.println("Server says= " + serverRespone);
		}
		
		
		
		
		
		//socket.close();
		//System.exit(0);
	}

}

