package core.serversender;

import core.contract.ResponseDTO;

public interface OnServerResponseListener {//bez I bo to bedzie implementowala klasa ktora ogarnia ten napis na gorze

    void responseReceived(ResponseDTO responseDTO);
}
