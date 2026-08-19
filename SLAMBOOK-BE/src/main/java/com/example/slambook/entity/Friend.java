package com.example.slambook.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "friend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slam_book_id", nullable = false)
    private SlamBook slamBook;

    private String friendName;
    private String relationship;
    private Integer friendshipRating;
    private Boolean isBestFriend;
    private LocalDate friendshipSince;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String songName;
    private String songArtist;

    @Column(columnDefinition = "TEXT")
    private String songUrl;

    @Column(columnDefinition = "TEXT")
    private String songDedication;

    @Column(columnDefinition = "TEXT")
    private String memoryPhotoUrl;

    @Column(columnDefinition = "TEXT")
    private String memoryText;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
