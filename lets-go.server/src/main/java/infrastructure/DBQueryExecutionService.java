package infrastructure;

import core.model.entities.ChangeEntity;
import core.model.entities.GameEntity;
import core.model.entities.TurnEntity;
import infrastructure.services.IDBQueryExecutionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class DBQueryExecutionService implements IDBQueryExecutionService {

    private final SessionFactory sessionFactory;

    public DBQueryExecutionService(){
//        Configuration configuration = new Configuration();
//        // Hibernate settings equivalent to hibernate.cfg.xml's properties
//        Properties properties = new Properties();
//        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useSSL=false");
//        properties.put(Environment.USER, "root");
//        properties.put(Environment.PASS, "root");
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
//        configuration.setProperties(properties);
//        configuration.addAnnotatedClass(GameEntity.class);
//        configuration.addAnnotatedClass(TurnEntity.class);
//        configuration.addAnnotatedClass(ChangeEntity.class);
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        /*
        sessionFactory =  new AnnotationConfiguration().configure()
                .buildSessionFactory();
        */
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(GameEntity.class);
        configuration.addAnnotatedClass(TurnEntity.class);
        configuration.addAnnotatedClass(ChangeEntity.class);
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//
//            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    @Override
    public void addGameToDB(GameEntity gameEntity) {

        Session session = sessionFactory.openSession();

        session.save(gameEntity);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void updateGameInDB(GameEntity gameEntity) {

        Session session = sessionFactory.openSession();

        session.update(gameEntity);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public GameEntity getGameFromDB(int gameId) {

        Session session = sessionFactory.openSession();

        GameEntity gameEntity = session.get(GameEntity.class, gameId);

        session.close();

        return gameEntity;
    }


}
