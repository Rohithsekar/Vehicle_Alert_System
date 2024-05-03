package com.vehicle_alert;

import com.vehicle_alert.dto.Graticule;
import com.vehicle_alert.entity.Location;
import com.vehicle_alert.repository.LocationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {


    private LocationRepository locationRepository;

    public DemoApplication(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @PostConstruct
    public void init() {

        //Initialize static source and destination locations

        long count = locationRepository.count();

        if(count == 0){
            List<Location> locations = new ArrayList<>();

            Location datayaanOffice = new Location("Datayaan Office, Agaram building, MEPZ," +
                    " Tambaram , Tamil Nadu-600045", new Graticule(12.944276424934937, 80.12319323401412));

            Location parvathyHospital = new Location("Parvathy Hospital, Chromepet, Chennai, Tamil Nadu 600044",
                    new Graticule(12.946059728107524, 80.13659502000786));

            Location perungalathurBusStand = new Location("Perungalathur Bus stand, New Perungalathur, Chennai, Tamil Nadu 600063",
                    new Graticule(12.905647659501856, 80.0949913232877));

            locations.add(datayaanOffice);
            locations.add(parvathyHospital);
            locations.add(perungalathurBusStand);

            List<Location> saved = locationRepository.saveAll(locations);

            System.out.println(saved);
        }else {
            System.out.println("Locations already exist in database");
        }



    }

}