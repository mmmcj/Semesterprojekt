package DTO;

import entity.City;
import entity.Country;
import entity.Event;
import entity.Genre;
import entity.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mark
 */
public class EventDTO {

    private Integer id;
    private Country country;
    private City city;
    private Genre genre;
    private String title;
    private double price;
    private String shortDesc;
    private String longDesc;

    private List<String> images = new ArrayList<>();

    private String defaultImg;

    private Date startDate;
    private Date endDate;

    public EventDTO() {
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.genre = event.getGenre();
        this.title = event.getTitle();
        this.price = event.getPrice();

        for (Image image : event.getImages()) {
            images.add(image.getImg());
        }

        this.shortDesc = event.getShortDesc();
        this.longDesc = event.getLongDesc();
        this.defaultImg = event.getDefaultImg();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
    }

}
