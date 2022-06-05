package server.controllers;

import model.AuthenticateData;
import model.Empleado;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.JsonTransformer;
import server.model.Interfaces.EmpleadoService;
import server.model.ImplementacionInterfaces.ImpEmpleadoService;
import spark.Request;
import spark.Response;

public class EmpleadoController {

    static Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
    static JsonTransformer<Empleado> jse = new JsonTransformer<>();
    static EmpleadoService service = new ImpEmpleadoService();


    public static Result<Empleado> authenticate(Request request, Response response) {
        logger.info("Autenticando...");
        String body = request.body();

        JsonTransformer<AuthenticateData> jst = new JsonTransformer<>();
        AuthenticateData ad = (AuthenticateData) jst.getObject(body,AuthenticateData.class);

        Result<Empleado> result = service.authenticate(ad);

        if(result instanceof Result.Success){
            response.status(200);
        } else {
            response.status(404);
        }
        return result;
    }
}
