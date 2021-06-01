package com.hk210.spacexcrew.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crew_members", indices = @Index(value = {"id"}, unique = true))
public class Crew {


    @PrimaryKey(autoGenerate = true)
    private int crew_id;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private String id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("agency")
    @ColumnInfo(name = "agency")
    private String agency;
    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;
    @SerializedName("wikipedia")
    @ColumnInfo(name = "wikipedia")
    private String wiki;
    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status;

    public Crew(String id,String name, String agency, String status, String wiki, String image) {

        this.name = name;
        this.agency = agency;
        this.status = status;
        this.wiki = wiki;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCrew_id() {
        return crew_id;
    }

    public void setCrew_id(int crew_id) {
        this.crew_id = crew_id;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crew_id=" + crew_id +
                ", name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                ", status='" + status + '\'' +
                ", wiki='" + wiki + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
