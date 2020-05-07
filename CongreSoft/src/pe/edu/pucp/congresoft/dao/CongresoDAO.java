
package pe.edu.pucp.congresoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.congresoft.model.Congreso;


public interface CongresoDAO {
    int insertar(Congreso congreso);
    int actualizar(Congreso congreso);
    int eliminar(int idCongreso);
    ArrayList<Congreso> listar();
}
