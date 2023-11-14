/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador.pool;

import java.sql.Connection;
import java.sql.SQLException;

interface ConnectionPool {

    Connection getConnection() throws SQLException;

    boolean releaseConnection(Connection connection);

    String getUrl();

    String getUser();

    String getPassword();
}
