package controlador.factory;

import java.sql.Connection;
import java.sql.SQLException;
import controlador.pool.BasicConnectionPool;
import modelo.dao.MataderoDAO;
import modelo.dao.TratarDAO;
import modelo.dao.VacaDAO;
import modelo.dao.VeterinarioDAO;

public class MySQLDAOFactory extends DAOFactory {

    final static String user = "root";
    //final static String password = "abc123.";
    final static String password = "root";
    final static String BD = "vacas"; //Indica aqui la BD 
    //final static String IP = "127.0.0.1"; //Indica aqui la IP 
    final static String IP = "192.168.56.117";
    final static String url = "jdbc:mysql://" + IP + ":3306/" + BD;

    static BasicConnectionPool bcp;


    public MySQLDAOFactory() {

        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    @Override
    public int getSize() {
        return bcp.getSize();
    }
    //add getUser, getURL....

    @Override
    public void shutdown() throws SQLException {
        bcp.shutdown();
    }

    @Override
    public VacaDAO getVacaDAO() {
        return new VacaDAO();
    }

    @Override
    public VeterinarioDAO getVeterinarioDAO() {
        return new VeterinarioDAO();
    }

    @Override
    public MataderoDAO getMataderoDAO() {
        return new MataderoDAO();
    }

    @Override
    public TratarDAO getTratarDAO() {
        return new TratarDAO();
    }
   

}
