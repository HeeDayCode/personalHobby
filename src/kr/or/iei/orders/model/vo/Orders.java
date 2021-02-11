package kr.or.iei.orders.model.vo;

import java.sql.Date;

public class Orders {
	private int ordersNo;
	private int memberNo;
	private int productNo;
	private Date ordersDate;
	private int ordersNum;
	private char ordersPay;
	private char deliveryYN;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private String demand;
	private char receiveYN;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(int ordersNo, int memberNo, int productNo, Date ordersDate, int ordersNum, char ordersPay,
			char deliveryYN, String receiverName, String receiverPhone, String receiverAddress, String demand,
			char receiveYN) {
		super();
		this.ordersNo = ordersNo;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.ordersDate = ordersDate;
		this.ordersNum = ordersNum;
		this.ordersPay = ordersPay;
		this.deliveryYN = deliveryYN;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.demand = demand;
		this.receiveYN = receiveYN;
	}

	public int getOrdersNo() {
		return ordersNo;
	}

	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
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

	public Date getOrdersDate() {
		return ordersDate;
	}

	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}

	public int getOrdersNum() {
		return ordersNum;
	}

	public void setOrdersNum(int ordersNum) {
		this.ordersNum = ordersNum;
	}

	public char getOrdersPay() {
		return ordersPay;
	}

	public void setOrdersPay(char ordersPay) {
		this.ordersPay = ordersPay;
	}

	public char getDeliveryYN() {
		return deliveryYN;
	}

	public void setDeliveryYN(char deliveryYN) {
		this.deliveryYN = deliveryYN;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public char getReceiveYN() {
		return receiveYN;
	}

	public void setReceiveYN(char receiveYN) {
		this.receiveYN = receiveYN;
	}

}