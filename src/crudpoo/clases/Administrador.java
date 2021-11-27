package crudpoo.clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Administrador extends Persona{
    String password;
    

    public Administrador(int id, int cedula, String nombre, String apellido, String cargo, String password) {
        super(id, cedula, nombre, apellido, cargo);
        this.password = password;
    }
    
    //funcion para encriptar la contraseña
    public String encriptar(String contraseña){
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
    
}
