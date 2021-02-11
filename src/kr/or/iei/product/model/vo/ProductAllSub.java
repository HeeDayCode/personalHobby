package kr.or.iei.product.model.vo;

import kr.or.iei.img.model.vo.Img;

public class ProductAllSub {
   private Category category;
   private Product product;
   private ProductDate pDate;
   private ProductDetail pDetail;
   private ProductOption pOption;
   private Img img;
   private Img img_sub;
   
   public ProductAllSub() {
      super();
      // TODO Auto-generated constructor stub
   }

   public ProductAllSub(Category category, Product product, ProductDate pDate, ProductDetail pDetail,
		ProductOption pOption, Img img, Img img_sub) {
	super();
	this.category = category;
	this.product = product;
	this.pDate = pDate;
	this.pDetail = pDetail;
	this.pOption = pOption;
	this.img = img;
	this.img_sub = img_sub;
	}
   public Category getCategory() {
      return category;
   }
   public void setCategory(Category category) {
      this.category = category;
   }
   public Product getProduct() {
      return product;
   }
   public void setProduct(Product product) {
      this.product = product;
   }
   public ProductDate getpDate() {
      return pDate;
   }
   public void setpDate(ProductDate pDate) {
      this.pDate = pDate;
   }
   public ProductDetail getpDetail() {
      return pDetail;
   }
   public void setpDetail(ProductDetail pDetail) {
      this.pDetail = pDetail;
   }
   public ProductOption getpOption() {
      return pOption;
   }
   public void setpOption(ProductOption pOption) {
      this.pOption = pOption;
   }
   public Img getImg() {
      return img;
   }
   public void setImg(Img img) {
      this.img = img;
   }
	public Img getImg_sub() {
		return img_sub;
	}
	public void setImg_sub(Img img_sub) {
		this.img_sub = img_sub;
	}
   
   
}