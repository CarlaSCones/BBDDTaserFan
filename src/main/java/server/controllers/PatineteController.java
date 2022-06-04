package server.controllers;

import model.Coche;
import model.Patinete;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.ImplementacionInterfaces.ImpCocheService;
import server.model.ImplementacionInterfaces.ImpPatineteService;
import server.model.Interfaces.CocheService;
import server.model.Interfaces.PatineteService;
import server.model.JsonTransformer;
import spark.Request;
import spark.Response;

import java.util.List;

public class PatineteController {
    static Logger logger = LoggerFactory.getLogger(PatineteController.class);

    private static PatineteService service = new ImpPatineteService();

    private static JsonTransformer<Patinete> jsonTransformer = new JsonTransformer<>();

    public static List<Patinete> getPatinetes(Request req, Response res){
        logger.info("Recibiendo solicitud de todos los patinetes...");
        return service.getAll();
    }

    public static Result<Patinete> getPatinete(Request req, Response res) {
        String matricula= req.queryParams("matricula");
        logger.info("Consiguiendo patinete con matricula = " + matricula + "...");

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

    public static Result<Patinete> addPatinete(Request request, Response response) {
        logger.info("Añadiendo nuevo patinete...");
        String body = request.body();
        Patinete patinete = jsonTransformer.getObjet(body, Patinete.class);
        Result<Patinete> result =  service.add(patinete);
        response.type("application/json");

        if(result instanceof Result.Success)
            response.status(200);
        else{
            Result.Error error= (Result.Error) result;
            response.status(error.getCode());
        }

        return result;
    }

    public static Result<Patinete> updatePatinete(Request req, Response res) {
        logger.info("Actualizando patinete...");
        String body = req.body();

        Patinete patinete = jsonTransformer.getObjet(body, Patinete.class);
        Result<Patinete> result = service.update(patinete);
        res.type("application/json");

        if(result instanceof Result.Success)
            res.status(200);
        else{
            Result.Error error= (Result.Error) result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Patinete> deletePatinete(Request req, Response res) {
        String matricula = req.queryParams("matricula");
        logger.info("Eliminando patinete con matricula " + matricula + "...");

        Result<Patinete> result = service.delete(matricula);
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
