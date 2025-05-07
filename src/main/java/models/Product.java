package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uom.UnitOfMeasure;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="product")
public class Product implements Comparable<Product>, Serializable {
    @NotNull(message = "Значение id не может быть пустым")
    @Min(value = 1, message = "ID -> натуральное число большее 0")
    @JsonProperty("id")
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name="id")
    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть //
    // уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank(message = "Имя не должно быть пустым")
    @JsonProperty("name")

    @Column(name = "name", nullable = false)
    private String name; // Поле не может быть null, Строка не может быть пустой

    @NotNull(message = "Коордианты не должны быть пустыми")
    @JsonProperty("coordinates")

    @javax.persistence.OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="coordinates_id")
    private Coordinates coordinates; // Поле не может быть null

    @NotNull(message = "Дата не должна быть пустой")
    @JsonProperty("creationDate")
    @Column(name="creationDate", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно
    // генерироваться// автоматически

    @Min(value = 1, message = "Цена не может быть меньше 0")
    @JsonProperty("price")
    @Column(name = "price")
    private int price; // Значение поля должно быть больше 0

    @NotNull(message = "Единицы измерения не должны быть пустыми")
    @JsonProperty("unitOfMeasure")
    @Column(name="unitOfMeasure")
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure; // Поле может быть null

    @NotNull(message = "Владелец не должен быть неопределён")
    @JsonProperty("owner")
    @javax.persistence.OneToMany(cascade = CascadeType.ALL)
    @javax.persistence.JoinColumn(name="owner_id", nullable = false)
    private Person owner; // Поле не может быть null



    public Product() {
        this.creationDate = LocalDateTime.now().withNano(0);
        this.id = IdGenerator.getProductID();
    }


    @javax.persistence.Transient
    @Override
    public int compareTo(Product other) {
        int priceComparison = Integer.compare(this.price, other.price);
        if (priceComparison != 0) {
            return priceComparison;
        }
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Long.compare(this.id, other.id);
    }

    public static class ProductBuilder {
        private Product newProduct;

        public ProductBuilder() {
            newProduct = new Product();
        }

        public Product buildProduct() {
            return newProduct;
        }

        public ProductBuilder setName(String name) throws NullValueException, EmptyValueException {
            if (null == name) {
                throw new NullValueException();
            }
            if (name.isEmpty()) {
                throw new EmptyValueException();
            }
            newProduct.name = name;
            return this;
        }

        public ProductBuilder setCoordinates(Coordinates coordinates) throws NullValueException {
            if (null == coordinates) {
                throw new NullValueException();
            }
            newProduct.coordinates = coordinates;
            return this;
        }

        public ProductBuilder setPrice(int newPrice) throws NegativeValueException {
            if (newPrice <= 0) {
                throw new NegativeValueException();
            }
            newProduct.price = newPrice;
            return this;
        }

        public ProductBuilder setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
            if (null == unitOfMeasure) {
                newProduct.unitOfMeasure = null;
                return this;
            }
            newProduct.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public ProductBuilder setOwner(Person owner) throws NullValueException {
            if (null == owner) {
                throw new NullValueException();
            }
            newProduct.owner = owner;
            return this;
        }
    }

    @javax.persistence.Transient
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @javax.persistence.Transient
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    @javax.persistence.Transient
    public String getName() {
        return this.name;
    }
    @javax.persistence.Transient
    public int getPrice() {
        return this.price;
    }
    @javax.persistence.Transient
    public Person getOwner() {
        return this.owner;
    }
    @javax.persistence.Transient
    public long getId() {
        return this.id;
    }
    @javax.persistence.Transient
    public UnitOfMeasure getUnitOfMeasure() {
        return this.unitOfMeasure;
    }
    @javax.persistence.Transient
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    @javax.persistence.Transient
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    @javax.persistence.Transient
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    @javax.persistence.Transient
    public void setID(long id) {this.id = id;}
    @javax.persistence.Transient
    @Override
    public String toString() {
        return "Product{" +
                "id=" + this.id +
                ", name=" + this.name +
                ", coordinates=" + this.coordinates +
                ", creationDate=" + this.creationDate +
                ", price=" + this.price +
                ", unitOfMeasure=" + this.unitOfMeasure +
                ", owner=" + this.owner +
                "}";
    }

    @Override
    @javax.persistence.Transient
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, unitOfMeasure, owner);
    }
}