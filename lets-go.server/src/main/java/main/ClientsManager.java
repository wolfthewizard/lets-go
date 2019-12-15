package main;

import java.util.ArrayList;

public class ClientsManager implements IClientsManager {

    private ArrayList<ClientConnectionThread> clients = new ArrayList<>();

    public void addClient(ClientConnectionThread clientConnectionThread) {
        clients.add(clientConnectionThread);
    }

    @Override
    public ArrayList<ClientConnectionThread> getAllClients() {
        return clients;
    }

    public ClientConnectionThread getClientWithId(int threadId) {
        for (ClientConnectionThread thread: clients) {
            if(thread.getThreadId() == threadId) {
                return thread;
            }
        }
        return null;
    }
}
