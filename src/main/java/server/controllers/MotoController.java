package server.controllers;

import model.Moto;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.ImplementacionInterfaces.ImpMotoService;
import server.model.Interfaces.MotoService;
import server.model.JsonTransformer;
import spark.Request;
import spark.Response;

import java.util.List;

public class MotoController {
    static Logger logger = LoggerFactory.getLogger(MotoController.class);

    private static MotoService service = new ImpMotoService();

    private static JsonTransformer<Moto> jsonTransformer = new JsonTransformer<>();

    public static List<Moto> getMotos(Request req, Response res){
        logger.info("Recibiendo solicitud de todas las motos...");
        return service.getAll();
    }

    public static Result<Moto> getMoto(Request req, Response res) {
        String matricula= req.queryParams("matricula");
        logger.info("Consiguiendo moto con matricula = " + matricula + "...");

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

    public static Result<Moto> addMoto(Request request, Response response) {
        logger.info("Añadiendo nueva moto...");
        String body = request.body();
        Moto moto = jsonTransformer.getObjet(body, Moto.class);
        Result<Moto> result =  service.add(moto);
        response.type("application/json");

        if(result instanceof Result.Success)
            response.status(200);
        else{
            Result.Error error= (Result.Error) result;
            response.status(error.getCode());
        }

        return result;
    }

    public static Result<Moto> updateMoto(Request req, Response res) {
        logger.info("Actualizando moto...");
        String body = req.body();

        Moto moto = jsonTransformer.getObjet(body, Moto.class);
        Result<Moto> result = service.update(moto);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Moto> deleteMoto(Request req, Response res) {
        String matricula = req.queryParams("matricula");
        logger.info("Eliminando moto con matricula " + matricula + "...");

        Result<Moto> result = service.delete(matricula);
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
