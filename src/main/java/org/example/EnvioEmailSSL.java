package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EnvioEmailSSL {
    public static void main(String[] args) {
        //Credenciales del remitente (correo y contraseña)
        final String username = "*******@gmail.com";
        final String password = "*******************";

        //configuro las propiedades para smtp de gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //Servidor SMTP de gmail
        props.put("mail.smtp.port", "465"); //Puerto SMTP seguro para SSL
        props.put("mail.smtp.auth", "true"); //Habilita autenticacion
        props.put("mail.smtp.socketFactory.port", "465"); //Configuro el puerto de socket factory
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //Uso SSL para la conexion

        //creo Una sesion Con Autenticacion
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            //creo el mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); //Remitente del correo
            message.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("************.com") //Destinatario
            );
            message.setSubject("Prueba de envio de correo con java"); //Asunto del correo
            message.setText("Hola \n\nCoreo de pueba enviado desde java"); //Contenido del correo

            //envio mensaje
            Transport.send(message);
            System.out.println("Correo enviado Correctamente");
        } catch (MessagingException e){
            e.printStackTrace(); //Manejo de errores si falla el envio del correo.
        }
    }
}


///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// */
//
//package com.mycompany.conexionservidor4;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.SocketException;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTPReply;
//
///**
// *
// * @author yosoy
// */
//public class ConexionServidor4 {
//
//    public static void main(String[] args) throws SocketException, IOException {
//        FTPClient cliente = new FTPClient();
//        String usuario = "user2"; // Ajusta a tus necesidades
//        String contrasena = "maikel"; // Ajusta a tus necesidades
//        String serverFTP = "127.0.0.1"; // Ajusta a tus necesidades
//
//        try {
//            System.out.println("Nos conectamos a: " + serverFTP);
//            cliente.connect(serverFTP, 21);
//            int replyCode = cliente.getReplyCode();///obtener el codgio de respuesta del servidor
//
//            if (!FTPReply.isPositiveCompletion(replyCode)) {
//                System.out.println("Operación fallida. Código de respuesta: " + replyCode);
//                return;
//            }
//
//            boolean success = cliente.login(usuario, contrasena);
//            if (!success) {
//                System.out.println("No se puede entrar al servidor.");
//                return;
//            }
//            System.out.println("Login correcto...");
//
//            // Crear un directorio en UsuarioA
//            String directorioUsuarioA = "UsuarioA/DirectorioCreado";
//            if (cliente.makeDirectory(directorioUsuarioA)) {
//                System.out.println("Directorio creado exitosamente: " + directorioUsuarioA);
//            } else {
//                System.out.println("Fallo al crear directorio. Código de respuesta: " + cliente.getReplyCode());
//            }
//
//            // Listar los directorios y archivos en Examen
//            FTPFile[] archivosEnExamen = cliente.listFiles("Examen/");
//            System.out.println("Ficheros en el directorio 'Examen': " + archivosEnExamen.length);
//            for (FTPFile archivo : archivosEnExamen) {
//                String tipo = archivo.isDirectory() ? "=> Directorio" : "=> Fichero";
//                System.out.println("  " + archivo.getName() + " " + tipo);
//            }
//
//            // Subir un archivo a UsuarioA
//            FileInputStream input = new FileInputStream("ArchivoSubir.txt");
//            if (cliente.storeFile("UsuarioA/ArchivoSubir.txt", input)) {
//                System.out.println("Archivo subido exitosamente.");
//            } else {
//                System.out.println("Error al subir el archivo. Código de respuesta: " + cliente.getReplyCode());
//            }
//            input.close();
//
//            // Renombrar el archivo subido
//            if (cliente.rename("UsuarioA/ArchivoSubir.txt", "UsuarioA/ArchivoRenombrado.txt")) {
//                System.out.println("Archivo renombrado exitosamente.");
//            } else {
//                System.out.println("Error al renombrar el archivo. Código de respuesta: " + cliente.getReplyCode());
//            }
//
//            // Descargar el archivo Archivo.txt
//            FileOutputStream output = new FileOutputStream("ArchivoDescargado.txt");
//            if (cliente.retrieveFile("Archivo.txt", output)) {
//                System.out.println("Archivo descargado exitosamente.");
//            } else {
//                System.out.println("Error al descargar el archivo. Código de respuesta: " + cliente.getReplyCode());
//            }
//            output.close();
//
//            // Eliminar el archivo Archivo.txt
//            if (cliente.deleteFile("Archivo.txt")) {
//                System.out.println("Archivo eliminado exitosamente.");
//            } else {
//                System.out.println("Error al eliminar el archivo. Código de respuesta: " + cliente.getReplyCode());
//            }
//
//            // Cerrar sesión y desconectar
//            cliente.logout();
//            System.out.println("Logout del servidor FTP...");
//            cliente.disconnect();
//            System.out.println("Desconectado...");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cliente.isConnected()) {
//                cliente.disconnect();
//            }
//        }
//    }
//}
