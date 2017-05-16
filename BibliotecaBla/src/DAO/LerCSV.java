/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author tads
 */
public class LerCSV {

    public void inportarCSV() throws Exception {
        String path = System.getProperty("user.home");
        String barra = System.getProperty("file.separator");

        System.out.println(path);

        /*String data = "25/01/2016";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data,formatter);
        
        System.out.println(date.getYear());*/
        HashMap retorno = new HashMap();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("/local/home/tads/NetBeansProjects/"
                + "BibliotecaBla/BibliotecaBla/BibliotecaBla/src/livroCSV/testes.ser"));
        retorno = (HashMap) in.readObject();
        in.close();
        System.out.println(retorno.get("bla"));
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.home");
        String barra = System.getProperty("file.separator");
        HashMap param = new HashMap();
        param.put("bla", "ble");

        System.out.println(path + barra + "NetBeansProjects" + barra + "BibliotecaBla" + barra + "BibliotecaBla" + barra
                + "BibliotecaBla" + barra + "src" + barra + "livroCSV" + barra + "04-final-livros.csv");
        try {
            /*ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("/local/home/tads/NetBeansProjects/"
                    + "BibliotecaBla/BibliotecaBla/BibliotecaBla/src/livroCSV/testes.ser"));
            ob.writeObject(param);
            ob.flush();
            ob.close();*/

        } catch (Exception ex) {
            Logger.getLogger(LerCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
