package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coordinates")
public class Coordinates implements Comparable<Coordinates>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть //
    // уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull(message = "Не может быть переменная Х быть null")
    @Min(value = -851, message = "Не может быть меньше -851 значение Х")
    @JsonProperty("x")
    @Column(name = "x", nullable = false)
    private Long x; // Значение поля должно быть больше -852, Поле не может быть null

    @NotNull(message = "Не может быть переменная Y быть null")
    @JsonProperty("y")
    @Column(name = "y", nullable = false)
    private Integer y; // Поле не может быть null

    @javax.persistence.Transient
    public void setX(Long newValueX) throws NullValueException, CoordinateWrongValueException {
        if (null == newValueX) {
            throw new NullValueException();
        }
        if (newValueX <= -852) {
            throw new CoordinateWrongValueException();
        }
        this.x = newValueX;
    }
    @javax.persistence.Transient
    public void setY(Integer newValueY) throws NullValueException {
        if (null == newValueY) {
            throw new NullValueException();
        }
        this.y = newValueY;
    }

    @Override
    @javax.persistence.Transient
    public String toString() {
        return "Coordinates{" +
                "x=" + this.x +
                ", y=" + this.y +
                "}";
    }

    @Override
    @javax.persistence.Transient
    public int compareTo(Coordinates other) {
        int xComparison = this.x.compareTo(other.x);
        if (xComparison != 0) {
            return xComparison;
        }
        return this.y.compareTo(other.y);
    }
}