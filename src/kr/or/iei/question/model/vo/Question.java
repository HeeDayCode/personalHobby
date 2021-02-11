package kr.or.iei.question.model.vo;

import java.sql.Date;

public class Question {
	private int questionNo;
	private int memberNo;
	private int productNo;
	private int questionCategoryNo;
	private String questionText;
	private int questionViewCount;
	private Date questionDate;
	private Date questionDeleteDate;
	private char questionDeleteYN;
	private String questionName;
	
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int questionNo, int memberNo, int productNo, int questionCategoryNo, String questionText,
			int questionViewCount, Date questionDate, Date questionDeleteDate, char questionDeleteYN,
			String questionName) {
		super();
		this.questionNo = questionNo;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.questionCategoryNo = questionCategoryNo;
		this.questionText = questionText;
		this.questionViewCount = questionViewCount;
		this.questionDate = questionDate;
		this.questionDeleteDate = questionDeleteDate;
		this.questionDeleteYN = questionDeleteYN;
		this.questionName = questionName;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getQuestionCategoryNo() {
		return questionCategoryNo;
	}
	public void setQuestionCategoryNo(int questionCategoryNo) {
		this.questionCategoryNo = questionCategoryNo;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getQuestionViewCount() {
		return questionViewCount;
	}
	public void setQuestionViewCount(int questionViewCount) {
		this.questionViewCount = questionViewCount;
	}
	public Date getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}
	public Date getQuestionDeleteDate() {
		return questionDeleteDate;
	}
	public void setQuestionDeleteDate(Date questionDeleteDate) {
		this.questionDeleteDate = questionDeleteDate;
	}
	public char getQuestionDeleteYN() {
		return questionDeleteYN;
	}
	public void setQuestionDeleteYN(char questionDeleteYN) {
		this.questionDeleteYN = questionDeleteYN;
	}
	public String getQuesionName() {
		return questionName;
	}
	public void setQuesionName(String quesionName) {
		this.questionName = quesionName;
	}

	
}