package com.mondido.mclearning.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String genre;
    private int yearReleased;
    private double rating;

    public Movie(){
        super();
    }
    public Movie(String name, String genre, int yearReleased, double rating){
        this.name = name;
        this.genre = genre;
        this.yearReleased = yearReleased;
        this.rating = rating;
    }

    public Movie(Long id, String name, String genre, int yearReleased, double rating){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.yearReleased = yearReleased;
        this.rating = rating;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the yearReleased
     */
    public int getYearReleased() {
        return yearReleased;
    }

    /**
     * @param yearReleased the yearReleased to set
     */
    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(rating);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + yearReleased;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
            return false;
        if (yearReleased != other.yearReleased)
            return false;
        return true;
    }
}