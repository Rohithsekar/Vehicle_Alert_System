package com.vehicle_alert;

import com.vehicle_alert.dto.Graticule;
import com.vehicle_alert.entity.JourneyCoordinates;
import com.vehicle_alert.entity.Location;
import com.vehicle_alert.repository.JourneyCoordinatesRepository;
import com.vehicle_alert.repository.LocationRepository;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {


    private LocationRepository locationRepository;

    private JourneyCoordinatesRepository journeyCoordinatesRepository;

    public DemoApplication(LocationRepository locationRepository,
                           JourneyCoordinatesRepository journeyCoordinatesRepository) {
        this.locationRepository = locationRepository;
        this.journeyCoordinatesRepository = journeyCoordinatesRepository;
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

//        File gpxFile = new File("src/main/resources/PARVATHY_HOSPITAL_TO_PERUNGALATHUR_FLYOVER.gpx");
//
//        try {
//            List<Graticule> gpxPoints = parseGPX(gpxFile);
//            JourneyCoordinates journeyCoordinates = new JourneyCoordinates();
//            journeyCoordinates.setSource("Parvathy Hospital, Chromepet, Chennai, Tamil Nadu 600044");
//            journeyCoordinates.setDestination("Perungalathur Bus stand, New Perungalathur, Chennai, Tamil Nadu 600063");
//            journeyCoordinates.setSourceLat(12.946059728107524);
//            journeyCoordinates.setSourceLng(80.13659502000786);
//            journeyCoordinates.setDestinationLat(12.905647659501856);
//            journeyCoordinates.setDestinationLng(80.0949913232877);
//            journeyCoordinates.setGraticuleList(gpxPoints);
//
//            System.out.println(Arrays.toString(gpxPoints.toArray()));
//
//            journeyCoordinatesRepository.save(journeyCoordinates);
//
//
//
//        } catch (JDOMException | IOException e) {
//            e.printStackTrace();
//        }



    }
//
//    public static List<Graticule> parseGPX(File file) throws JDOMException, IOException {
//        List<Graticule> points = new ArrayList<>();
//        SAXBuilder saxBuilder = new SAXBuilder();
//        Document document = saxBuilder.build(file);
//        Element rootElement = document.getRootElement();
//        Namespace namespace = rootElement.getNamespace();
//        String namespaceURI = namespace.getURI();
//        System.out.println(namespace+" , "+namespaceURI);
//
//        // Handling <rte> element
//        Element rteElement = rootElement.getChild("rte");
//        if (rteElement != null) {
//            List<Element> rtePoints = rteElement.getChildren("rtept");
//            for (Element rtePoint : rtePoints) {
//                double lat = Double.parseDouble(rtePoint.getAttributeValue("lat"));
//                double lon = Double.parseDouble(rtePoint.getAttributeValue("lon"));
//                points.add(new Graticule(lat, lon));
//            }
//        }
//
//        // Handling <trk> element
//        Element trkElement = rootElement.getChild("trk",namespace);
//        if (trkElement != null) {
//            Element trksegElement = trkElement.getChild("trkseg",namespace);
//            if (trksegElement != null) {
//                List<Element> trkPoints = trksegElement.getChildren("trkpt",namespace);
//                for (Element trkPoint : trkPoints) {
//                    double lat = Double.parseDouble(trkPoint.getAttributeValue("lat"));
//                    double lon = Double.parseDouble(trkPoint.getAttributeValue("lon"));
//                    points.add(new Graticule(lat, lon));
//                }
//            }
//        }
//
//        return points;
//    }

}