package kr.or.iei.question.model.vo;

public class QuestionCategory {
	private int questionCategoryNo;
	private String questionCategoryName;
	public QuestionCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionCategory(int questionCategoryNo, String questionCategoryName) {
		super();
		this.questionCategoryNo = questionCategoryNo;
		this.questionCategoryName = questionCategoryName;
	}
	public int getQuestionCategoryNo() {
		return questionCategoryNo;
	}
	public void setQuestionCategoryNo(int questionCategoryNo) {
		this.questionCategoryNo = questionCategoryNo;
	}
	public String getQuestionCategoryName() {
		return questionCategoryName;
	}
	public void setQuestionCategoryName(String questionCategoryName) {
		this.questionCategoryName = questionCategoryName;
	}
	
}
