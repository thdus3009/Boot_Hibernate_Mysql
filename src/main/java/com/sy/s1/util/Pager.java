package com.sy.s1.util;


public class Pager {

	private Long curPage;
	private Integer perPage;
	
	private long startRow;
	private long lastRow;
	
	private long totalPage;
	private long totalBlock; //1~5페이지 > 1블럭 //총30페이지 > 6블럭
	private long curBlock;
	
	private long startNum; //블럭 시작 번호 1
	private long lastNum; //블럭 끝 번호 5
	
	private String kind;
	private String search;
	private String bar;
	


	//페이지당 10개 출력 > perpage=10
	public void makeRow() {
		this.startRow=this.getCurPage()-1;
		this.lastRow=this.getCurPage()*this.getPerPage();
	}
	//다음페이지 생성
	public void makePage(long totalCount) {
		//1. totalCount : 전체 글의 갯수
		//2. totalCount로 totalPage 계산
		this.totalPage =  totalCount/this.getPerPage();
		if(totalCount%this.getPerPage()!=0) {
			this.totalPage++;
		}
		
		
		//3. totalPage -> totalBlock 계산
		long perBlock = 5L;// 블록당 5개씩 설정
		
		this.totalBlock = totalPage/perBlock;
		if(totalPage % perBlock !=0) {
			this.totalBlock++;
		}
		
		//4. curpage ->curBlock찾기(몇번째 블록인가) curpage 1-5 curblock 1, curpage 6-10 curblock 2
		this.curBlock = this.curPage/perBlock;
		if(this.curPage % perBlock !=0) {
			this.curBlock++;
		}
		
		//5. curBlock startnum,lastnum계산
		this.startNum = (this.curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		//마지막 번호면 거기서 끊어주기
		if(this.curBlock == this.totalBlock) { // 5개씩 끊지만 글 갯수에 맞게되도록
			this.lastNum = this.totalPage;
		}
	}
	
	
	
	
	
	
	
	public Long getCurPage() {
		if(this.curPage==null || this.curPage==0) {
			this.curPage=1L;
		}
		
		return curPage;
	}
	
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	
	public Integer getPerPage() {
		if(this.perPage==null || this.perPage==0) {
			this.perPage=10;
		}
		return perPage;
	}
	
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	
	public long getStartRow() {
		return startRow;
	}
	
	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}
	
	public long getLastRow() {
		return lastRow;
	}
	
	public void setLastRow(long lastRow) {
		this.lastRow = lastRow;
	}
	
	public long getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	public long getTotalBlock() {
		return totalBlock;
	}
	
	public void setTotalBlock(long totalBlock) {
		this.totalBlock = totalBlock;
	}
	
	public long getCurBlock() {
		return curBlock;
	}
	
	public void setCurBlock(long curBlock) {
		this.curBlock = curBlock;
	}
	
	public long getStartNum() {
		return startNum;
	}
	
	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}
	
	public long getLastNum() {
		return lastNum;
	}
	
	public void setLastNum(long lastNum) {
		this.lastNum = lastNum;
	}
	

	
	
	
	
	public String getKind() {
		
		 if(this.kind== null) {
			 this.kind="title";
		 }
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search= "";
		}
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
}
