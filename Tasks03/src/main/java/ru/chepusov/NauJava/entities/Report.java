package ru.chepusov.NauJava.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name="reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Status status;

    @Column
    private String content;

    @Column
    private Long t1;
    @Column
    private Long t2;
    @Column
    private Long t3;

    @Column
    private OffsetDateTime dateTime;

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getT1() {
        return t1;
    }

    public void setT1(Long t1) {
        this.t1 = t1;
    }

    public Long getT2() {
        return t2;
    }

    public void setT2(Long t2) {
        this.t2 = t2;
    }

    public Long getT3() {
        return t3;
    }

    public void setT3(Long t3) {
        this.t3 = t3;
    }

    @Override
    public String toString() {
        return "Report_" +
                 id +
                " is " + status +
                ". Content:\n" + content
                + "\nFinished with t1 = " + t1
                + " ms, t2 = " + t2
                + " ms t3 = t3";
    }

    public String getTimeInfo() {
        return getEkbTime();
    }

    private String getEkbTime() {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();

        ZoneOffset offset = dateTime.getOffset(); // +05:00
        int hour = dateTime.getHour() + (offset.getTotalSeconds() / 3600);
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();

        return "" + hour + ":" + minute + ":" + second + "  " + day + "-" + month + "-" + year + " \n"
                + offset + " " + offset.getTotalSeconds() + " -> " + (offset.getTotalSeconds() / 3600);
    }
}
