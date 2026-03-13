package com.Yassine.dev.api.termine.dto;

import com.Yassine.dev.api.termine.enums.Occasion;
import com.Yassine.dev.api.termine.enums.Status;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public class TermineUpdateRequest {

    private UUID id;

    @Size(max = 100)
    private String region;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Size(max = 255)
    private String exactLocation;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String hallOrLocation;

    private Occasion occasion;

    @Size(max = 255)
    private String packageName;

    @Size(max = 100)
    private String bookingType;

    @Size(max = 100)
    private String duration;

    @Size(max = 1000)
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    private Status status;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getExactLocation() {
        return exactLocation;
    }

    public void setExactLocation(String exactLocation) {
        this.exactLocation = exactLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHallOrLocation() {
        return hallOrLocation;
    }

    public void setHallOrLocation(String hallOrLocation) {
        this.hallOrLocation = hallOrLocation;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}