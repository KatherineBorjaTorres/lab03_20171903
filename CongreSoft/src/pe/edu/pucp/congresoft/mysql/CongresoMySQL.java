
package pe.edu.pucp.congresoft.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.congresoft.config.DBManager;
import pe.edu.pucp.congresoft.dao.CongresoDAO;
import pe.edu.pucp.congresoft.model.Congreso;


public class CongresoMySQL implements CongresoDAO {

    @Override
    public int insertar(Congreso congreso) {
        int resultado = 0;
        try{
        //Registrar el Driver Conexion
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecer la conexión
        Connection con = 
        DriverManager.getConnection(DBManager.urlMySQL, 
                DBManager.user, DBManager.password);
        String sql = "INSERT INTO CONGRESO(NOMBRE,"
                + "FECHA_INICIO,FECHA_FIN,PAIS, ACTIVO) VALUES(?,?,?,?, ?)";
        
        PreparedStatement ps = con.prepareStatement(sql);
        
        boolean activo = congreso.isActivo();
        ps.setString(1, congreso.getNombre());
        ps.setDate(2, congreso.getFechaInicio());
        ps.setDate(3, congreso.getFechaFin());
        ps.setString(4, congreso.getPais());
        if(activo){
            ps.setInt(5, 1);
        }
        else{
            ps.setInt(5, 0);
        }
                
        //Ejecutar la sentencia SQL
        resultado = ps.executeUpdate();
        //Cerrar la conexión
        con.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int actualizar(Congreso congreso) {
        int resultado = 0;
        try{
            //Registrar el Driver Conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecer la conexión
            Connection con = 
            DriverManager.getConnection(DBManager.urlMySQL, 
                    DBManager.user, DBManager.password);
            String sentencia = "UPDATE CONGRESO "
                    + "SET NOMBRE=?,"
                + "SET FECHA_INICIO=?,"
                    + "SET FECHA_FIN=?,"
                    + "SET PAIS=?,"
                    + "SET ACTIVO=?"
                    + "WHERE ID_CONGRESO=?";
            PreparedStatement pst = con.prepareStatement("sentencia");
            pst.setString(1, congreso.getNombre()); 
            pst.setDate(2, congreso.getFechaInicio()); 
            pst.setDate(3, congreso.getFechaFin()); 
            pst.setString(4, congreso.getPais());
            if(congreso.isActivo()){
                pst.setInt(5, 1);
            }
            else{
                pst.setInt(5, 0);
            }
            pst.setInt(6, congreso.getIdCongreso()); 
            //executeQuery > SELECT
            //executeUpdate > INSERT, UPDATE, DELETE
            resultado = pst.executeUpdate();           
            
            //cerrar la conexion
            con.close();
            
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            
        }
        return resultado;
    }

    @Override
    public int eliminar(int idCongreso) {
        int resultado = 0;
        try{
            //Registrar el Driver Conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecer la conexión
            Connection con = 
            DriverManager.getConnection(DBManager.urlMySQL, 
                    DBManager.user, DBManager.password);
            String sentencia = "UPDATE CONGRESO SET ACTIVO=0 WHERE ID_CONGRESO=?";
            PreparedStatement pst = con.prepareStatement("sentencia");
            pst.setInt(1, idCongreso); 
            //executeQuery > SELECT
            //executeUpdate > INSERT, UPDATE, DELETE
            resultado = pst.executeUpdate();           
            
            //cerrar la conexion
            con.close();
            
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            
        }
        return resultado;
    }

    @Override
    public ArrayList<Congreso> listar() {
        ArrayList<Congreso> congresos = new ArrayList<>();
        try{
            //Registrar el JAR de conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecer una conexión a la BD
            Connection con = DriverManager.
//                    getConnection("jdbc:mysql://"+
//         "lp2mysql.c77e2n0iwfgi.us-east-1.rds.amazonaws.com"+
//                  ":3306/inf282", "admin", "abcd1234");
          getConnection(DBManager.urlMySQL, DBManager.user, DBManager.password);
            //Ejecutar una sentencia
            String sentencia = "SELECT * FROM CONGRESO WHERE ACTIVO=1";
            Statement st = con.createStatement();
            //executeQuery -> SELECT
            //executeUpdate -> INSERT, UPDATE, DELETE
            ResultSet rs = st.executeQuery(sentencia);
            //Recorrer todas las filas que devuelve la ejecucion sentencia
            while(rs.next()){
                Congreso congreso = new Congreso();
                congreso.setIdCongreso(rs.getInt("ID_CONGRESO"));
                congreso.setNombre(rs.getString("NOMBRE"));
                congreso.setFechaInicio(rs.getDate("FECHA_INICIO"));
                congreso.setFechaFin(rs.getDate("FECHA_FIN"));
                congreso.setPais(rs.getString("PAIS"));
                if(rs.getInt("ACTIVO") == 1){
                    congreso.setActivo(true);
                }
                congresos.add(congreso);
            }
            //cerrar conexion
            con.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        //Devolviendo los empleados
        return congresos;
    }
    
}
