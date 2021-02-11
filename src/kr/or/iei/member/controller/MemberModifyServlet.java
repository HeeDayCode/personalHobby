package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberAddress;
import kr.or.iei.member.model.vo.MemberAll;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/Modify.kh")
public class MemberModifyServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      //response.getWriter().append("Served at: ").append(request.getContextPath());
      request.setCharacterEncoding("utf-8");
      
      //2. view페이지에서 보낸 데이터를 자바 변수에 저장
         
      HttpSession session = request.getSession();
      
      MemberAll mAll=(MemberAll)session.getAttribute("memberAll");
      String memberId = mAll.getM().getMemberId();
      int memberNo = mAll.getM().getMemberNo();      
      
      
      String pw = request.getParameter("memberPw");
      StringBuffer sb = new StringBuffer();
      try {
         MessageDigest md=MessageDigest.getInstance("MD5");//SHA-1 또는 MD5
         md.update(pw.getBytes());
         byte [] digest = md.digest();
   
         for(byte b:digest) {
            sb.append(Integer.toHexString(b&0xff));
         }
         
      } catch (NoSuchAlgorithmException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      pw=sb.toString();
      
      
      
      String memberPw = pw;
      String memberName = request.getParameter("memberName");
      String memberEmail = request.getParameter("memberEmail");
      String memberNickname = request.getParameter("memberNickname");
      
      String phone1 = request.getParameter("phone1");
      String phone2 = request.getParameter("phone2");
      String phone3 = request.getParameter("phone3");
      
      String memberPhone=phone1+phone2+phone3;
      String address=request.getParameter("address");
      int postNum=Integer.parseInt(request.getParameter("postNum"));
      Member m = new Member();
      m.setMemberNo(memberNo);
      m.setMemberId(memberId);
      m.setMemberPw(memberPw);
      m.setMemberName(memberName);
      m.setMemberEmail(memberEmail);
      m.setMemberNickname(memberNickname);
      m.setMemberPhone(memberPhone);
      MemberAddress ma=new MemberAddress();
      ma.setAddress(address);
      ma.setPostNum(postNum);   
      mAll=new MemberAll();
      mAll.setM(m);
      mAll.setMa(ma);
      
  
      int result = new MemberService().updateMember(mAll);
      
       RequestDispatcher view = request.getRequestDispatcher("/views/member/myPage/modifyResult.jsp");
         
    
       if(result>0)
       {
          request.setAttribute("result", true);
           session.setAttribute("memberAll", mAll); //session 갱신
           request.setAttribute("memberAll", mAll);
           
       }else {
       
          request.setAttribute("result", false);
       }
       
       view.forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}