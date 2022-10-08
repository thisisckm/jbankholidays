package com.ck.bankholidays.data;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Holiday {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Holiday newInstance(Date date, String title) {
        Holiday returnValue = new Holiday();
        returnValue.setDate(date);
        returnValue.setTitle(title);
        return returnValue;
    }

    @Override
    public String toString() {
        return String.format("Holiday[id=%d, date=%s]", id, date);
    }

}
