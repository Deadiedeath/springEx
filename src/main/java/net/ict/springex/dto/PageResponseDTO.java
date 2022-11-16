package net.ict.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/* PageResponseDTO 의 기능
-todoDTO 목록
-전체 데이터의 수
-페이지 번호 처리를 위한 데이터 (시작페이지 번호 / 끝 페이지 번호)
*  */
@Getter
@ToString

public class PageResponseDTO<E> { //제너릭을 이용한 이유는 다른 종류의 객체를 이용하여 pageresponseDTO를 구성할수 있도록
                                    // 예로 회원 정보 게시판이나 공지사항 도 페이징 처리가 필요하므로 공통처리를 위해 제너릭<E>로 처리
    private int page;
    private int size;
    private int total;



    //시작 페이지 번호
    private int start;
    // 끝 페이지 번호
    private int end;
    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;
        //마지막 페이지(end)를 구하는 계산식 end는 현재의 페이지 번호를 기준으로 계산한다
        this.end = (int)(Math.ceil(this.page/10.0))*10;
        //마지막 페이지를 먼저 계산하는 이유는 시작페이지 계산을 쉽게 하기 위해서이다. 시작 페이지(start)의 경우 계산한 마지막 페이지에서
        //9를 뺀다
        this.start = this.end-9;
        //시작페이지를 구성한 후 마지막 페이지는 다시 전체 개수(total)을 고려하여 만약 10개씩 (size)를 보여주는 경우
        //전체개수(total)을 구하여 last를 구해야 한다.
        //만약 전체개수가 75라면 마지막 페이지(last)는 75/10 = 7.5 -> 8
        int last =(int)(Math.ceil((total/(double)size)));
        //마지막 페이지(end)는 앞에서 구한 last값 보다 작은 경우는 last가 end가 된다.
        this.end = end>last ? last : end;
        //이전페이지(prev) 존재 여부 시작페이지 1이 아니라면 무조건 true
        this.prev = this.start > 1;
        //다음페이지(next)는 마지막 페이지(end)와 페이지당 수(size)를 곱한 값보다 전체개수(total)이 더 많은지 체크하여 판단한다.
        this.next = total > this.end * this.size;
    }
}
