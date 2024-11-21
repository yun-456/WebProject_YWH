package utils;

public class BoardPage {
	/*
	totalCount : 전체 게시물의 갯수 
	pageSize : web.xml에 설정한 값. 한페이지에 출력할 게시물 수
	blockPage : 위와동일. 한 블럭당 출력할 페이지번호 수.
	pageNum : 현제 진입해있는 페이지번호.
	reqUrl : 게시판 목록의 요청명.
	 */
	public static String pagingStr(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl) {
		String pagingStr = "";
		
		/*단계 3 : 전체 페이지 수 계산
		: ceil(전체게시물 수 / 한페이지당 수)=> 결과를 무조건 올림처리
		하여 계산한다.
		*/
		int totalPages = (int) (Math.ceil(((double) totalCount /  pageSize)));
		
		//단계 4 : 이전 페이지 블록 바로가기 출력
		/*
		페이지블럭에서 첫번째 페이지번호를 구하기 위한 계산식.
		우리는 blockPage를 5로 설정했으므로 아래 계산식에 1 ~ 5를 대입해보면
		1의 결과가 나온다.
		즉 1 ~ 5페이지 일때는 pageTemp가 1이 되어 반복하면서 출력할 수 있다.
		*/
		
		int pageTemp = (((pageNum - 1) / blockPage)*blockPage)+1;
		//첫번째 블럭이 아닌 경우에만 이전페이지로 바로가기 링크를 출력한다.
		if (pageTemp !=1) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp -1)
						+"'>[이전 블록]</a>";
		}
		//각 페이지 번호 출력
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				//현재 페이지는 링크를 걸지 않음
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				//현재 페이지가 아니면 링크를 걸어준다.
				pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
							+ "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		//다음 페이지 블록 바로가기 출력
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
						+"'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl +"?pageNum=" + totalPages
						+"'>[마지막 페이지]</a>";
		}
		return pagingStr;
	}

}
