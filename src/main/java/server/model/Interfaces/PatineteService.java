package server.model.Interfaces;

import model.Moto;
import model.Patinete;
import model.Result;

import java.util.List;

public interface PatineteService {
    List<Patinete> getAll();
    Result<Patinete> get(String matricula);
    Result<Patinete> update(Patinete patinete);
    Result<Patinete> add(Patinete patinete);
    Result<Patinete> delete(String matricula);
}
