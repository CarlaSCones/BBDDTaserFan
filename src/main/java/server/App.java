package server;

import server.controllers.*;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {

        // Oracle
        post(API.Routes.AUTHENTICATE, EmpleadoController::authenticate, new JsonTransformer<>());
        //Autenticar persona
        //localhost:4567/authenticate
        //body/raw:
        //{
        //    email:"pepa@mordor.es",
        //    password:"1111"
        //}

        //Tabla coche
        get(API.Routes.COCHES, CocheController::getCoches, new JsonTransformer<>());
        //localhost:4567/coches

        get(API.Routes.COCHE, CocheController::getCoche,new JsonTransformer<>());
        //localhost:4567/coche?matricula=0000AAA

        post(API.Routes.COCHE, CocheController::addCoche, new JsonTransformer<>());
        //localhost:4567/coche
        //body/raw:{
        //    matricula: '0000ABA',
        //    numPlazas: 5,
        //    numPuertas: 4
        //}
        //

        put(API.Routes.COCHE, CocheController::updateCoche,new JsonTransformer<>());
        //localhost:4567/coche
        //body/raw:{
        //    matricula: '0000ABA',
        //    numPlazas: 5,
        //    numPuertas: 4
        //}
        //

        delete(API.Routes.COCHE, CocheController::deleteCoche, new JsonTransformer<>());
        //localhost:4567/coche?matricula=0000AAA


        //Tabla Vehiculo
        get(API.Routes.VEHICULOS, VehiculoController::getVehiculos, new JsonTransformer<>());
        //localhost:4567/vehiculos

        //get(API.Routes.VEHICULO, VehiculoController::getVehiculo,new JsonTransformer<>());
        //localhost:4567/vehiculo?matricula=0000AAA

        post(API.Routes.VEHICULO, VehiculoController::addVehiculo, new JsonTransformer<>());
        //localhost:4567/vehiculo
        //body/raw:
        //    matricula: '0123plj',
        //    preciohora: 5,
        //    marca: 'Ford',
        //    color: 'rojo',
        //    bateria: 45,
        //    fechaadq: '07/01/21',
        //    estado: 'preparado',
        //    idCarnet: 2,
        //    changedBy: 'initial'
        //}
        //

        put(API.Routes.VEHICULO, VehiculoController::updateVehiculo,new JsonTransformer<>());
        //localhost:4567/vehiculo
        //body/raw:
        //    matricula: '0123plj',
        //    preciohora: 5,
        //    marca: 'Ford',
        //    color: 'rojo',
        //    bateria: 45,
        //    fechaadq: '07/01/21',
        //    estado: 'preparado',
        //    idCarnet: 2,
        //    changedBy: 'initial'
        //}
        //
        delete(API.Routes.VEHICULO, VehiculoController::deleteVehiculo, new JsonTransformer<>());

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
