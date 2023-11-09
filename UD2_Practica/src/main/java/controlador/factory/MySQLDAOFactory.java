package controlador.factory;

import java.sql.Connection;
import java.sql.SQLException;
import controlador.pool.BasicConnectionPool;
import modelo.dao.ClienteDAO;
import modelo.dao.DetalleDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.FacturaDAO;
import modelo.dao.HistoricoDAO;
import modelo.dao.ProductoDAO;

public class MySQLDAOFactory extends DAOFactory {

    final static String user = "root";
    final static String password = "root";
    final static String BD = "practicaud2"; //Indica aqui la BD 
    final static String IP = "192.168.56.117"; //Indica aqui la IP 
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
   
    //IMPLEMENTACION DE METODOS ABSTRACTOS

    @Override
    public ClienteDAO getClienteDAO() {return new ClienteDAO();}
    @Override
    public DetalleDAO getDetalleDAO() {return new DetalleDAO();}
    @Override
    public EmpleadoDAO getEmpleadoDAO() {return new EmpleadoDAO();}
    @Override
    public FacturaDAO getFacturaDAO() {return new FacturaDAO();}
    @Override
    public HistoricoDAO getHistoricoDAO() {return new HistoricoDAO();}
    @Override
    public ProductoDAO getProductoAO() {return new ProductoDAO();}
}
