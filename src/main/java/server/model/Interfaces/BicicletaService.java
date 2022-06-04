package server.model.Interfaces;

import model.Bicicleta;
import model.Coche;
import model.Result;

import java.util.List;

public interface BicicletaService {

    List<Bicicleta> getAll();
    Result<Bicicleta> get(String matricula);
    Result<Bicicleta> update(Bicicleta bicicleta);
    Result<Bicicleta> add(Bicicleta bicicleta);
    Result<Bicicleta> delete(String matricula);
}
