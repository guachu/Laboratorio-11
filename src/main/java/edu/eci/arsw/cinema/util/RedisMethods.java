package edu.eci.arsw.cinema.util;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;


@Service
public class RedisMethods {

    public void saveToREDIS(String key, String data) {
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.watch(key);
        Transaction t1 = jedis.multi();
        t1.set(key, data);
        t1.exec();
        jedis.close();
    }



    public String getFromREDIS(String key) {
        boolean intentar = true;
        String content = "";
        while (intentar) {
            // Inicializar jedis y obtener recursos
            Jedis jedis = JedisUtil.getPool().getResource();
            // Hacer watch de la llave
            jedis.watch(key);
            // Crear la transacción t
            Transaction t = jedis.multi();
            Response<String> data = t.get(key);
            List<Object> result = t.exec();
            if (result.size() > 0) {
                intentar = false;
                content = data.get();
                // Cerrar recurso jedis
                jedis.close();
            }
        }
        return content;
    }

    public List<List<Boolean>> buyTicketRedis(int row, int col, String cinema, String date, String movieName) throws JsonParseException, JsonMappingException, IOException, CinemaException {
        String key = cinema+"+"+date+"+"+movieName+"+";
        String value = getFromREDIS(key);
        ObjectMapper mapper = new ObjectMapper();
        List<List<Boolean>> seats = mapper.readValue(value, ArrayList.class);
        if (seats.get(row).get(col).equals(true)){
            seats.get(row).set(col,Boolean.FALSE);
            String data = mapper.writeValueAsString(seats);
            saveToREDIS(key, data);
            value = getFromREDIS(key);
            seats = mapper.readValue(value, ArrayList.class);
            return seats;
        }
        else{
            throw new CinemaException("Este asiento ya esta reservado. Porfavor vuelva a intentar el proceso.");
        }
        
        
    }
    
    public List<List<Boolean>> getSeatsRedis(String cinema, CinemaFunction f) throws JsonParseException, JsonMappingException, IOException {
        String key = cinema+"+"+f.getDate()+"+"+f.getMovie().getName()+"+";
        String value = getFromREDIS(key);
        ObjectMapper mapper = new ObjectMapper();
        List<List<Boolean>> seats = mapper.readValue(value, ArrayList.class);
        return seats;
    }

}
