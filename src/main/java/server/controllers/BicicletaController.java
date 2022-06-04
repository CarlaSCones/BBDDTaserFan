package server.controllers;

import model.Bicicleta;
import model.Moto;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.ImplementacionInterfaces.ImpBicletaService;
import server.model.ImplementacionInterfaces.ImpMotoService;
import server.model.Interfaces.BicicletaService;
import server.model.Interfaces.MotoService;
import server.model.JsonTransformer;
import spark.Request;
import spark.Response;

import java.util.List;

public class BicicletaController {

    static Logger logger = LoggerFactory.getLogger(BicicletaController.class);

    private static BicicletaService service = new ImpBicletaService();

    private static JsonTransformer<Bicicleta> jsonTransformer = new JsonTransformer<>();

    public static List<Bicicleta> getBicicletas(Request req, Response res){
        logger.info("Recibiendo solicitud de todas las bicicletas...");
        return service.getAll();
    }

    public static Result<Bicicleta> getBicicleta(Request req, Response res) {
        String matricula= req.queryParams("matricula");
        logger.info("Consiguiendo bicicleta con matricula = " + matricula + "...");

        Result result = service.get(matricula);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Bicicleta> addBicicleta(Request request, Response response) {
        logger.info("Añadiendo nueva bicicleta...");
        String body = request.body();
        Bicicleta bicicleta = jsonTransformer.getObjet(body, Bicicleta.class);
        Result<Bicicleta> result =  service.add(bicicleta);
        response.type("application/json");

        if(result instanceof Result.Success)
            response.status(200);
        else{
            Result.Error error= (Result.Error) result;
            response.status(error.getCode());
        }

        return result;
    }

    public static Result<Bicicleta> updateBicicleta(Request req, Response res) {
        logger.info("Actualizando bicicleta...");
        String body = req.body();

        Bicicleta bicicleta = jsonTransformer.getObjet(body, Bicicleta.class);
        Result<Bicicleta> result = service.update(bicicleta);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Bicicleta> deleteBicicleta(Request req, Response res) {
        String matricula = req.queryParams("matricula");
        logger.info("Eliminando bicicleta con matricula " + matricula + "...");

        Result<Bicicleta> result = service.delete(matricula);
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
