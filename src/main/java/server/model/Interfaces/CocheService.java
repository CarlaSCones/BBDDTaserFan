package server.model.Interfaces;

import model.Coche;
import model.Result;

import java.util.List;

public interface CocheService {
    List<Coche> getAll();
    Result<Coche> get(String matricula);
    Result<Coche> update(Coche coche);
    Result<Coche> add(Coche coche);
    Result<Coche> delete(String matricula);
}
