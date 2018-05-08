package KTS3G1;


public class AGV_instruktioner implements Runnable{
  
DataStore ds;
ControlUI cui;
      
public AGV_instruktioner(DataStore ds){
    this.ds = ds;
    this.cui = cui;
    
}      

@Override
public void run(){

    try {
    Thread.sleep(2000);     //Detta är för att ds.kommandon ska hinna köras och få värden innan denna tråd startar
   
        
        try { 
            
            
            
            for(int i = 0; i <RobotRutt.rutt.length(); i++){
            //dcount räknar antalet "D" (Done) som fås från AGVn
            
               while(ds.starta == i){
                Thread.sleep(1000);

                ds.passeradenoder = i; //Räknar antalet noder som passeras 
                String kommando = RobotRutt.rutt;  
                cui.appendStatus("Skickat"+kommando);
                //tc.send(kommando);
                
                
                
                

                }
               if (RobotRutt.rutt.isEmpty())
                {
                   ds.passeradenoder = ds.passeradenoder +1;
                   RobotRutt.rutt="";             //Tömmer kommandon när alla är körda
                   ds.Antal_passagerare = 0;                   //Nollställer passagerarantal
                 //  ds.passant  = new int[IntStorlek]; 

               }
               
               
                }
                     ds.starta = 0; 

                }   catch (InterruptedException exeption) {
              }
             
 
    
                try {Thread.sleep(10000);
            }   catch (InterruptedException exeption) {
               }
        
   
    
} catch (InterruptedException exeption) {
               }   
}
}
