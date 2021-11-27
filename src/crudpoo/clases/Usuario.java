package crudpoo.clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario extends Persona {

    String correo;
    String contraseña;

    public Usuario(int id, int cedula, String nombre, String apellido, String cargo, String correo, String contraseña) {
        super(id, cedula, nombre, apellido, cargo);
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    

    public String encriptar(String contraseña) {
        String passEncriptada = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contraseña.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEncriptada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passEncriptada;
    }

    @Override
    public String toString() {
        return "Usuario{ nombre= " + super.nombre + " Apellido= " + super.apellido + " correo=" + correo + '}';
    }

}
