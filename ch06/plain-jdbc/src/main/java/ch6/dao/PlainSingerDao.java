package ch6.dao;

import ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("데이터베이스 드라이버를 로딩할 수 없습니다!", ex);
        }
    }

    // ssl 사용 안하므로 useSSL=false 로 변경
    private Connection getConnnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/musicdb?useSSL=false",
                "prospring5", "prospring5"
        );
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            logger.error("데이터베이스 커넥션을 닫을 때 문제가 발생했습니다!", ex);
        }
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM singer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                result.add(singer);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("SELECT 실행 중 문제 발생!", ex);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public void insert(Singer singer) {
        Connection connection = null;
        try {
            connection = getConnnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Singer (FIRST_NAME, LAST_NAME, BIRTH_DATE)" +
                            " VALUES (?, ?, ?)"
                    , Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            logger.error("INSERT 실행 중 문제 발생!", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long singerId) {
        Connection connection = null;
        try {
            connection = getConnnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM singer WHERE ID=?");
            statement.setLong(1, singerId);
            statement.execute();
        } catch (SQLException ex) {
            logger.error("DELETE 실행 중 문제 발생!", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        throw new NotImplementedException("findByFirstName");
    }

    @Override
    public String findFirstNameById(Long id) {
        throw new NotImplementedException("findFirstNameById");
    }

    @Override
    public String findLastNameById(Long id) {
        throw new NotImplementedException("findLastNameById");
    }

    @Override
    public void update(Singer singer) {
        throw new NotImplementedException("update");
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        throw new NotImplementedException("findAllWithAlbums");
    }

    @Override
    public void insertWithAlbum(Singer singer) {
        throw new NotImplementedException("insertWithAlbum");
    }

    @Override
    public String findNameById(Long id) {
        throw new NotImplementedException("findNameById");
    }
}
