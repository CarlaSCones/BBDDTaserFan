package server.model.Interfaces;

import model.Result;
import model.TipoVehiculo;
import model.Vehiculo;

import java.util.List;

public interface VehiculoService {
    List<Vehiculo> getAll();
    List<Vehiculo> getAll(TipoVehiculo tipo);
    Result<Vehiculo> update(Vehiculo vehiculo);
    Result<Vehiculo> add(Vehiculo vehiculo);
    Result<Vehiculo> delete(String matricula);
}
