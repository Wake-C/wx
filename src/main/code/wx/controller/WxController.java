package wx.controller;

import wx.servic.WxServic;
import wx.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WxController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter pw = resp.getWriter();

		if (SignUtil.checkString(signature, timestamp, nonce)) { // 返回微信核对
			pw.print(echostr);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			String signature =req.getParameter("signature");
			String timestamp =req.getParameter("timestamp");
			String nonce=req.getParameter("nonce");
			
			PrintWriter out =resp.getWriter();
			
			if(SignUtil.checkString(signature, timestamp, nonce)){
				String resXml =WxServic.processRequest(req);  //处理信息
				out.print(resXml);
			}
			out.close();
			out=null;
	}

}
