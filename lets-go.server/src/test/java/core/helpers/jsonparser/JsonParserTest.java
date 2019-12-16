package core.helpers.jsonparser;

import contract.ActionDTO;
import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import main.helpers.jsonparser.JsonParser;
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

        String json = "{\"actionType\":\"STARTGAME\",\"boardSize\":\"NINE\"}";

        ActionDTO result = jsonParser.parseJsonToAction(json);

        ActionDTO expected = new ActionDTO(BoardSize.NINE);
        assertEquals(expected.getActionType(), result.getActionType());
        assertEquals(expected.getBoardSize(), result.getBoardSize());

    }

    @Test
    public void parseActionToJson_ParseCorrectly() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.SERVER_ERROR);

        String result = jsonParser.parseResponseToJson(responseDTO);

        assertEquals("{\"responseType\":\"SERVER_ERROR\"}", result);
    }
}
