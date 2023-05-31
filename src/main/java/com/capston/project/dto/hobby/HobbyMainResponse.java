package com.capston.project.dto.hobby;


import com.capston.project.entity.hobby.Hobby;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HobbyMainResponse {

    private Long hobbyId;
    private String hobbyTitle;

    private String hobbyAttribute1;
    private String hobbyAttribute2;
    private String hobbyAttribute3;

    public static HobbyMainResponse toDto(Hobby hobby) {
        return new HobbyMainResponse(
                hobby.getHobbyId(),
                hobby.getHobbyTitle(),
                hobby.getHobbyAttribute1(),
                hobby.getHobbyAttribute2(),
                hobby.getHobbyAttribute3());
    }
}
