package com.dev;

import au.com.bytecode.opencsv.CSVWriter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import model.Payload;
import org.apache.log4j.Logger;

import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by valluri.chowdary on 04/11/15.
 */
public class GetData {
    Client client;
    String city;
    static Logger logger = Logger.getLogger(GetData.class);

    public GetData(String city){
        client = Client.create();
        this.city =city;
    }

    public List<Payload> getPayloadList() throws Exception {

        URI uri = buildURI();
        WebResource webResource = client
                .resource(uri);

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            logger.error("Error while processing Get Call");
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        List<Payload> output = response.getEntity(new GenericType<List<Payload>>(){});

        toCSVFile(output);

        logger.info("Get Call finished with CSV generation");

        return null;
    }

    public URI buildURI()
    {
        URI uri =
                UriBuilder.fromUri("http://api.goeuro.com/api/v2/position/suggest/en/" + city).build();
        logger.info("Get Call "+uri);
        return uri;
    }

    public void toCSVFile(List<Payload> payloadList) throws IllegalAccessException {
        File returnFile = new File("location.csv");
        try {

            CSVWriter writer = new CSVWriter(new FileWriter(returnFile));
            writer.writeNext(new String[]{"id", "name", "type", "latitude", "longitude"});

            String[] outPut  =new String[]{"NA","NA","NA","NA","NA"};

            for (Payload payload : payloadList) {

                outPut[0]= payload.getId()!=null?payload.getId():"NA";

                outPut[1] = payload.getName()!=null?payload.getName():"NA";

                outPut[2] = payload.getType()!=null?payload.getType():"NA";

                if(payload.getGeoPosition()!=null){
                    outPut[3] = payload.getGeoPosition().getLatitude()!=null?payload.getGeoPosition().getLatitude():"NA";
                    outPut[4] = payload.getGeoPosition().getLongitude()!=null?payload.getGeoPosition().getLongitude():"NA";
                } else {
                    outPut[3] ="NA";
                    outPut[4]="NA";
                }

                writer.writeNext(outPut);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("Error while writing to CSV");
            return;
        }
    }
}
