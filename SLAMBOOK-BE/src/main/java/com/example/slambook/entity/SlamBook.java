package com.example.slambook.entity;

import com.example.slambook.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "slam_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlamBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String nickname;
    private String profilePhotoUrl;
    
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private String favoriteColor;
    
    @ElementCollection
    @CollectionTable(name = "slambook_hobbies", joinColumns = @JoinColumn(name = "slambook_id"))
    @Column(name = "hobby")
    private List<String> hobbies;
    
    @Column(columnDefinition = "TEXT")
    private String aboutMe;
    
    private Integer friendshipRating;
    private Boolean isBestFriend;
    private LocalDate friendshipStartDate;
    
    private String songName;
    private String songArtist;
    private String songUrl;
    private String songDedication;
    
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
