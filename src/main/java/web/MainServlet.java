package web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dao.CostDao;
import dao.CostDaoImpl;
import entity.Admin;
import entity.Cost;
import util.ImageUtil;

public class MainServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException {
		String p=req.getServletPath();
		if("/findCost.do".equals(p)){
			findCost(req,res);
		}else if("/toAddCost.do".equals(p)){
			toAddCost(req,res);
		}else if("/addCost.do".equals(p)){
			addCost(req,res);
		}else if("/toUpdateCost.do".equals(p)){
			toUpdateCost(req,res);
		}else if("/updateCost.do".equals(p)){
			updateCost(req,res);
		}else if("/toLogin.do".equals(p)){
			toLogin(req,res);			
		}else if("/toIndex.do".equals(p)){
			toIndex(req,res);
		}else if("/login.do".equals(p)){
			login(req,res);
		}else if("/createImg.do".equals(p)){
			createImg(req,res);
		}else if("/toDelete.do".equals(p)){
			toDelete(req,res);
		}else if("/list.do".equals(p)){
			list(req,res);
		}else{
			throw new RuntimeException("查无此页");
		}
	}
	//分页
	private void list(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		CostDao dao=new CostDaoImpl();
		List<Cost> list=dao.findAll();
		int totalPage=(list.size()/10+1);
		String pages=req.getParameter("page");
		int page=Integer.parseInt(pages);
		if(10*page<list.size()){
			list=list.subList(10*(page-1),10*page);
		}else{
			list=list.subList(10*(page-1),list.size());
		}
		
		System.out.println(list.size());
		System.out.println(pages);
		System.out.println(page);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		req.setAttribute("costs", list);
		req.getRequestDispatcher("/WEB-INF/cost/find.jsp")
		.forward(req, res);
	}
	//打开资费页面
	protected void findCost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		CostDao dao=new CostDaoImpl();
		List<Cost> list=dao.findAll();
		int totalPage=(list.size()/10+1);
		
		list=list.subList(0,10);
		//System.out.println(list.size());
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("costs", list);
		req.getRequestDispatcher("/WEB-INF/cost/find.jsp")
		.forward(req, res);
	}
	private void toDelete(HttpServletRequest req, 
			HttpServletResponse res)throws ServletException, 
	IOException {
		req.getRequestDispatcher("/WEB-INF/cost/add.jsp")
		.forward(req, res);
		
	}
	//生验证码
	private void createImg(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		Object[] objs=ImageUtil.createImage();
		HttpSession session=req.getSession();
		session.setAttribute("imgcode", objs[0]);
		res.setContentType("image/png");
		OutputStream os=res.getOutputStream();
		BufferedImage image=(BufferedImage) objs[1];
		ImageIO.write(image, "png", os);
		os.close();
	}
	//登录检查
	private void login(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		//接受参数
		String adminCode=req.getParameter("adminCode");
		String password=req.getParameter("password");
		String code=req.getParameter("code");
		HttpSession session =req.getSession();
		String imgcode=(String) session.getAttribute("imgcode");
		if(code==null||!code.equalsIgnoreCase(imgcode)){
			System.out.println(imgcode);
			System.out.println(adminCode);
			System.out.println(password);
			req.setAttribute("error1","验证码错误");
			req.getRequestDispatcher("WEB-INF/login.jsp")
			.forward(req, res);
			return;
		}
		AdminDao dao=new AdminDaoImpl();
		Admin a=dao.findByCode(adminCode);
		if(a==null){
			req.setAttribute("error","账号密码错误");
			req.getRequestDispatcher("WEB-INF/login.jsp")
			.forward(req, res);
		}else if(!a.getPassword().equals(password)){
			req.setAttribute("error","密码错误");
			req.getRequestDispatcher("WEB-INF/login.jsp")
			.forward(req, res);
		}else{
			Cookie c=new Cookie("adminCode", adminCode);
			res.addCookie(c);
			
			session.setAttribute("adminCode", adminCode);
			res.sendRedirect("toIndex.do");
		}
		
	}
	//打开主页
	private void toIndex(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		req.getRequestDispatcher("/WEB-INF/index.jsp")
		.forward(req, res);
	}
	//打开登录页面
	private void toLogin(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		req.getRequestDispatcher("/WEB-INF/login.jsp")
		.forward(req, res);
	}

	//打开增加资费页面
	private void toAddCost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		req.getRequestDispatcher("/WEB-INF/cost/add.jsp")
		.forward(req, res);
	}

	//增加资费数据
	protected void addCost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		//接收参数
		req.setCharacterEncoding("utf-8");
		String name=req.getParameter("name");
		String costType=req.getParameter("costType");
		String baseDuration=req.getParameter("baseDuration");
		String baseCost=req.getParameter("baseCost");
		String unitCost=req.getParameter("unitCost");
		String descr=req.getParameter("descr");
		//保存以上数据
		Cost c=new Cost();
		c.setName(name);
		c.setCostType(costType);
		if(baseDuration!=null&&
				!baseDuration.equals("")){
			c.setBaseDuration(new Integer(baseDuration));
		}
		if(baseCost!=null&&
				!baseCost.equals("")){
			c.setBaseCost(new Double(baseCost));
		}
		
		if(unitCost!=null&&
				!unitCost.equals("")){
			c.setBaseCost(new Double(baseCost));
		}
		c.setDescr(descr);
		CostDao dao=new CostDaoImpl();
		dao.save(c);
		//重定向到查询功能
		res.sendRedirect("findCost.do");
		
	}
	protected void toUpdateCost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		//接收参数		
		String id=req.getParameter("id");
		CostDao dao=new CostDaoImpl();
		Cost cost=dao.findById(new Integer(id));
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("WEB-INF/cost/update.jsp")
		.forward(req, res);
		
	}
	protected void updateCost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, 
	IOException{
		//接收参数
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("cost_id");
		String name=req.getParameter("name");
		String costType=req.getParameter("costType");
		String baseDuration=req.getParameter("baseDuration");
		String baseCost=req.getParameter("baseCost");
		String unitCost=req.getParameter("unitCost");
		String descr=req.getParameter("descr");
		System.out.println(id);
		//保存以上数据
		Cost c=new Cost();
		if(id!=null&&
				!id.equals("")){
			c.setCostId(new Integer(id));
		}
		
		c.setName(name);
		c.setCostType(costType);
		if(baseDuration!=null&&
				!baseDuration.equals("")){
			c.setBaseDuration(new Integer(baseDuration));
		}
		if(baseCost!=null&&
				!baseCost.equals("")){
			c.setBaseCost(new Double(baseCost));
		}
		
		if(unitCost!=null&&
				!unitCost.equals("")){
			c.setBaseCost(new Double(unitCost));
		}
		c.setDescr(descr);
		CostDao dao=new CostDaoImpl();
		dao.update(c);
		//重定向到查询功能
		res.sendRedirect("findCost.do");
		
	}

}
