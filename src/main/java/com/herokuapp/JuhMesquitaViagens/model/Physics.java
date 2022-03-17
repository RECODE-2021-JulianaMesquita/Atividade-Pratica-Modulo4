package com.herokuapp.JuhMesquitaViagens.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "physics")
public class Physics extends Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String cpf;    
    @OneToMany
    @JoinColumn(name = "package")
	private List<Package> packages;

	public Physics() {
		super();
	}

	public Physics(int id, String cpf, List<Package> packages) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.packages = packages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cpf, id, packages);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Physics other = (Physics) obj;
		return Objects.equals(cpf, other.cpf) && id == other.id && Objects.equals(packages, other.packages);
	}

	@Override
	public String toString() {
		return "Physics [id=" + id + ", cpf=" + cpf + ", packages=" + packages + "]";
	}
    

}
