package kr.or.iei.product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.img.model.vo.Img;
import kr.or.iei.product.model.dao.ProductDAO;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductAll;
import kr.or.iei.product.model.vo.ProductAllSub;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.product.model.vo.ProductPageData;
import kr.or.iei.product.model.vo.ProductWrite;

public class ProductService {
	ProductDAO productDAO = new ProductDAO();
	ProductDAO pDAO=new ProductDAO();
	public boolean insertProduct(Product productInfo, ProductOption productOption,
			Img imgData_sub, Img imgData_main) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = productDAO.insertProduct(conn, productInfo, productOption, imgData_sub,
				imgData_main);
		if (result == true) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean deleteProduct(int productNo, int imgNo) {
	      Connection conn = JDBCTemplate.getConnection();
	      boolean result=productDAO.deleteProduct(conn, productNo, imgNo);
	      
	      if (result) {
	         JDBCTemplate.commit(conn);
	      } else {
	         JDBCTemplate.rollback(conn);
	      }
	      JDBCTemplate.close(conn);
	      
	      return result;
	   }

	public ProductAll selectOneProduct(int productNo) {
	      Connection conn = JDBCTemplate.getConnection();
	      ProductAll productAll=productDAO.selectOneProduct(conn, productNo);
	      JDBCTemplate.close(conn);
	      return productAll;
	   }
	
	public ProductPageData selectAllProductPage(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage =10; //한페이지당 몇개를 보이게 할 것인지 결정
		
		ArrayList<ProductAll> list = productDAO.selectAllProductPageList(conn,currentPage,recordCountPerPage);
		
		
		int naviCountPerPage = 5; //navi개수를 몇개를 보여줄 것인지 결정
		String pageNavi = productDAO.getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		
		ProductPageData ppd = new ProductPageData();
		ppd.setList(list);
		ppd.setPageNavi(pageNavi);
		JDBCTemplate.close(conn);
		
		return ppd;
		
	}
	
	   public boolean updateProduct(Product productInfo, ProductOption productOption, Img imgData_sub, Img imgData_main) {
		      Connection conn = JDBCTemplate.getConnection();
		      boolean result = productDAO.updateProduct(conn, productInfo, productOption, imgData_sub, imgData_main);
		      if (result == true) {
		         JDBCTemplate.commit(conn);
		      } else {
		         JDBCTemplate.rollback(conn);
		      }
		      JDBCTemplate.close(conn);
		      return result;
		   }
	   
	   public ArrayList<ProductAll> searchProductName(String keyword, String startDate, String endDate) {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<ProductAll> list = productDAO.searchProductName(conn, keyword, startDate, endDate);
			JDBCTemplate.close(conn);
			
			return list;
	}

	public ArrayList<ProductAll> searchProductNo(int keywordNo, String startDate, String endDate) {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<ProductAll> list = productDAO.searchProductNo(conn,keywordNo, startDate, endDate);
			JDBCTemplate.close(conn);
			
			return list;
	}

	public ArrayList<ProductAll> searchProductCategoryNo(int keywordNo, String startDate, String endDate) {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<ProductAll> list = productDAO.searchProductCategoryNo(conn, keywordNo, startDate, endDate);
			JDBCTemplate.close(conn);
			
			return list;
	}
	

public ArrayList<ProductAll> storeMainNew() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.storeMainNew(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<ProductAll> storeMainHot() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.storeMainHot(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<ProductAll> storeMainBest() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.storeMainBest(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<ProductWrite> storeMainTop() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductWrite> list = pDAO.storeMainTop(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	public ArrayList<ProductAll> printIMG(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.printIMG(conn,categoryNo);
		JDBCTemplate.close(conn);
		
		return list;
	}


	public ArrayList<ProductAll> printIMG() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.printIMG(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}


	public ArrayList<ProductAll> productAlign(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.productAlign(conn,categoryNo);
		JDBCTemplate.close(conn);
		
		return list;
	}


	public ArrayList<ProductAll> productAlignPrice(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.productAlignPrice(conn,categoryNo);
		JDBCTemplate.close(conn);
		
		return list;
	}


	public ArrayList<ProductAll> productAlignNew(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.productAlignNew(conn,categoryNo);
		JDBCTemplate.close(conn);
		
		return list;
	}


	public ArrayList<ProductAll> productAlignPopular(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductAll> list = pDAO.productAlignPopular(conn,categoryNo);
		JDBCTemplate.close(conn);
		
		return list;
		
	}

public ProductAllSub selectOneProductList(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		ProductAllSub product_main =  productDAO.selectOneProductStoreMain(conn,productNo);
		ProductAllSub product_sub =  productDAO.selectOneProductStoreSub(conn,productNo);
		
		ProductAllSub oneProduct = new ProductAllSub();
		
		oneProduct.setCategory(product_main.getCategory());
		oneProduct.setProduct(product_main.getProduct());
		oneProduct.setpDate(product_main.getpDate());
		oneProduct.setpOption(product_main.getpOption());
		oneProduct.setImg(product_main.getImg());
		
		oneProduct.setpDetail(product_sub.getpDetail());
		oneProduct.setImg_sub(product_sub.getImg_sub());
							
		JDBCTemplate.close(conn);
		return oneProduct;
	}

	public ArrayList<ProductWrite> selectpncList(int productNo) {
		Connection conn = JDBCTemplate.getConnection();	
		ArrayList<ProductWrite> list2 = productDAO.selectpncList2(conn,productNo);		
	
		JDBCTemplate.close(conn);
		return list2;
	}
	}



