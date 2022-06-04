package server.model.ImplementacionInterfaces;

import model.MyDataSource;
import model.Patinete;
import model.Result;
import server.model.Interfaces.PatineteService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpPatineteService implements PatineteService {

    @Override
    public List<Patinete> getAll() {

        List<Patinete> patineteList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from patinete")){

            String matricula;
            int numRuedas;
            int tamanyo;

            while(resultSet.next()){
                matricula = resultSet.getString("matricula");
                numRuedas = resultSet.getInt("numRuedas");
                tamanyo = resultSet.getInt("tamanyo");
                patineteList.add(new Patinete(matricula,numRuedas,tamanyo));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patineteList;
    }

    @Override
    public Result<Patinete> get(String matricula) {

        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from patinete where matricula='" + matricula + "'")) {

            int numRuedas;
            int tamanyo;

            if (resultSet.next()) {

                numRuedas = resultSet.getInt("numRuedas");
                tamanyo = resultSet.getInt("tamanyo");

                Patinete patinete = new Patinete(matricula, numRuedas, numRuedas);
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
    public Result<Patinete> update(Patinete patinete) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE patinete SET numRuedas=?,tamanyo=? WHERE matricula LIKE ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            pstmt.setInt(++pos, patinete.getNumRuedas());
            pstmt.setInt(++pos, patinete.getTamanyo());
            pstmt.setString(++pos, patinete.getMatricula());
            int cant = pstmt.executeUpdate();
            if (cant == 1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ningun patinete actualizado", 404);
        } catch (Exception e) {
            return new Result.Error(e.getMessage(),404);
        }
    }

    @Override
    public Result<Patinete> add(Patinete patinete) {
        DataSource ds = MyDataSource.getMyOracleDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();){
            String sql = "INSERT INTO " + "patinete VALUES ('" +patinete.getMatricula()+ "','"
                    +patinete.getNumRuedas()+ "','" +patinete.getTamanyo()+ "')";

            int count = statement.executeUpdate(sql);
            if(count==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Error al añadir el patinete",404);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getMessage(),404);
        }
    }

    @Override
    public Result<Patinete> delete(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM patinete WHERE matricula = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            pstmt.setString(++pos, matricula);
            int res = pstmt.executeUpdate();

            if(res==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ningun patinete eliminado",404);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(e.getMessage(),404);
        }
    }
}
