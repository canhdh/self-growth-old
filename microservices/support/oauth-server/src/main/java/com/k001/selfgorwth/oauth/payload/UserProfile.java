package com.k001.selfgorwth.oauth.payload;

import java.time.Instant;

public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant jointAt;
    private long diaryCount;

    public UserProfile(Long id, String username, String name, Instant jointAt, long diaryCount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.jointAt = jointAt;
        this.diaryCount = diaryCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getJointAt() {
        return jointAt;
    }

    public void setJointAt(Instant jointAt) {
        this.jointAt = jointAt;
    }

    public long getDiaryCount() {
        return diaryCount;
    }

    public void setDiaryCount(long diaryCount) {
        this.diaryCount = diaryCount;
    }
}
