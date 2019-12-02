import contract.ResponseDTO;
import contract.enums.BoardSize;
import core.serversender.JsonParser;
import core.serversender.OnServerResponseListener;
import core.serversender.ServerCommunicator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServerCommunicator serverCommunicator = new ServerCommunicator(new JsonParser(), new OnServerResponseListener() {
            public void responseReceived(ResponseDTO responseDTO) {
                if(responseDTO == null)
                    System.out.println("go null");
                else
                    System.out.println("got sth else");
            }
        });
        serverCommunicator.sendStartGameMessage(false, BoardSize.NINE);
        Thread.sleep(100);
        serverCommunicator.sendLeaveGameMessage();
        serverCommunicator.shutDownConnection();
    }
}
