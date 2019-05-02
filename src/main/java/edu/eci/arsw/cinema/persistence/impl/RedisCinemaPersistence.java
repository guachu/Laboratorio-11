/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.util.RedisMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 2124203
 */
public class RedisCinemaPersistence implements CinemaPersitence{

    @Autowired
    RedisMethods jedis;
    
    private final Map<String,Cinema> cinemas=new HashMap<>();
    
    
    public RedisCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);
        functionDate = "2018-12-18 15:30";
        try {
           //LOAD DATA FROM REDIS
           funct1.setSeats(jedis.getSeatsRedis("cinemaX",funct1));
           funct2.setSeats(jedis.getSeatsRedis("cinemaX",funct2));
        } catch (Exception ex) {
           Logger.getLogger(RedisCinemaPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        functions= new ArrayList<>();
        funct1 = new CinemaFunction(new Movie("Mora y el despertar de las moras","Action"),functionDate);
        funct2 = new CinemaFunction(new Movie("MoraYa","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        c=new Cinema("cineMorado",functions);
        cinemas.put("cineMorado", c);
        functionDate = "2018-12-18 15:30";
        try {
           //LOAD DATA FROM REDIS
           funct1.setSeats(jedis.getSeatsRedis("cineMorado",funct1));
           funct2.setSeats(jedis.getSeatsRedis("cineMorado",funct2));
        } catch (Exception ex) {
           Logger.getLogger(RedisCinemaPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        functions= new ArrayList<>();
        funct1 = new CinemaFunction(new Movie("Missigno","Action"),functionDate);
        funct2 = new CinemaFunction(new Movie("detective Pikachu","Aventura"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        c=new Cinema("cineDorado",functions);
        cinemas.put("cineDorado", c);
        try {
           //LOAD DATA FROM REDIS
           funct1.setSeats(jedis.getSeatsRedis("cineDorado",funct1));
           funct2.setSeats(jedis.getSeatsRedis("cineDorado",funct2));
        } catch (Exception ex) {
           Logger.getLogger(RedisCinemaPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }  
    
    
    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Cinema> getCinemas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinema(String cinema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CreateFunctionInCinema(String Cinema, CinemaFunction funcion) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void UpdateFunctionInCinema(String Cinema, CinemaFunction funcion) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
