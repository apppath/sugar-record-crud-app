package com.basic.programming.dbflownewversion.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table(name = "androidOs")
public class Versions extends SugarRecord {

    @Unique
    @Column(name = "android_id")
    private String androidId;
    @Column(name = "code_name")
    private String codeName;
    @Column(name = "version_numbers")
    private String versionNumbers;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "api_level")
    private String apiLevel;

    public Versions() {
    }

    public Versions(String codeName, String versionNumbers, String releaseDate, String apiLevel) {
        this.codeName = codeName;
        this.versionNumbers = versionNumbers;
        this.releaseDate = releaseDate;
        this.apiLevel = apiLevel;
    }

    public String getAndroidId() {
        return androidId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getVersionNumbers() {
        return versionNumbers;
    }

    public void setVersionNumbers(String versionNumbers) {
        this.versionNumbers = versionNumbers;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }
}
