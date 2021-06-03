
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ClientHandler implements Runnable
{
	 
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	private static String[] phonenumbers = {"06201234567", "06301234567", "06501234567", "06701234567"};
	List<String> phonumberList = new ArrayList<>(Arrays.asList(phonenumbers));
	
	
	public ClientHandler(Socket clientSocket) throws IOException 
	{
		this.client=clientSocket;
		in=new BufferedReader(new InputStreamReader(client.getInputStream()));
		out= new PrintWriter(client.getOutputStream(), true);
	}
	@Override
	public void run() 
	{
		try 
		{
			int code = 0;
			String phoneNumber = " ";
			while(true) 
			{
				String request= in.readLine();				
				String[] splittedrequest = request.split(" ");			
				if(phonumberList.contains(splittedrequest[0]))
				{
					code=(int)(Math.random()*(9999-1000+1)+1000);
					phoneNumber=request;
					out.println("OK " + code);					
				}else if(splittedrequest[0].equals(String.valueOf(code)) && splittedrequest[1].equals(phoneNumber) )
				{	
				//	if(splittedrequest[1].equals(phoneNumber))
				//	{					
						out.println(splittedrequest[2]);
						save(splittedrequest[1], splittedrequest[2] + code);
						
					//}
				
				}else if(splittedrequest[0].equals("list") && phonumberList.contains(splittedrequest[1])) 
				{
					out.println(send(splittedrequest[1]));
				}
				else 
				{
					//out.println("request " + request + " code: " + code + " phonenumber " + phoneNumber + splittedrequest[0]);
					out.println("ERROR");
				}				
			}
		}catch (IOException e) 
		{
			System.err.println("Ioexception  in client handler");
			System.err.println(e.getStackTrace());
		} finally
		{
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}	
	}


	void save(String phoneNumber,String price) {
		
	
			try (FileWriter writer = new FileWriter("data/" + phoneNumber + ".txt", true ); BufferedWriter b = new BufferedWriter(writer); PrintWriter p = new PrintWriter(b);){
				
					p.println(price);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		

	}
	
	public static String send(String phoneNumber) {
		
		
		String data = "";
		String line;
		File file = new File("data/" + phoneNumber +".txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
			    data= data + line + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*try {
			File myObj = new File("data/" + phoneNumber +".txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				data = data + line + "\n";
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("server> An error occurred.");
			e.printStackTrace();
		}*/
		
		return data;
	

	
		
	

}
	

}
