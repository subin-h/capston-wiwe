package com.capston.project.dto.hobby;

import com.capston.project.entity.hobby.Hobby;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HobbyMainRequest {

    private String hobbyAttribute1;
    private String hobbyAttribute2;
    private String hobbyAttribute3;

    public static HobbyMainRequest toDto(Hobby hobby) {
        return new HobbyMainRequest(hobby.getHobbyAttribute1(), hobby.getHobbyAttribute2(), hobby.getHobbyAttribute3());
    }
    public static HobbyMainRequest totoDto(String hobbyAttribute1, String hobbyAttribute2, String hobbyAttribute3) {
        return new HobbyMainRequest(
                hobbyAttribute1,
                hobbyAttribute2,
                hobbyAttribute3
        );
    }
}
