package kr.or.iei.question.model.vo;

import java.sql.Date;

public class Board {
// 게시판에 출력하기 위한 정보를 받아올 vo
   private int boardNo;
   private String productName;
   private int productNo;
   private String boardCategoryName;
   private String boardName;
   private String boardText;
   private String userNickname;
   private int boardViewCount;
   private Date boardDate;
   
   public Board() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   public Board(int boardNo, String productName, int productNo, String boardCategoryName, String boardName,
	         String boardText, String userNickname, int boardViewCount, Date boardDate) {
	      super();
	      this.boardNo = boardNo;
	      this.productName = productName;
	      this.productNo = productNo;
	      this.boardCategoryName = boardCategoryName;
	      this.boardName = boardName;
	      this.boardText = boardText;
	      this.userNickname = userNickname;
	      this.boardViewCount = boardViewCount;
	      this.boardDate = boardDate;
	   }


   public int getboardNo() {
      return boardNo;
   }

   public void setboardNo(int boardNo) {
      this.boardNo = boardNo;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public int getProductNo() {
      return productNo;
   }

   public void setProductNo(int productNo) {
      this.productNo = productNo;
   }

   public String getboardCategoryName() {
      return boardCategoryName;
   }

   public void setboardCategoryName(String boardCategoryName) {
      this.boardCategoryName = boardCategoryName;
   }

   public String getboardName() {
      return boardName;
   }

   public void setboardName(String boardName) {
      this.boardName = boardName;
   }

   public String getboardText() {
      return boardText;
   }

   public void setboardText(String boardText) {
      this.boardText = boardText;
   }

   public String getuserNickname() {
      return userNickname;
   }

   public void setuserNickname(String userNickname) {
      this.userNickname = userNickname;
   }

   public int getboardViewCount() {
      return boardViewCount;
   }

   public void setboardViewCount(int boardViewCount) {
      this.boardViewCount = boardViewCount;
   }

   public Date getboardDate() {
      return boardDate;
   }

   public void setboardDate(Date boardDate) {
      this.boardDate = boardDate;
   }
   
   
}