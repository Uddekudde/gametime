/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.tudd.project.api.reader.integration;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import se.kth.id1212.tudd.project.api.reader.model.Match;

/**
 *
 * @author udde
 */
@Stateless
public class APICaller {

    private final String USER_AGENT = "Mozilla/5.0";

    public Match[] getMatchesFromID(String id, String timeScale) throws Exception {
        
        String parameter = "?date=" + timeScale;
        if(timeScale.equals("")){
           parameter = "";
        }
            
        String url = "https://api.opendota.com/api/players/" + id + "/matches"+parameter;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String jsonData = in.readLine();
        Match[] matches = new Gson().fromJson(jsonData, Match[].class);
        return matches;
    }
}
