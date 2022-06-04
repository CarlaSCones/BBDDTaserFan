package server.model.ImplementacionInterfaces;

import model.*;
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

                Color colorCoche = Color.ROJO;
                switch (color){
                    case "rojo":
                        colorCoche = Color.ROJO;
                        break;
                    case "amarillo":
                        colorCoche = Color.AMARILLO;
                        break;
                    case "verde":
                        colorCoche = Color.VERDE;
                        break;
                    case "azul":
                        colorCoche = Color.AZUL;
                        break;
                    case "blanco":
                        colorCoche = Color.BLANCO;
                        break;
                    case "negro":
                        colorCoche = Color.NEGRO;
                        break;
                }

                Estado estadoCoche = Estado.PREPARADO;
                switch (estado){
                    case "baja":
                        estadoCoche = Estado.BAJA;
                        break;
                    case "taller":
                        estadoCoche = Estado.TALLER;
                        break;
                    case "preparado":
                        estadoCoche = Estado.PREPARADO;
                        break;
                    case "reservado":
                        estadoCoche = Estado.RESERVADO;
                        break;
                    case "alquilado":
                        estadoCoche = Estado.ALQUILADO;
                        break;
                }
                vehiculoList.add(new Vehiculo(matricula,preciohora,marca));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehiculoList;
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

    //No
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
