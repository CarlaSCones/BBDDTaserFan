package server.model.ImplementacionInterfaces;

import model.*;
import server.controllers.VehiculoController;
import server.model.Interfaces.VehiculoService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpVehiculoService implements VehiculoService {

    @Override
    public List<Vehiculo> getAll() {

        List<Vehiculo> vehiculoList = new ArrayList<>();
        TipoVehiculo vehiculos[] = {
                TipoVehiculo.COCHE,
                TipoVehiculo.BICICLETA,
                TipoVehiculo.BICICLETA,
                TipoVehiculo.PATINETE
        };
        for (int i=0;i<4;i++){
            for (Vehiculo vehiculo : getAll(vehiculos[i])){
                vehiculoList.add(vehiculo);
            }
        }
        return vehiculoList;
    }

    @Override
    public List<Vehiculo> getAll(TipoVehiculo tipoVehiculo) {

        List<Vehiculo> vehiculoList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehiculo")){

            String matricula;
            int preciohora;
            String marca;
            String descripcion;
            String color;
            int bateria;
            String estado;
            int idCarnet;
            String changedBy;

            while(resultSet.next()){
                matricula = resultSet.getString("matricula");
                preciohora = resultSet.getInt("preciohora");
                marca = resultSet.getString("marca");
                descripcion = resultSet.getString("descripcion");
                color = resultSet.getString("color").toLowerCase();
                bateria = resultSet.getInt("bateria");
                estado = resultSet.getString("estado").toLowerCase();
                idCarnet = resultSet.getInt("idCarnet");
                changedBy = resultSet.getString("changedBy");

                Color colorV = Color.ROJO;
                switch (color){
                    case "rojo":
                        colorV = Color.ROJO;
                        break;
                    case "amarillo":
                        colorV = Color.AMARILLO;
                        break;
                    case "verde":
                        colorV = Color.VERDE;
                        break;
                    case "azul":
                        colorV = Color.AZUL;
                        break;
                    case "blanco":
                        colorV = Color.BLANCO;
                        break;
                    case "negro":
                        colorV = Color.NEGRO;
                        break;
                }

                Estado estadoV = Estado.PREPARADO;
                switch (estado){
                    case "baja":
                        estadoV = Estado.BAJA;
                        break;
                    case "taller":
                        estadoV = Estado.TALLER;
                        break;
                    case "preparado":
                        estadoV = Estado.PREPARADO;
                        break;
                    case "reservado":
                        estadoV = Estado.RESERVADO;
                        break;
                    case "alquilado":
                        estadoV = Estado.ALQUILADO;
                        break;
                }

                vehiculoList.add(new Vehiculo(matricula,preciohora, marca, descripcion, colorV, bateria, estadoV, idCarnet, changedBy, tipoVehiculo));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehiculoList;
    }

    @Override
    public Result<Vehiculo> get(String matricula) {
        DataSource dataSource = MyDataSource.getMyOracleDataSource();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from vehiculo where matricula='" + matricula + "'")) {

            int preciohora;
            String marca;
            String descripcion;
            String color;
            int bateria;
            String estado;
            int idCarnet;
            String changedBy;

            if (resultSet.next()) {

                matricula = resultSet.getString("matricula");
                preciohora = resultSet.getInt("preciohora");
                marca = resultSet.getString("marca");
                descripcion = resultSet.getString("descripcion");
                color = resultSet.getString("color").toLowerCase();
                bateria = resultSet.getInt("bateria");
                estado = resultSet.getString("estado").toLowerCase();
                idCarnet = resultSet.getInt("idCarnet");
                changedBy = resultSet.getString("changedBy");

                Color colorV = Color.ROJO;
                switch (color){
                    case "rojo":
                        colorV = Color.ROJO;
                        break;
                    case "amarillo":
                        colorV = Color.AMARILLO;
                        break;
                    case "verde":
                        colorV = Color.VERDE;
                        break;
                    case "azul":
                        colorV = Color.AZUL;
                        break;
                    case "blanco":
                        colorV = Color.BLANCO;
                        break;
                    case "negro":
                        colorV = Color.NEGRO;
                        break;
                }

                Estado estadoV = Estado.PREPARADO;
                switch (estado){
                    case "baja":
                        estadoV = Estado.BAJA;
                        break;
                    case "taller":
                        estadoV = Estado.TALLER;
                        break;
                    case "preparado":
                        estadoV = Estado.PREPARADO;
                        break;
                    case "reservado":
                        estadoV = Estado.RESERVADO;
                        break;
                    case "alquilado":
                        estadoV = Estado.ALQUILADO;
                        break;
                }
                TipoVehiculo tipoVehiculo = TipoVehiculo.COCHE;
                switch (estado){
                    case "coche":
                        tipoVehiculo = TipoVehiculo.COCHE;
                        break;
                    case "moto":
                        tipoVehiculo = TipoVehiculo.MOTO;
                        break;
                    case "bicicleta":
                        tipoVehiculo = TipoVehiculo.BICICLETA;
                        break;
                    case "patinete":
                        tipoVehiculo = TipoVehiculo.PATINETE;
                        break;
                }
                Vehiculo vehiculo = new Vehiculo(matricula,preciohora, marca, descripcion, colorV, bateria, estadoV, idCarnet, changedBy, tipoVehiculo);
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
    public Result<Vehiculo> update(Vehiculo vehiculo) {

        DataSource ds = MyDataSource.getMyOracleDataSource();

        String sql = "UPDATE vehiculo SET preciohora=?,marca=?,descripcion=?,color=?,bateria=?,estado=?,idCarnet=?,changedBy=? WHERE matricula LIKE ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            pstmt.setFloat(++pos, vehiculo.getPreciohora());
            pstmt.setString(++pos, vehiculo.getMarca());
            pstmt.setString(++pos, vehiculo.getDescripcion());
            pstmt.setString(++pos, String.valueOf(vehiculo.getColor()));
            pstmt.setInt(++pos, vehiculo.getBateria());
            pstmt.setString(++pos, String.valueOf(vehiculo.getEstado()));
            pstmt.setInt(++pos, vehiculo.getIdCarnet());
            pstmt.setString(++pos, vehiculo.getChangedBy());
            pstmt.setString(++pos, vehiculo.getMatricula());
            int cant = pstmt.executeUpdate();
            if (cant == 1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Ningun vehiculo actualizado", 404);
        } catch (Exception e) {
            return new Result.Error(e.getMessage(),404);
        }
    }

    @Override
    public Result<Vehiculo> add(Vehiculo vehiculo) {
        DataSource ds = MyDataSource.getMyOracleDataSource();


        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();){
            String sql = "INSERT INTO " + "vehiculo VALUES ('" +vehiculo.getMatricula()+ "','"
                    +vehiculo.getPreciohora()+ "','" +vehiculo.getMarca()+ "','" +vehiculo.getDescripcion()+ "','" +vehiculo.getColor()+
                    "','" +vehiculo.getBateria()+"','" + vehiculo.getEstado()+ "','" +vehiculo.getIdCarnet()+ "','" +vehiculo.getChangedBy()+ "')";

            int count = statement.executeUpdate(sql);
            if(count==1)
                return new Result.Success<>(200);
            else
                return new Result.Error("Error al añadir el vehiculo",404);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getMessage(),404);
        }
    }

    @Override
    public Result<Vehiculo> delete(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo WHERE matricula = ?";
        int cant = 0;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            int pos = 0;
            pstmt.setString(++pos, matricula);
            cant = pstmt.executeUpdate();

        } catch (Exception e) {
            return new Result.Error(e.getMessage(), 404);
        }
        if (cant == 1)
            return new Result.Success<>(200);
        else
            return new Result.Error("Ningun vehiculo eliminado", 404);
    }
}
