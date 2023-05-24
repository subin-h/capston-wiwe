package com.capston.project.dto.memo;

import com.capston.project.dto.community.BoardsDto;
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
public class MemoResponseDto {

    private Long memoId;
    private String memoTitle;
    private String memoContent1;
    private String memoContent2;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime memoDatetime;

    public static MemoResponseDto toDto(Memo memo) {
        return new MemoResponseDto(
                memo.getMemoId(),
                memo.getMemoTitle(),
                memo.getMemoContent1(),
                memo.getMemoContent2(),
                memo.getMemoDate()
        );
    }
}
