package server.model.ImplementacionInterfaces;

import model.AuthenticateData;
import model.Empleado;
import model.MyDataSource;
import model.Result;
import server.model.Interfaces.EmpleadoService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpEmpleadoService implements EmpleadoService {
    @Override
    public Result<Empleado> authenticate(AuthenticateData authenticateData) {

        String email = authenticateData.getEmail();
        String password = authenticateData.getPassword();
        String sql = "SELECT * FROM EMPLEADO WHERE " +
                "EMAIL=? AND PASSWORD=ENCRYPT_PASWD.encrypt_val(?)";

        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            int pos = 0;
            pstmt.setString(++pos,email);
            pstmt.setString(++pos,password);

            ResultSet rs = pstmt.executeQuery();

            String nombre;
            String apellidos;
            String dni;
            String domicilio;
            String cp;

            if(rs.next()){
                nombre = rs.getString("NOMBRE");
                apellidos = rs.getString("APELLIDOS");
                dni = rs.getString("DNI");
                domicilio = rs.getString("DOMICILIO");
                cp = rs.getString("CP");

                Empleado e = new Empleado(nombre, apellidos, dni,email, domicilio,cp);
                return new Result.Success<>(e);

            } else
                return new Result.Error("Email y/o password incorrectos.", 404);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getMessage(), 404);
        }

    }

    @Override
    public List<Empleado> getAll() {

        List<Empleado> empleadoList = new ArrayList<>();
        DataSource ds = MyDataSource.getMyOracleDataSource();

        try(Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from empleado")) {

            String nombre;
            String apellidos;
            String dni;
            String email;
            String domicilio;
            String cp;

            if(rs.next()){
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                dni = rs.getString("dni");
                email = rs.getString("email");
                domicilio = rs.getString("domicilio");
                cp = rs.getString("cp");

                empleadoList.add(new Empleado(nombre, apellidos, dni, email, domicilio, cp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleadoList;
    }
}
