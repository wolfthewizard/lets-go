package core.serversender;

import core.FrontendManager;
import core.IFrontendManager;
import core.contract.ResponseDTO;

public class ServerResponseListener implements OnServerResponseListener {

    private IFrontendManager frontendManager;

    public ServerResponseListener(FrontendManager frontendManager) {
        this.frontendManager = frontendManager;
    }

    @Override
    public void responseReceived(ResponseDTO responseDTO) {

    }
}
