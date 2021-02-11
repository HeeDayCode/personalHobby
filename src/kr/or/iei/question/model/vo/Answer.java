package kr.or.iei.question.model.vo;

import java.sql.Date;

public class Answer {
	private int questionNo;
	private String answerText;
	private Date answerDate;
	private Date answerDeleteDate;
	private char answerDeleteYN;
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int questionNo, String answerText, Date answerDate, Date answerDeleteDate, char answerDeleteYN) {
		super();
		this.questionNo = questionNo;
		this.answerText = answerText;
		this.answerDate = answerDate;
		this.answerDeleteDate = answerDeleteDate;
		this.answerDeleteYN = answerDeleteYN;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	public Date getAnswerDeleteDate() {
		return answerDeleteDate;
	}
	public void setAnswerDeleteDate(Date answerDeleteDate) {
		this.answerDeleteDate = answerDeleteDate;
	}
	public char getAnswerDeleteYN() {
		return answerDeleteYN;
	}
	public void setAnswerDeleteYN(char answerDeleteYN) {
		this.answerDeleteYN = answerDeleteYN;
	}
	
}