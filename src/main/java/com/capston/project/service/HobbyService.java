package com.capston.project.service;


import com.capston.project.dto.community.BoardsDto;
import com.capston.project.dto.community.BoardsMainDto;
import com.capston.project.dto.hobby.HobbyMainRequest;
import com.capston.project.dto.hobby.HobbyMainResponse;
import com.capston.project.dto.hobby.HobbySingleResponse;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.hobby.Hobby;
import com.capston.project.entity.user.User;
import com.capston.project.exception.BoardNotFoundException;
import com.capston.project.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    @Transactional(readOnly = true) // 취미 검색 리스트 출력
    public List<HobbyMainResponse> hobbySearch(HobbyMainRequest req) {
        List<Hobby> hobby = hobbyRepository.findByHobbyAttribute1ContainingAndHobbyAttribute2ContainingAndHobbyAttribute3Containing(
                    req.getHobbyAttribute1(), req.getHobbyAttribute2(), req.getHobbyAttribute3()
        );
        List<HobbyMainResponse> hobbyMainResponseList = new ArrayList<>();
        hobby.stream().forEach(i -> hobbyMainResponseList.add(new HobbyMainResponse().toDto(i)));
        return hobbyMainResponseList;
        }

    @Transactional(readOnly = true)
    public HobbySingleResponse findHobby(Long hobbyId){ //게시글 단건 조회
        Hobby hobby = hobbyRepository.findById(hobbyId)
                .orElseThrow(BoardNotFoundException::new);
        return HobbySingleResponse.toDto(hobby);
    }

}
