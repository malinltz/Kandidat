/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KTS3G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.List;
import java.io.DataOutputStream;
//import java.util.regex.Pattern;

/**
 *
 * @author malinlilliecreutz
 */

public class HTTPextern {

    public OptPlan op;
    public DataStore ds;
    public HTTPanrop http;
    int sleepTime;
    int[] capacity;
    int ID = 1;
    int[] messagetyp;
    String z= "z";
    String m= "m";
    String n= "n";
    String p= "p";
    

    public HTTPextern(HTTPanrop http, DataStore ds) {

        this.http = http;
        this.ds=ds;
        sleepTime = 1000;

    }

    public void exprotokoll() {

        //om företagsgruppens id är lägre än en annan så  får den uppdraget
        // 
       /* 
        if (ID < http.ID) 
        {
            System.out.println("");
        }
        System.out.println();
        System.out.println();

        if ()
*/
    }

}
