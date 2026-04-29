package org.acme.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class CurrencyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "from_currency")
    public String from;
    @Column(name = "to_currency")
    public String to;
    public float rate;
    public String date;
    public String source;
    public double value;
    public double convertedValue;

    @ManyToOne
    @JoinColumn(name = "radnik_id")
    public Radnik radnik;
    // Getter Methods


    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public float getRate() {
        return rate;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    // Setter Methods

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CurrencyResponse() {
    }

    public CurrencyResponse(String from, String to, float rate, String date, String source, double value, double convertedValue) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.date = date;
        this.source = source;
        this.value = value;
        this.convertedValue = convertedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return Float.compare(rate, that.rate) == 0 && Double.compare(value, that.value) == 0 && Double.compare(convertedValue, that.convertedValue) == 0 && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(date, that.date) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, rate, date, source, value, convertedValue);
    }

    @Override
    public String toString() {
        return "CurrencyResponse{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                ", source='" + source + '\'' +
                ", value=" + value +
                ", convertedValue=" + convertedValue +
                '}';
    }
}