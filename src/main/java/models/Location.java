package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "loc")
public class Location implements Comparable<Location>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть //
    // уникальным, Значение этого поля должно генерироваться автоматически
    @JsonProperty("x")
    @Column(name="x")
    private long x;

    @NotNull(message = "Высота не может отсутствовать")
    @JsonProperty("y")
    @Column(name="y", nullable = false)
    private Double y; // Поле не может быть null

    @JsonProperty("z")
    @Column(name="z")
    private double z;
    public Location() {
    }

    @javax.persistence.Transient
    public void setY(Double newHeight) throws NullValueException {
        if (null == newHeight) {
            throw new NullValueException();
        }
        this.y = newHeight;
    }
    @javax.persistence.Transient

    public void setX(long newWidth) {
        this.x = newWidth;
    }
    @javax.persistence.Transient
    public void setZ(double newDepth) {
        this.z = newDepth;
    }
    @javax.persistence.Transient
    public long getX() {
        return this.x;
    }
    @javax.persistence.Transient
    public double getZ() {
        return this.z;
    }
    @javax.persistence.Transient
    public Double getY() {
        return this.y;
    }

    @Override
    @javax.persistence.Transient
    public String toString() {
        return "Location={" +
                "width=" + this.x +
                ", height=" + this.y +
                ", depth=" + this.z +
                "}";
    }

    @Override
    @javax.persistence.Transient
    public int compareTo(Location other) {
        int xComparison = Long.compare(this.x, other.x);
        if (xComparison != 0) {
            return xComparison;
        }
        int yComparison = this.y.compareTo(other.y);
        if (yComparison != 0) {
            return yComparison;
        }
        return Double.compare(this.z, other.z);
    }

    @Override
    @javax.persistence.Transient
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location myClass = (Location) o;
        return x == myClass.x &&
                Double.compare(myClass.z, z) == 0 &&
                y.equals(myClass.y);
    }

    @Override
    @javax.persistence.Transient
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}