package server.model.ImplementacionInterfaces;

import model.Bicicleta;
import model.MyDataSource;
import model.Result;
import server.model.Interfaces.BicicletaService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpBicletaService implements BicicletaService {
    @Override
    public List<Bicicleta> getAll() {

        List<Bicicleta> bicicletaList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from bicicleta")){

            String matricula;
            String tipoB;

            while(resultSet.next()){
                matricula = resultSet.getString("matricula");
                tipoB = resultSet.getString("tipo");
                bicicletaList.add(new Bicicleta(matricula,tipoB));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bicicletaList;
    }

    @Override
    public Result<Bicicleta> get(String matricula) {
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from bicicleta where matricula='" + matricula + "'")) {

            String tipoB;

            if (resultSet.next()) {

                tipoB = resultSet.getString("tipo");
                Bicicleta bicicleta = new Bicicleta(matricula,tipoB);
                return new Result.Success<>(200);

            } else {
                return new Result.Error("No se ha encontrado la matricula " + matricula, 404);
            }

        }catch (SQLSyntaxErrorException sqlee){
            return new Result.Error("Error de acceso a la BD", 404);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getMessage(),404);
        }

    }

    @Override
    public Result<Bicicleta> update(Bicicleta bicicleta) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE bicicleta SET tipo=? WHERE matricula LIKE ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            if(bicicleta.getMatricula()!=null){
                pstmt.setString(++pos, bicicleta.getTipo());
                pstmt.setString(++pos, bicicleta.getMatricula());
            }
            int cant = pstmt.executeUpdate();
            if (cant == 1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ninguna bicicleta actualizada", 404);
        } catch (Exception e) {
            return new Result.Error(e.getMessage(),404);
        }
    }

    @Override
    public Result<Bicicleta> add(Bicicleta bicicleta) {
        DataSource ds = MyDataSource.getMyOracleDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();){
            String sqlV = "INSERT INTO vehiculo (matricula, precioHora, marca, descripcion, color, bateria, fechaadq, estado, idCarnet) VALUES ('"
                    +bicicleta.getMatricula()+ "','" +bicicleta.getPrecioHora()+ "','" +bicicleta.getMarca()+ "','" +bicicleta.getDescripcion()
                    + "','" +bicicleta.getColor()+ "','" +bicicleta.getBateria()+ "',to_date('" +bicicleta.getFechaadq()+"','YYYY-MM-DD'),'" + bicicleta.getEstado()
                    + "','" +bicicleta.getIdCarnet()+ "')";
            String sqlB = "INSERT INTO " + "bicicleta VALUES ('" +bicicleta.getMatricula()+ "','"
                    +bicicleta.getTipo()+ "')";

            int count = statement.executeUpdate(sqlV);
            int count2 = statement.executeUpdate(sqlB);
            if(count==1 && count2==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Error al añadir la bicicleta",404);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getMessage(),404);
        }
    }

    @Override
    public Result<Bicicleta> delete(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        int cant = 0;
        String sql = "DELETE FROM bicicleta WHERE matricula = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {

            int pos = 0;
            pstmt.setString(++pos, matricula);
            cant = pstmt.executeUpdate();

            if(cant==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ninguna bicicleta eliminada",404);

        } catch (Exception e) {
            return new Result.Error(e.getMessage(),404);
        }
    }
}
