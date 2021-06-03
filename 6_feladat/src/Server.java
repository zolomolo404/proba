


import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server 
{
	
	private static final int PORT = 9090;
	
	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	//private static ArrayList<requestHandler> requests = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(10);
	
	public static void main(String args[]) throws IOException
	{
		ServerSocket listener = new ServerSocket(PORT);
		
		while(true) 
		{
			System.out.println("[SERVER] Waiting fot the client connection...");
			Socket client =listener.accept();
			System.out.println("[SERVER] Connected the client!");
			ClientHandler clientThread = new ClientHandler(client);
			//requestHandler request  = new requestHandler(client);
			clients.add(clientThread);
			//requests.add(request);
			
			pool.execute(clientThread);
			//pool.execute(request);
		}		
	}	
}
