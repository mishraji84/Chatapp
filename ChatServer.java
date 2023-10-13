import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    ServerSocket skt ;
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    public ChatServer()
    {
       try {
        skt = new ServerSocket(888);
        socket = skt.accept();
        sendToClient(socket);
        receivedFromClient(socket);
       } catch (Exception e) {
        e.printStackTrace();
       } 
    }

    public void receivedFromClient(Socket socket){
        Runnable r1 = ()->{
            try {
            while(true){
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = br.readLine();
            System.out.println("Message received by Clinet::" +msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        };
        
new Thread(r1).start();
    }

public void sendToClient(Socket socket){
    Runnable r2 = ()->{
        try {
        while(true)
        {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        String content = br1.readLine();
        pw = new PrintWriter(socket.getOutputStream());
        pw.println(content);
        pw.flush();

    }
 } catch (Exception e) {
        e.printStackTrace();
    }
    };
    new Thread(r2).start();
}


   public static void main(String[] args) {
    System.out.println("Server is ready to accept request");
    ChatServer cs = new ChatServer();
   } 
}
