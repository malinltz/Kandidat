/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package KTS3G1;

/**
 *
 * @author malinlilliecreutz
 
public class Test {
    
}
/* 
//extra från HTTPanrop
    /* public String HTTPkontact(String URL) {
       url = URL;
       op = new OptPlan(ds);

        try {
            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();

            //add reuqest header
            anslutning.setRequestMethod("POST");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = message;

            // Send post request
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = anslutning.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
            new InputStreamReader(anslutning.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            utmessage = response.toString();
            System.out.println(utmessage);

            String[] paras = utmessage.split(";" + "");

            for (int i = 0; i < paras.length; i++) {
                paragraph = paras[i];
             
                System.out.println("Parametrar: " + paragraph);

            }
        } catch (Exception e) {

            //print result
            System.out.println(e.toString());
        }
        return utmessage;

    }

    
    public String HTTPuppdrag(String URL) {

       url = URL;
       op = new OptPlan(ds);

        try {

            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();
            System.out.println("\nAnropar: " + url);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }

            inkommande.close();
            gruppmessage = inkommande_samlat.toString();
            System.out.println(gruppmessage);

            String[] paras = gruppmessage.split(";" + "");

            for (int i = 0; i < paras.length; i++) {
                paragraph = paras[i];
                System.out.println("Parametrar: " + paragraph);
            }
//
        } catch (Exception e) {
            System.out.print(e.toString());

        }
        return gruppmessage;
     
// till att dela upp i arrays. 

    //3 students for reading 9 lines
    //Susan,  Jurgen and You ;)

    Student[] students = new Student[3];

    int linesRead = 0;

    String aLine = null;

    // read each line through aLine
    for (Student student : students) {

        //use buffered/scanner classes for reading input(each line) in aLine
        while (aLine != null) {
            ++linesRead;
            if (linesRead == 1) {
                student.setNoOfCases(Integer.valueOf(aLine));
                ++linesRead;
            } else if (linesRead == 2) {
                student.setWorkSchedule(Arrays.asList(aLine.split(" ")));
                ++linesRead;
            } else if (linesRead == 3) {
                student.setInitialTV(Arrays.asList(aLine.split(" ")));
            } else {
                linesRead = 0;
            }
        }
    }
}
}

  while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
                arrayOfStrings[j]= inkommande_text;
                j++; 
                inkommande_text = inkommande.readLine() ;      
            }

 while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }
          //Försöker att sätta slutnoden till upphämtningsplatsen
          //op.getCost();
           
          //int attakatill = Integer.parseInt(plats1.substring(2,4));
          
          //op.createPlan();
          
//        int attakatill2 = Integer.parseInt(plats1.substring(5,7));
//        

//        if(attakatill < attakatill2){
//            slut = attakatill2;
//        }
//        else {
//            slut = attakatill;
//        }
ArrayList<String> ink = new ArrayList<String>();

         
        while ((inkommande_text = inkommande.readLine()) != null) {
            System.out.println("Inkommande: " + inkommande_text);
                inkommande_samlat.append(inkommande_text); 

                 inkommande_samlat.append(inkommande_text); 
                ink.add(inkommande_text);      
        }
         inkommande.close();
         
            for(int k = 0; k < ink.size(); k++){
            System.out.println("Ink: " + ink.get(k));
         }
*/