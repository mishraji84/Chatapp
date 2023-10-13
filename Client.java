import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Client {

    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    public Client()
    {
        try {
            System.out.println("sending request to server");
            socket = new Socket("localhost",7777);
            System.out.println("Message sent to server");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//tap socket stream to read Inputsream sent by Server 
            pw = new PrintWriter(socket.getOutputStream());//pw will hold data and ready to send to the Server
        startReading();
        startWriting();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void startReading()
    {
        Runnable r1 = ()->{
            System.out.println("Reader started");
            try {
                while (true){

                String msg = br.readLine(); //taking request from Server
                if(msg.equalsIgnoreCase("quit"))
                {
                    System.out.println("Server has terminated the chat");
                    break;
                }
                else 
                {
                    System.out.println("Server::"+msg);
                }
            }
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        

        };
        new Thread(r1).start();
    }

public void startWriting()
    {
       Runnable r2 = ()->{
        try {
            while(true){
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            String content = br1.readLine();
            pw.println(content);
            pw.flush();

        }
     } catch (Exception e) {
            e.printStackTrace();
        }
       };
       new Thread(r2).start();
    }


    public static void main(String args[])
    {   

        System.out.println("this is client.. going to start server");
        Client c = new Client();
    }
}