package com.example.kiber.Model;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class city {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "mounth")
    private int mounth;
    @Column(name = "day")
    private int day;
    @Column(name = "time")
    private String time;
    @Column(name = "temperature")
    private int temperature;
    @Column(name = "direction")
    private String direction;
    @Column(name = "speed")
    private int speed;
    @Column(name = "name_city")
    private String name;
    @Column(name = "Date")
    private Date date;

    public city() {
    }

    public city(Long id, int mounth, int day, String time, int temperature, String direction, int speed, String name, Date date) {
        this.id = id;
        this.mounth = mounth;
        this.day = day;
        this.time = time;
        this.temperature = temperature;
        this.direction = direction;
        this.speed = speed;
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "city{" +
                "id=" + id +
                ", mounth=" + mounth +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", temperature=" + temperature +
                ", direction='" + direction + '\'' +
                ", speed=" + speed +
                ", name_city='" + name + '\'' +
                '}';
    }
}

