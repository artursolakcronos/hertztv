package com.hertz.hertztv.model;

import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_SEQ")
    @SequenceGenerator(name = "EVENT_SEQ", sequenceName = "EVENT_SEQ", allocationSize = 1)
    @Column(nullable = false)
    private long id;
    private String name;
    private String location;
    private String description;
    private long capacity;
    private Date eventDate;
    private String flyerPath;

    @ManyToMany(mappedBy = "events", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @Nullable
    private Set<Artist> artists = new LinkedHashSet<>();

    public Event() {
    }

    public Event(String name, String location, String description, long capacity, Date eventDate, String flyerPath, @Nullable Set<Artist> artists) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.eventDate = eventDate;
        this.flyerPath = flyerPath;
        this.artists = artists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }


    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }


    public String getFlyerPath() {
        return flyerPath;
    }

    public void setFlyerPath(String flyerPath) {
        this.flyerPath = flyerPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
