package PWS;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import dbop.MySQLConnector;
import dbop.MySQLdbopDailyObs;

public class Main {

    public static void main(String[] args) {
        try {
            String ApiKey = "0531e8989b884563b1e8989b88756376";
            String StationID = "ISANVI15";
            GetObservation RCO = new GetObservation(ApiKey, StationID);
            RCO.setApiKey(ApiKey);
            RCO.setStationID(StationID);
            JSONArray currentObservationArray = RCO.getCurrentObservation();
            JSONArray dailyObservationArray = RCO.getDailyObservation(DateUtil.getYesterdayDateString("yyyyMMdd"));
            System.out.println(dailyObservationArray.length());

            //Connessione MYSQL
	          /*
	            MySQLConnector msc = new MySQLConnector();
	            Connection dcConn = MySQLConnector.getMYSQLDBConnection();
	            System.out.println(dcConn.getClass());
	           */


            //DAILY OBSERVATION
            try {
                MySQLConnector mySQLConn = new MySQLConnector();
                Connection connection = mySQLConn.getMYSQLDBConnection();
                for (int i = 0; i < dailyObservationArray.length(); i++) {
                    //DailyObservation observation = RCO.createDailyObservation(dailyObservationArray);
                    JSONObject obs = dailyObservationArray.getJSONObject(i);
                    DailyObservation observation = RCO.createDailyObservation(obs);
                    System.out.println(observation.getStationID());
                    System.out.println(observation.getObsTimeLocal());
                    MySQLdbopDailyObs mysqldaily = new MySQLdbopDailyObs(connection, observation);
                    mysqldaily.addDailyObs();
                }
                connection.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            //CURRENT OBSERVATION
            try {
                Observation observation = RCO.createCurrentObservation(currentObservationArray);
                System.out.println(observation.getNeighborhood());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}