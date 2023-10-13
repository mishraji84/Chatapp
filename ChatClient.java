import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
    Socket socket;
    public ChatClient()
    {
    try {
        socket = new Socket("localhost",888);
        //pw.println(socket.getOutputStream());
        sendToServer(socket);
        receivedFromServer(socket);
        
    } catch (Exception e) {
    
        e.printStackTrace();
    } 
    }
public void sendToServer(Socket sc){
    Runnable r1 = ()->{
        while(true){
           
        try {
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            String content = br1.readLine();
           PrintWriter pw = new PrintWriter(sc.getOutputStream());
            pw.println(content);
            pw.flush();   
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    };
    new Thread(r1).start();
    }

    public void receivedFromServer(Socket socket){
       Runnable r2 = ()->{
         try {
            while(true){
           BufferedReader  br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = br1.readLine();
            System.out.println("Message received by Server::" +msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       };
       new Thread(r2).start();

    }

    public static void main(String[] args) {
        System.out.println("Client is ready to send request");
        ChatClient cc = new ChatClient();
    }
}
