package com.capston.project.dto.memo;

import com.capston.project.dto.community.BoardsCreateDto;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.memo.Memo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoCreateDto {

    private Long memoId;

    public static MemoCreateDto toDto(Memo memo) {
        return new MemoCreateDto(memo.getMemoId());
    }

}
