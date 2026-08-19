package com.example.slambook.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateFriendRequest {
    
    private String friendName;
    
    private String relationship;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be at most 10")
    private Integer friendshipRating;
    
    private Boolean isBestFriend;
    private LocalDate friendshipSince;
    
    @Size(max = 500, message = "Message must not exceed 500 characters")
    private String message;
    
    private String songName;
    private String songArtist;
    
    @URL(message = "Invalid URL format")
    private String songUrl;
    
    private String songDedication;
    private String memoryPhotoUrl;
    
    @Size(max = 500, message = "Memory text must not exceed 500 characters")
    private String memoryText;
}
