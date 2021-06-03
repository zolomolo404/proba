

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ATM {
/*	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	private static String[] phonenumbers = {"06201234567", "06301234567", "06501234567", "06701234567", "06101234567"};
	private static String[] prices = {"3000", "5000", "10000", "15000"};
	
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	BufferedReader input = new BufferedReader( new InputStreamReader(System.in));
	
	Client()
	{
		
	}
	
	void run()  {
		try {
			requestSocket = new Socket(SERVER_IP, SERVER_PORT);
			
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			in = new ObjectInputStream(requestSocket.getInputStream());
			

			String phonenumber=null;
			String command=null;
			String serverRespone="test";
			while(true) {
				
				System.out.println("> ");
				
				String[] splittedrespone = serverRespone.split(" ");	
				
				command=phonenumbers[(int)(Math.random()*phonenumbers.length)];
				
			//	if(command=="quit") {
				//	break;
			//	}
				if(splittedrespone[0].equals("OK"))
				{
				
					command=splittedrespone[1] + " " + phonenumber + " " + prices[(int)(Math.random()*prices.length)];
					System.out.println("Client says: " + command);
					
					
				}else {
					phonenumber=command;
					System.out.println("Client says: " + command);
					out.writeObject(command);
					out.flush();
				}
				
				
				
				
				
				
				serverRespone = input.readLine();
				System.out.println("Server says= " + serverRespone);
				TimeUnit.SECONDS.sleep(10);
			}
			
		}catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4: Kapcsolat zárása
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		Client client = new Client();
		client.run();
	}*/

	
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
	private static String[] phonenumbers = {"06201234567", "06301234567", "06501234567", "06701234567", "06101234567"};
	private static String[] prices = {"3000", "5000", "10000", "15000"};
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
			
			String[] splittedrespone = serverRespone.split(" ");	
			
			command=phonenumbers[(int)(Math.random()*phonenumbers.length)];
			
		//	if(command=="quit") {
			//	break;
		//	}
			if(splittedrespone[0].equals("OK"))
			{
			
				command=splittedrespone[1] + " " + phonenumber + " " + prices[(int)(Math.random()*prices.length)];
				System.out.println("Client says: " + command);
				out.println(command);
				
			}else {
				phonenumber=command;
				System.out.println("Client says: " + command);
				out.println(command);
			}
			
			
			
			
			
			
			serverRespone = input.readLine();
			System.out.println("Server says= " + serverRespone);
			TimeUnit.SECONDS.sleep(5);
		}
		
		
		
		
		
		//socket.close();
		//System.exit(0);
	}

}


