package com.club.getraenkeapp.data.entities;

import java.util.Objects;

/**
 * Member entity representing club members in the database.
 * 
 * Each member has a unique ID, name, optional grid position for layout configuration,
 * and creation timestamp for audit purposes.
 */
public class Member {
    
    public static final String TABLE_NAME = "members";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GRID_POSITION = "grid_position";
    public static final String COLUMN_CREATED_AT = "created_at";
    
    // Create table SQL
    public static final String CREATE_TABLE = 
        "CREATE TABLE " + TABLE_NAME + " (" +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_NAME + " TEXT NOT NULL, " +
        COLUMN_GRID_POSITION + " INTEGER, " +
        COLUMN_CREATED_AT + " INTEGER DEFAULT " + System.currentTimeMillis() +
        ")";
    
    private long id;
    private String name;
    private Integer gridPosition;
    private long createdAt;
    
    // Default constructor
    public Member() {
        this.createdAt = System.currentTimeMillis();
    }
    
    // Constructor without ID (for new members)
    public Member(String name, Integer gridPosition) {
        this();
        this.name = name;
        this.gridPosition = gridPosition;
    }
    
    // Full constructor
    public Member(long id, String name, Integer gridPosition, long createdAt) {
        this.id = id;
        this.name = name;
        this.gridPosition = gridPosition;
        this.createdAt = createdAt;
    }
    
    // Getters
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Integer getGridPosition() {
        return gridPosition;
    }
    
    public long getCreatedAt() {
        return createdAt;
    }
    
    // Setters
    public void setId(long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGridPosition(Integer gridPosition) {
        this.gridPosition = gridPosition;
    }
    
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member member = (Member) obj;
        return id == member.id && 
               createdAt == member.createdAt &&
               Objects.equals(name, member.name) &&
               Objects.equals(gridPosition, member.gridPosition);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, gridPosition, createdAt);
    }
    
    @Override
    public String toString() {
        return "Member{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", gridPosition=" + gridPosition +
               ", createdAt=" + createdAt +
               '}';
    }
}