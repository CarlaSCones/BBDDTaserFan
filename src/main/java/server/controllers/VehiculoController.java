package server.controllers;

import model.Result;
import model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.JsonTransformer;
import server.model.ImplementacionInterfaces.ImpVehiculoService;
import server.model.Interfaces.VehiculoService;
import spark.Request;
import spark.Response;

import java.util.List;

public class VehiculoController {
    static Logger logger = LoggerFactory.getLogger(VehiculoController.class);

    private static VehiculoService service = new ImpVehiculoService();

    private static JsonTransformer<Vehiculo> jsonTransformer = new JsonTransformer<>();

    public static List<Vehiculo> getVehiculos(Request req, Response res){
        logger.info("Receiving request for all vehiculos");
        return service.getAll();
    }

   /* public static Result<Vehiculo> getVehiculo(Request req, Response res) {
        String matricula= req.queryParams("matricula");
        logger.info("Get person with matricula = " + matricula);

        Result result = service.getVehiculo(matricula);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }
*/
    public static Result<Vehiculo> addVehiculo(Request request, Response response) {
        logger.info("Add new vehiculo");
        String body = request.body();
        Vehiculo v = (Vehiculo) jsonTransformer.getObject(body,Vehiculo.class);
        Result<Vehiculo> result =  service.add(v);
        response.type("application/json");

        if(result instanceof Result.Success)
            response.status(200);
        else{
            Result.Error error= (Result.Error) result;
            response.status(error.getCode());
        }

        return result;
    }

    public static Result<Vehiculo> updateVehiculo(Request req, Response res) {
        logger.info("Updating vehiculo ");
        Vehiculo v = (Vehiculo) jsonTransformer.getObject(req.body(), Vehiculo.class);
        Result<Vehiculo> result = service.update(v);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Vehiculo> deleteVehiculo(Request req, Response res) {

        logger.info("Request vehiculo by matricula: " + req.queryParams("matricula") );
        String matricula = req.queryParams("matricula");
        Result<Vehiculo> result = service.delete(matricula);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }
}
