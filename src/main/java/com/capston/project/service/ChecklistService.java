package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.checklist.*;
import com.capston.project.dto.user.UserDto;
import com.capston.project.entity.checklist.ResultInfo;
import com.capston.project.entity.user.User;
import com.capston.project.exception.ChecklistNotFoundException;
import com.capston.project.repository.ResultInfoRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.tools.cache.CacheKeyResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChecklistService {

    private final UserRepository userRepository;
    private final ResultInfoRepository resultInfoRepository;

    @Transactional
    public UserDto updateChecklist1(ChecklistSum1RequestDto req) { //checklist1 갱신
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum1(req.getChecklistSum1());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public ChecklistSingleResponseDto resultChecklist1(){ //checklist1 단일 결과 출력
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Integer checklistNumber1 = user.getChecklistSum1();
        String resultString;
        if(checklistNumber1 != null) {
            if (checklistNumber1 >= 16) {
                resultString = "위험";
            } else if (checklistNumber1 >= 8) {
                resultString = "중증";
            } else if (checklistNumber1 >= 5)
                resultString = "경미";
            else
                resultString = "안전";
        }
        else throw new ChecklistNotFoundException();
        return ChecklistSingleResponseDto.toDto(resultString, checklistNumber1);
    }

    @Transactional
    public UserDto updateChecklist2(ChecklistSum2RequestDto req) { //checklist2 갱신
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum2(req.getChecklistSum2());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public ChecklistSingleResponseDto resultChecklist2(){ //checklist2 단일 결과 출력
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Integer checklistNumber2 = user.getChecklistSum2();
        String resultString;
        if(checklistNumber2 != null) {
            if (checklistNumber2 >= 7) {
                resultString = "위험";
            } else if (checklistNumber2 >= 4) {
                resultString = "경미";
            }else
                resultString = "안전";
        }
        else throw new ChecklistNotFoundException();
        return ChecklistSingleResponseDto.toDto(resultString, checklistNumber2);
    }

    @Transactional
    public UserDto updateChecklist3(ChecklistSum3RequestDto req) { //checklist3 갱신
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum3(req.getChecklistSum3());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public ChecklistSingleResponseDto resultChecklist3() { //checklist3 단일 결과 출력
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Integer checklistNumber3 = user.getChecklistSum3();
        String resultString;
        if (checklistNumber3 != null) {
            if (checklistNumber3 >= 10)
                resultString = "위험";
            else if (checklistNumber3 >= 5)
                resultString = "경미";
            else
                resultString = "안전";
        }
        else throw new ChecklistNotFoundException();
        return ChecklistSingleResponseDto.toDto(resultString, checklistNumber3);
    }

    @Transactional
    public ChecklistResponseDto getResult(){ //전체 체크리스트 결과 출력
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        ResultInfo resultInfo = resultInfoRepository.findById(resultChecklist()).orElseThrow(ChecklistNotFoundException::new);
        return ChecklistResponseDto.toDto(user, resultInfo);
    }

    @Transactional
    public Long resultChecklist(){ // 결과 id 매칭
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Long checkNumber;
        Integer resultCheck1 = user.getChecklistSum1();
        Integer resultCheck2 = user.getChecklistSum2();
        Integer resultCheck3 = user.getChecklistSum3();
        if(resultCheck1 == null || resultCheck2 == null || resultCheck3 == null) {
            throw new ChecklistNotFoundException();
        }
        if (resultCheck1>=16){
            checkNumber = 1L;
        } else if (resultCheck1>=8 && resultCheck2>=7 && resultCheck3>=10) {
            checkNumber = 2L;
        } else if (resultCheck1>=8 && resultCheck2>=7 && resultCheck3>=0) {
            checkNumber = 3L;
        } else if (resultCheck1>=8 && resultCheck2>=4 && resultCheck3>=10) {
            checkNumber = 4L;
        } else if (resultCheck1>=8 && resultCheck2>=4 && resultCheck3>=0) {
            checkNumber = 5L;
        } else if (resultCheck1>=8 && resultCheck2>=0 && resultCheck3>=10) {
            checkNumber = 6L;
        }  else if (resultCheck1>=8 && resultCheck2>=0 && resultCheck3>=0) {
            checkNumber = 7L;
        } else if (resultCheck1>=4 && resultCheck2>=7 && resultCheck3>=10) {
            checkNumber = 8L;
        } else if (resultCheck1>=4 && resultCheck2>=7 && resultCheck3>=0) {
            checkNumber = 9L;
        } else if (resultCheck1>=4 && resultCheck2>=4 && resultCheck3>=10) {
            checkNumber = 10L;
        } else if (resultCheck1>=4 && resultCheck2>=4 && resultCheck3>=0) {
            checkNumber =11L;
        } else if (resultCheck1>=4 && resultCheck2>=0 && resultCheck3>=10) {
            checkNumber = 12L;
        } else if (resultCheck1>=4 && resultCheck2>=0 && resultCheck3>=0) {
            checkNumber = 13L;
        } else if (resultCheck1>=0 && resultCheck2>=7) {
            checkNumber = 14L;
        } else if (resultCheck1>=0 && resultCheck2>=4) {
            checkNumber = 15L;
        } else if (resultCheck1>=0 && resultCheck2>=0) {
            checkNumber = 16L;
        } else
            checkNumber = 17L;

        return checkNumber;
    }
}
