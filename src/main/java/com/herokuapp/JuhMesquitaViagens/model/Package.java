package com.herokuapp.JuhMesquitaViagens.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "addressDestiny")
    private Address addressDestiny;
    @OneToOne
    @JoinColumn(name = "addressOrigin")
    private Address addressOrigin;
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
    @OneToOne
    @JoinColumn(name = "legal")
    private Legal legal;
    

    public Package() {
    }

	public Package(int id, Address addressDestiny, Address addressOrigin, String title, int people, double value,
			boolean promotion, Date dateGoing, Date dateReturn) {
		super();
		this.id = id;
		this.addressDestiny = addressDestiny;
		this.addressOrigin = addressOrigin;
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

	public Address getAddressDestiny() {
		return addressDestiny;
	}

	public void setAddressDestiny(Address addressDestiny) {
		this.addressDestiny = addressDestiny;
	}

	public Address getAddressOrigin() {
		return addressOrigin;
	}

	public void setAddressOrigin(Address addressOrigin) {
		this.addressOrigin = addressOrigin;
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
		return Objects.hash(addressDestiny, addressOrigin, dateGoing, dateReturn, id, people, promotion, title, value);
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
		return Objects.equals(addressDestiny, other.addressDestiny)
				&& Objects.equals(addressOrigin, other.addressOrigin) && Objects.equals(dateGoing, other.dateGoing)
				&& Objects.equals(dateReturn, other.dateReturn) && id == other.id && people == other.people
				&& promotion == other.promotion && Objects.equals(title, other.title)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Package [id=" + id + ", addressDestiny=" + addressDestiny + ", addressOrigin=" + addressOrigin
				+ ", title=" + title + ", people=" + people + ", value=" + value + ", promotion=" + promotion
				+ ", dateGoing=" + dateGoing + ", dateReturn=" + dateReturn + "]";
	}	
    
}
