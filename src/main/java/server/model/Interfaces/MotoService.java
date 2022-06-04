package server.model.Interfaces;

import model.Coche;
import model.Moto;
import model.Result;

import java.util.List;

public interface MotoService {
    List<Moto> getAll();
    Result<Moto> get(String matricula);
    Result<Moto> update(Moto moto);
    Result<Moto> add(Moto moto);
    Result<Moto> delete(String matricula);
}
