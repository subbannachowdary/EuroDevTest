package com.dev;

import org.apache.log4j.Logger;

/**
 * Created by valluri.chowdary on 04/11/15.
 */
public class Locator {

    static Logger logger = Logger.getLogger(Locator.class);

    public static void main(String args[]){
        if(args.length==0){
            logger.error("City Name missing");
            logger.info("java -jar GoEuroTest.jar \"CITY_NAME\"");
            return;
        }
        GetData getData = new GetData(args[0]);
        try {
            getData.getPayloadList();
        } catch (Exception e) {
            logger.error("Error while processing {} ");
            e.printStackTrace();
        }
    }
}
