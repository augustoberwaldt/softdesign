package com.softdesign.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private  Long id;

    @NotBlank(message = "Title e obrigatorio")
    private  String title;

    private  String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private  Date dtCreated;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private  Date dtFinish;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Date getDtFinish() {
        return dtFinish;
    }

    public void setDtFinish(Date dtFinish) {
        this.dtFinish = dtFinish;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dtCreated=" + dtCreated +
                ", dtFinish=" + dtFinish +
                '}';
    }
}
