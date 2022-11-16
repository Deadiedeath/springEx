package net.ict.springex.dto;

/* 페이지 처리는 현재 페이지 번호(page), 한페이지당 데이터 수(size) 기본적으로 필요*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRequestDTO {
    @Builder.Default // 기본값 page 나 size 의 기본값을 처리할 때
    @Min(value =1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    public int getSkip(){
        return (page-1)*10;
    }
}
