package database;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void initDb() {
        Flyway flyway = Flyway
                .configure()
                .dataSource("jdbc:h2:./test", null, null)
                .load();

        flyway.migrate();
    }
}
