package core.helpers;

import contract.ActionDTO;
import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private JsonParser jsonParser;

    @BeforeEach
    public void setUp(){
        jsonParser = new JsonParser();
    }

    @Test
    public void parseJsonToResponse_ParseCorrectly() {

        String json = "{ \"responseType\": \"SERVER_ERROR\" }";

        ResponseDTO result = jsonParser.parseJsonToResponse(json);

        ResponseDTO expected = new ResponseDTO(ResponseType.SERVER_ERROR);
        assertEquals(expected.getResponseType(), result.getResponseType());
        assertEquals( expected.getChanges(), result.getChanges());

    }

    @Test
    public void parseActionToJson_ParseCorrectly() {

        ActionDTO actionDTO = new ActionDTO(BoardSize.NINE);

        String result = jsonParser.parseActionToJson(actionDTO);

        assertEquals(result, "{\"actionType\":\"STARTGAME\",\"boardSize\":\"NINE\"}");
    }
}
