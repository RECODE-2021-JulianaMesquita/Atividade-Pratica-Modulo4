package com.herokuapp.JuhMesquitaViagens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table
class Physics extends Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String cpf;
    @OneToMany(mappedBy = "package")
    ArrayList<Package> packages = new ArrayList<>();

    public Physics(int id, String cpf, ArrayList<Package> packages) {
        this.id = id;
        this.cpf = cpf;
        this.packages = packages;
    }

    public Physics() {
    }

    public Physics(int id, int idAddress, String name, String email, String password, String phone,
            boolean administrator, int id2, String cpf, ArrayList<Package> packages) {
        super(id, idAddress, name, email, password, phone, administrator);
        id = id2;
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

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + id;
        result = prime * result + ((packages == null) ? 0 : packages.hashCode());
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
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (id != other.id)
            return false;
        if (packages == null) {
            if (other.packages != null)
                return false;
        } else if (!packages.equals(other.packages))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Physics [cpf=" + cpf + ", id=" + id + ", packages=" + packages + "]";
    }

}
