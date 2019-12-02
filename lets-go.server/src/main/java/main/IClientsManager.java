package main;

import java.util.ArrayList;

public interface IClientsManager {

    void addClient(ClientConnectionThread clientConnectionThread);

    ArrayList<ClientConnectionThread> getAllClients();

    ClientConnectionThread getClientWithId(int threadId);
}
