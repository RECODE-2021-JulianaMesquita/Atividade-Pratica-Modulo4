package com.herokuapp.JuhMesquitaViagens.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private int idAddressDestiny;
    @ManyToOne
    @JoinColumn(nullable = false)
    private int idAddressOrigin;
    @Column(nullable = false)
    private int idLegal;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int people;
    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    private boolean promotion;
    @Column(nullable = false)
    private Date dateGoing;
    @Column(nullable = false)
    private Date dateReturn;

    public Package() {
    }

    public Package(int id, int idAddressDestiny, int idAddressOrigin, int idLegal, String title, int people,
            double value, boolean promotion, Date dateGoing, Date dateReturn) {
        this.id = id;
        this.idAddressDestiny = idAddressDestiny;
        this.idAddressOrigin = idAddressOrigin;
        this.idLegal = idLegal;
        this.title = title;
        this.people = people;
        this.value = value;
        this.promotion = promotion;
        this.dateGoing = dateGoing;
        this.dateReturn = dateReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAddressDestiny() {
        return idAddressDestiny;
    }

    public void setIdAddressDestiny(int idAddressDestiny) {
        this.idAddressDestiny = idAddressDestiny;
    }

    public int getIdAddressOrigin() {
        return idAddressOrigin;
    }

    public void setIdAddressOrigin(int idAddressOrigin) {
        this.idAddressOrigin = idAddressOrigin;
    }

    public int getIdLegal() {
        return idLegal;
    }

    public void setIdLegal(int idLegal) {
        this.idLegal = idLegal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public Date getDateGoing() {
        return dateGoing;
    }

    public void setDateGoing(Date dateGoing) {
        this.dateGoing = dateGoing;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateGoing == null) ? 0 : dateGoing.hashCode());
        result = prime * result + ((dateReturn == null) ? 0 : dateReturn.hashCode());
        result = prime * result + id;
        result = prime * result + idAddressDestiny;
        result = prime * result + idAddressOrigin;
        result = prime * result + idLegal;
        result = prime * result + people;
        result = prime * result + (promotion ? 1231 : 1237);
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Package other = (Package) obj;
        if (dateGoing == null) {
            if (other.dateGoing != null)
                return false;
        } else if (!dateGoing.equals(other.dateGoing))
            return false;
        if (dateReturn == null) {
            if (other.dateReturn != null)
                return false;
        } else if (!dateReturn.equals(other.dateReturn))
            return false;
        if (id != other.id)
            return false;
        if (idAddressDestiny != other.idAddressDestiny)
            return false;
        if (idAddressOrigin != other.idAddressOrigin)
            return false;
        if (idLegal != other.idLegal)
            return false;
        if (people != other.people)
            return false;
        if (promotion != other.promotion)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Package [dateGoing=" + dateGoing + ", dateReturn=" + dateReturn + ", id=" + id + ", idAddressDestiny="
                + idAddressDestiny + ", idAddressOrigin=" + idAddressOrigin + ", idLegal=" + idLegal + ", people="
                + people + ", promotion=" + promotion + ", title=" + title + ", value=" + value + "]";
    }

}
