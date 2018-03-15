/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author luis
 */
public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private static final Connection INSTANCE = new Connection();
    private final Datastore datastore;
    private final ConfigDTO config;

    private Connection() {
        ConfigReader reader = new ConfigReader();
        this.config = reader.getConfiguration();
        if (this.config != null) {
            MongoClient mongoClient;
            MongoClientOptions mongoOptions = MongoClientOptions.builder().socketTimeout(config.getSocketTimeout().intValue()).connectTimeout(config.getConnectionTimeout().intValue()).build();
            try {
                MongoClientURI uri = new MongoClientURI("mongodb://developers.2017:Espe.2017@10.1.0.93:27017/developersEspe?authSource=admin");
                //MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/dbStrategicPlanning");
                mongoClient = new MongoClient(uri);
                //mongoClient = new MongoClient(new ServerAddress(config.getHost(), config.getPort().intValue()), mongoOptions);
            } catch (Exception e) {
                throw new RuntimeException("Error initializing MongoDB", e);
            }
            Morphia morphia = new Morphia();
            for (String packageN : this.config.getPackages()) {
                morphia.mapPackage(packageN, true);
            }
            this.datastore = morphia.createDatastore(mongoClient, this.config.getDatabase());
            this.datastore.ensureIndexes();
            LOG.log(Level.INFO, "Connection: {0} initialized", this.config.toString());
        } else {
            LOG.info("Configuracion invalida, persistencia no disponible.");
            this.datastore = null;
        }
    }

    public static Connection instance() {
        return INSTANCE;
    }

    public Datastore getDatabase() {
        return this.datastore;
    }
}
