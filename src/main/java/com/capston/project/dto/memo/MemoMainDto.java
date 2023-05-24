package com.capston.project.dto.memo;

import com.capston.project.dto.community.BoardsMainDto;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.memo.Memo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoMainDto {

    private Long memoId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime memoDatetime;

    public MemoMainDto toDto(Memo memo) {
        return new MemoMainDto(memo.getMemoId(), memo.getMemoTitle(),memo.getMemoDate());
    }
}
