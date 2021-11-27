package crudpoo.clases;

import crudpoo.clases.Usuario;
import crudpoo.clases.Vehiculos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost/crudpoo";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    Connection con;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public Conexion() {
        try {
            this.con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            //handle any errors
            mensajeSQLException(ex);
        }
    }
    
    public String obtenerNombreIngresado(int cedula) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nombre, apellido FROM usuario WHERE cedula=" + cedula +" AND cargo='Admin'");
            rs.next();
            do{
                return rs.getString("nombre") + " " +rs.getString("apellido");
            }while(rs.next());
        } catch (SQLException ex) {
            mensajeSQLException(ex);
        }
        return null;
    }
    
    public boolean verificarDatosIngresados(int cedula, String password) throws SQLException {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT cedula, contraseña FROM usuario WHERE cedula=" + cedula + " AND contraseña='" + password + "' AND cargo=" + "'Admin'");
            rs.next();
            do {
                int cedulaObtenida = rs.getInt("cedula");
                String pass = rs.getString("contraseña");
                if (cedulaObtenida == cedula && pass.equals(password)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Cedula o contraseña erroneos! ");
                    return false;
                }
            } while (rs.next());
            
        } catch (SQLException ex) {
            //handle any errors
            JOptionPane.showMessageDialog(null, "No se han encontrado los datos!");
            mensajeSQLException(ex);
        }
        return false;
    }
    
    public void insertarEnBaseDeDatos(String nombre, String apellido, String cargo, int cedula, String correo, String contraseña) throws SQLException {
        try {
            ps = con.prepareStatement("INSERT INTO usuario (nombre, apellido, cargo, cedula, correo, contraseña) VALUES (?,?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, cargo);
            ps.setInt(4, cedula);
            ps.setString(5, correo);
            ps.setString(6, contraseña);
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Los datos han sido registrados correctamente! ");
            } else {
                JOptionPane.showMessageDialog(null, "No ha sido posible registrar los datos! ");
            }
        } catch (SQLException ex) {
            //handle any errors
            mensajeSQLException(ex);
        }
    }
    private ArrayList<Usuario> users = new ArrayList<>();
    
    public ArrayList traerUsuarios() throws SQLException {
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuario WHERE cargo='usuario'");
            rs.next();
            do {
                int id = rs.getInt("Id_User");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cargo = rs.getString("cargo");
                int cedula = rs.getInt("cedula");
                String correo = rs.getString("correo");
                String contraseña = rs.getString("contraseña");
                Usuario user = new Usuario(id, cedula, nombre, apellido, cargo, correo, contraseña);
                users.add(user);
                System.out.println(user);
            } while (rs.next());
            return users;
        } catch (SQLException ex) {
            //handle any errors
            mensajeSQLException(ex);
        }
        return null;
    }
    
    public void registrarVehiculos(String usuario, String placa, int numeroRuedas, String color, String tipoVehiculo) throws SQLException {
        try {
            ps = con.prepareStatement("INSERT INTO vehiculos (usuario, placa, numero_ruedas, color, tipo_vehiculo) VALUES (?,?,?,?,?)");
            ps.setString(1, usuario);
            ps.setString(2, placa);
            ps.setInt(3, numeroRuedas);
            ps.setString(4, color);
            ps.setString(5, tipoVehiculo);
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Los datos han sido registrados correctamente! ");
            } else {
                JOptionPane.showMessageDialog(null, "No ha sido posible registrar los datos! ");
            }
        } catch (SQLException ex) {
            //handle any errors
            mensajeSQLException(ex);
        }
    }
    
    private void mensajeSQLException(SQLException ex) {
        if (ex.getSQLState().equals("23000")) {
            JOptionPane.showMessageDialog(null, "LA CEDULA SE ENCUENTRA YA REGISTRADA! ");
        }
        System.out.println("SQLException:  " + ex.getMessage());
        System.out.println("SQLState:  " + ex.getSQLState());
        System.out.println("VendorError:  " + ex.getErrorCode());
    }

    //funcion para buscar por placa
    private ArrayList<Vehiculos> listaVehiculos = new ArrayList<>();
    
    public ArrayList buscarPorPlaca(String placa) throws SQLException {
        System.out.println(placa);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM crudpoo.vehiculos WHERE placa='" + placa + "'");
            rs.next();
            do {
                int id = rs.getInt("Id_Vehiculo");
                String usuario = rs.getString("usuario");
                String placa2 = rs.getString("placa");
                int numeroRuedas = rs.getInt("numero_ruedas");
                String color = rs.getString("color");
                String tipoVehiculo = rs.getString("tipo_vehiculo");
                Vehiculos v = new Vehiculos(id, usuario, placa2, numeroRuedas, color, tipoVehiculo);
                listaVehiculos.add(v);
            } while (rs.next());
            return listaVehiculos;
        } catch (SQLException ex) {
            //handle any errors
            mensajeSQLException(ex);
        }
        return null;
    }

    //funcion para actualizar datos;
    public void actualizarInformacionVehiculos(int id, String usuario, String placa, int numRuedas, String color, String tipoVehiculo) throws SQLException {
        try {
            ps = con.prepareStatement("UPDATE vehiculos SET usuario = ?, placa = ?, numero_ruedas = ?, color = ?, tipo_vehiculo = ? WHERE Id_Vehiculo = '" + id + "'");
            ps.setString(1, usuario);
            ps.setString(2, placa);
            ps.setInt(3, numRuedas);
            ps.setString(4, color);
            ps.setString(5, tipoVehiculo);
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "se han actualizado los datos correctamente!");
            }
            
        } catch (SQLException ex) {
            mensajeSQLException(ex);
        }
    }

    //funcion para eliminar datos completos de vehiculos con determinada placa!
    public void eliminarInformacion(String valor, String tabla, String columna) {
        try {
            ps = con.prepareStatement("DELETE FROM " + tabla + " WHERE " + columna + " = ?; ");
            if (columna.equals("placa")) {
                ps.setString(1, valor);
            } else if (columna.equals("cedula")) {
                ps.setInt(1, Integer.parseInt(valor));
            }
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se han eliminado los datos requeridos!");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido eliminar nada");
            }
            
        } catch (SQLException ex) {
            mensajeSQLException(ex);
        }
    }
    //funcion para enviar recibo a la base de datos//
    public void enviarReciboDePago(String usuario, String placa, String tipoVehiculo, String color, int cantidadHoras, int precioPorHora, int totalAPagar) throws SQLException{
        try{
            ps = con.prepareStatement("INSERT INTO recibo (Usuario, Placa, Tipo_Vehiculo, Color, CantidadHoras, PrecioPorHora, TotalAPagar) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1,usuario);
            ps.setString(2,placa);
            ps.setString(3,tipoVehiculo);
            ps.setString(4,color);
            ps.setInt(5,cantidadHoras);
            ps.setInt(6,precioPorHora);
            ps.setInt(7,totalAPagar);
            
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Los datos han sido registrados correctamente! ");
            } else {
                JOptionPane.showMessageDialog(null, "No ha sido posible registrar los datos! ");
            }
            
        }catch(SQLException ex){
            mensajeSQLException(ex);
        }
    }
    
}
