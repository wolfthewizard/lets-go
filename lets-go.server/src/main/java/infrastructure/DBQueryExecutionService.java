package infrastructure;

import core.model.entities.GameEntity;
import infrastructure.services.IDBQueryExecutionService;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class DBQueryExecutionService implements IDBQueryExecutionService {

    private final Session session;

    public DBQueryExecutionService(){
        session =  new Configuration().configure()
                .buildSessionFactory().openSession();
    }

    @Override
    public void addGameToDB(GameEntity gameEntity) {

        session.save(gameEntity);
    }

    @Override
    public void updateGameInDB(GameEntity gameEntity) {

        session.update(gameEntity);
    }

    @Override
    public GameEntity getGameFromDB(int gameId) {

        return (GameEntity)session.get(GameEntity.class, gameId);
    }


}
