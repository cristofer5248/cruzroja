/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.reporte;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sv.cruzroja.www.entities.BeneficiadosEntity;

/**
 *
 * @author crist
 */
public class grafica {

    /*public List<BeneficiadosEntity> listar() throws SQLException, ClassNotFoundException {
        List<BeneficiadosEntity> lista = null;
        ResultSet rs;
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cruzroja?user=root&password=");
            PreparedStatement st = cn.prepareStatement("select datosbeneficiados.nombres, COUNT(datosbeneficiados.edad) as veces, datosbeneficiados.edad, beneficiados.idproyecto from beneficiados inner join lugarproyecto ON lugarproyecto.idl = beneficiados.idproyecto inner join lugar ON lugar.idlugar = lugarproyecto.idlugar inner join datosbeneficiados ON datosbeneficiados.idusuario = beneficiados.idbeneficiado where datosbeneficiados.edad<100 and lugarproyecto.idl = 'APP123'  group by datosbeneficiados.edad");
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                BeneficiadosEntity bene = new BeneficiadosEntity();
                System.out.println(bene.getIdb());
            }
            rs.close(); 

        } catch (Exception e) {

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
    }*/

}
