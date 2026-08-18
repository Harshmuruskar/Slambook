package com.example.slambook.dto;

import com.example.slambook.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSlamBookRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    private String nickname;
    private String profilePhotoUrl;
    
    @PastOrPresent(message = "Date of birth must be a past or present date")
    private LocalDate dateOfBirth;
    
    private Gender gender;
    
    private String favoriteColor;
    private List<String> hobbies;
    
    @Size(max = 500, message = "About me cannot exceed 500 characters")
    private String aboutMe;
    
    @Min(value = 1, message = "Friendship rating must be at least 1")
    @Max(value = 10, message = "Friendship rating must be at most 10")
    private Integer friendshipRating;
    
    private Boolean isBestFriend;
    
    @PastOrPresent(message = "Friendship start date must be a past or present date")
    private LocalDate friendshipStartDate;
    
    private String songName;
    private String songArtist;
    
    @URL(message = "Invalid URL format")
    private String songUrl;
    
    private String songDedication;
    
    private String memoryPhotoUrl;
    
    @Size(max = 500, message = "Memory text cannot exceed 500 characters")
    private String memoryText;
}
