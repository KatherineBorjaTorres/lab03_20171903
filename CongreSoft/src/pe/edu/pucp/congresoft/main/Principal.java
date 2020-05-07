
package pe.edu.pucp.congresoft.main;

import java.util.ArrayList;
import pe.edu.pucp.congresoft.dao.CongresoDAO;
import pe.edu.pucp.congresoft.model.Congreso;
import pe.edu.pucp.congresoft.mysql.CongresoMySQL;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Principal {
    public static void main(String[] args){
//        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        
        int resultado;
        
        ArrayList<Congreso> congresos = new ArrayList<>();

        CongresoDAO daoCongreso = new CongresoMySQL();

        congresos = daoCongreso.listar();

        for(Congreso c : congresos){
            System.out.println(c.mostrarDatos());
        }
        
        Congreso congreso = new Congreso("INTERACT 2021",Date.valueOf("2021-01-01") ,Date.valueOf("2021-01-05") ,"BOLIVIA", true);
        resultado= daoCongreso.insertar(congreso);
        
        congresos = daoCongreso.listar();

        for(Congreso c : congresos){
            System.out.println(c.mostrarDatos());
        }
    }
}
    
