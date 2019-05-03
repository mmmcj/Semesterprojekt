/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.minidev.json.annotate.JsonIgnore;

/**
 *
 * @author Christian
 */
@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Country country;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private City city;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Genre genre;

    private String title;
    private double price;
    private String shortDesc;
    private String longDesc;

    @ManyToMany(mappedBy = "events", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Image> images = new ArrayList<>();

    private String defaultImg;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date endDate;

    public Event() {
    }

    public Event(Country country, City city, Genre genre, String title, double price, String shortDesc, String longDesc, Image image, String defaultImg, Date startDate, Date endDate) {
        this.country = country;
        this.city = city;
        this.genre = genre;
        this.title = title;
        this.price = price;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        images.add(image);
        image.addEvent(this);
        this.defaultImg = defaultImg;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Event[ id=" + id + " ]";
    }

}
