package server.model.Interfaces;

import model.AuthenticateData;
import model.Empleado;
import model.Result;

import java.util.List;

public interface EmpleadoService {

    Result<Empleado> authenticate(AuthenticateData authenticateData);
    List<Empleado> getAll();

}