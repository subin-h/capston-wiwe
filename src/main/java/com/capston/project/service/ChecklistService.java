package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.checklist.ChecklistResponseDto;
import com.capston.project.dto.checklist.ChecklistSum1RequestDto;
import com.capston.project.dto.checklist.ChecklistSum2RequestDto;
import com.capston.project.dto.checklist.ChecklistSum3RequestDto;
import com.capston.project.dto.user.UserDto;
import com.capston.project.entity.checklist.ResultInfo;
import com.capston.project.entity.user.User;
import com.capston.project.exception.ChecklistNotFoundException;
import com.capston.project.repository.ResultInfoRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChecklistService {

    private final UserRepository userRepository;
    private final ResultInfoRepository resultInfoRepository;

    @Transactional
    public UserDto updateChecklist1(ChecklistSum1RequestDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum1(req.getChecklistSum1());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public UserDto updateChecklist2(ChecklistSum2RequestDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum2(req.getChecklistSum2());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public UserDto updateChecklist3(ChecklistSum3RequestDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum3(req.getChecklistSum3());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public ChecklistResponseDto getResult(){
        ResultInfo resultInfo = resultInfoRepository.findById(resultChecklist()).orElseThrow(ChecklistNotFoundException::new);
        return ChecklistResponseDto.toDto(resultInfo);
    }

    @Transactional
    public Long resultChecklist(){
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
