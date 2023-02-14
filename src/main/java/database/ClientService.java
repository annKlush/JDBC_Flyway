package database;

import table.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement setNewName;
    private PreparedStatement deletById;
    private PreparedStatement allClient;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO client (id, name) VALUES(?, ?)");
        getByIdSt = connection.prepareStatement(
                "SELECT id, name FROM client WHERE id = ?");
        selectMaxIdSt = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM client"
        );
        setNewName = connection.prepareStatement(
                "UPDATE client SET name = ? WHERE id=?"
        );
        deletById = connection.prepareStatement(
                "DELETE FROM client WHERE id =?"
        );
        allClient = connection.prepareStatement(
                "SELECT id, name FROM client"
        );
    }

    public long create(long ig, String name) throws SQLException {
        createSt.setLong(1, ig);
        createSt.setString(2, name);
        createSt.executeUpdate();
        long id;
        try (ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
        }
        return id;
    }

    public String getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);
        try (ResultSet rs = getByIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Client result = new Client();
            result.setId(id);
            result.setName(rs.getString("name"));
            return result.getName();
        }
    }

    public void setName(long id, String name) throws SQLException {
        setNewName.setString(1, name);
        setNewName.setLong(2, id);
        setNewName.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deletById.setLong(1, id);
        deletById.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        try (ResultSet rs = allClient.executeQuery()) {
            List<Client> result = new ArrayList<>();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                result.add(client);
            }
            return result;
        }
    }
}
