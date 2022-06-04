package server.model.ImplementacionInterfaces;

import model.Moto;
import model.MyDataSource;
import model.Result;
import server.model.Interfaces.MotoService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpMotoService implements MotoService {

    @Override
    public List<Moto> getAll() {

        List<Moto> motoList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from moto")){

            String matricula;
            int velocidadMax;
            int cilindrada;

            while(resultSet.next()){

                matricula = resultSet.getString("matricula");
                velocidadMax = resultSet.getInt("velocidadMax");
                cilindrada = resultSet.getInt("cilindrada");
                motoList.add(new Moto(matricula,velocidadMax,cilindrada));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return motoList;
    }

    @Override
    public Result<Moto> get(String matricula) {
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from moto where matricula='" + matricula + "'")) {

            int velocidadMax;
            int cilindrada;

            if (resultSet.next()) {

                velocidadMax = resultSet.getInt("velocidadMax");
                cilindrada = resultSet.getInt("cilindrada");
                Moto moto = new Moto(matricula,velocidadMax,cilindrada);
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
    public Result<Moto> update(Moto moto) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE moto SET velocidadMax=?,cilindrada=? WHERE matricula LIKE ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            pstmt.setInt(++pos, moto.getVelocidadMax());
            pstmt.setInt(++pos, moto.getCilindrada());
            pstmt.setString(++pos, moto.getMatricula());
            int cant = pstmt.executeUpdate();
            if (cant == 1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ninguna moto actualizada", 404);
        } catch (Exception e) {
            return new Result.Error(e.getMessage(),404);
        }
    }

    @Override
    public Result<Moto> add(Moto bicicleta) {
        DataSource ds = MyDataSource.getMyOracleDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();){
            String sql = "INSERT INTO " + "moto VALUES ('" +bicicleta.getMatricula()+ "','"+bicicleta.getVelocidadMax()+ "','"
                    +bicicleta.getCilindrada()+ "')";

            int count = statement.executeUpdate(sql);
            if(count==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Error al añadir la moto",404);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getMessage(),404);
        }
    }

    @Override
    public Result<Moto> delete(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM moto WHERE matricula = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            pstmt.setString(++pos, matricula);
            int res = pstmt.executeUpdate();

            if(res==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ninguna moto eliminada",404);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(e.getMessage(),404);
        }
    }
}
