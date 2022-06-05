package server;

import server.controllers.*;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {

        post(API.Routes.AUTHENTICATE, EmpleadoController::authenticate, new JsonTransformer<>());

        get(API.Routes.VEHICULOS, VehiculoController::getVehiculos, new JsonTransformer<>());
        get(API.Routes.VEHICULO, VehiculoController::getVehiculo,new JsonTransformer<>());
        post(API.Routes.VEHICULO, VehiculoController::addVehiculo, new JsonTransformer<>());
        put(API.Routes.VEHICULO, VehiculoController::updateVehiculo,new JsonTransformer<>());
        delete(API.Routes.VEHICULO, VehiculoController::deleteVehiculo, new JsonTransformer<>());

        get(API.Routes.COCHES, CocheController::getCoches, new JsonTransformer<>());
        get(API.Routes.COCHE, CocheController::getCoche,new JsonTransformer<>());
        post(API.Routes.COCHE, CocheController::addCoche, new JsonTransformer<>());
        put(API.Routes.COCHE, CocheController::updateCoche,new JsonTransformer<>());
        delete(API.Routes.COCHE, CocheController::deleteCoche, new JsonTransformer<>());

        get(API.Routes.BICICLETAS, BicicletaController::getBicicletas, new JsonTransformer<>());
        get(API.Routes.BICICLETA, BicicletaController::getBicicleta,new JsonTransformer<>());
        post(API.Routes.BICICLETA, BicicletaController::addBicicleta, new JsonTransformer<>());
        put(API.Routes.BICICLETA, BicicletaController::updateBicicleta,new JsonTransformer<>());
        delete(API.Routes.BICICLETA, BicicletaController::deleteBicicleta, new JsonTransformer<>());

        get(API.Routes.MOTOS, MotoController::getMotos, new JsonTransformer<>());
        get(API.Routes.MOTO, MotoController::getMoto,new JsonTransformer<>());
        post(API.Routes.MOTO, MotoController::addMoto, new JsonTransformer<>());
        put(API.Routes.MOTO, MotoController::updateMoto,new JsonTransformer<>());
        delete(API.Routes.MOTO, MotoController::deleteMoto, new JsonTransformer<>());

        get(API.Routes.PATINETES, PatineteController::getPatinetes, new JsonTransformer<>());
        get(API.Routes.PATINETE, PatineteController::getPatinete,new JsonTransformer<>());
        post(API.Routes.PATINETE, PatineteController::addPatinete, new JsonTransformer<>());
        put(API.Routes.PATINETE, PatineteController::updatePatinete,new JsonTransformer<>());
        delete(API.Routes.PATINETE, PatineteController::deletePatinete, new JsonTransformer<>());

    }

}
