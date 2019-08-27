package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.spring.board.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.common.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bService; //BoardService 느슨한 결합구조를위해서 interface로 생성했음
	
	
	@RequestMapping("blist.do")
	public ModelAndView boardList(@RequestParam(value="page",required=false) Integer page,ModelAndView mv) throws BoardException {
		
		
		
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
			
		}
		
		int listCount = bService.getListCount();
	
		
		PageInfo pi =  Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		if(list !=null) {
			mv.addObject("list",list);
			mv.addObject("pi",pi);
			mv.setViewName("board/boardListView");
			 	
		}else {
			
			throw new BoardException("게시글 전체 조회에 실퍃였습니다");
			
		}
		
		return mv;
		
		
	}
	
	@RequestMapping("binsertView.do")
	public String boardInsertView() {
		
		
		return"board/boardInsertForm";
		
		
	}
	
	//객체를 한번에 받아올때 사용하는 Annotation ==>ModelAtribute
	//Board 객체에는 uploatFile이 없으므로 RequestParam 을 사용해서가져온다
	@RequestMapping("binsert.do")
	public String boardInsert(@ModelAttribute Board b,@RequestParam(value="uploadFile" ,required=false) MultipartFile uploadFile,
			HttpServletRequest request) throws BoardException {
		System.out.println(b);
		System.out.println(uploadFile);
		System.out.println(uploadFile.getOriginalFilename());
		
		//파일을 넣지 않은 경우 파일 이름은 ""로들어감
		
		//if(!uploadFile.getOriginalFilename().equals("")) {	
		if(uploadFile !=null && !uploadFile.isEmpty()) {
				//저장할 경로를 지정하는 saveFile()메소드 생성
			
			String renameFileName = saveFile(uploadFile,request);
			
			if(renameFileName !=null) {
				b.setOriginalFileName(uploadFile.getOriginalFilename());
				b.setRenameFileName(renameFileName);
			}
			
		}
		
		int result = bService.insertBoard(b);
		
		if(result>0) {
			return "redirect:blist.do";
		}else {
			throw new BoardException("게시글 등록에 실패하였습니다.");
		}
		
	
		
	}
	
	public String saveFile(MultipartFile file,HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			
			folder.mkdirs();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))+"."
				+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		
		String renamePath =folder +"\\" +renameFileName;
		
			
		try {
			file.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return renameFileName;
		
		
		
		
	}
	
	@RequestMapping("bdetail.do") //상세보기할때 이전페이지 까지 가져올수있도록
	public ModelAndView boardDetail(@RequestParam("bId") int bId,@RequestParam("page") int page,
				ModelAndView mv) throws BoardException {
		
		bService.addReadCount(bId);
		Board board = bService.selectBoard(bId);
		
		if(board !=null) {
			mv.addObject("board",board)
			.addObject("page",page)
			.setViewName("board/boardDetailView"); // 겹쳐서 쓰기도 가능하다
		}else{
			throw new BoardException("게시물 상세보기에 실패하였습니다.");
		}
				
		return mv;
	}
	@RequestMapping("bupView.do")
	   public ModelAndView boardUpdateView(@RequestParam("bId") int bId, @RequestParam("page") int page, ModelAndView mv) {
	      Board board = bService.selectBoard(bId);
	      
	      mv.addObject("board", board).addObject("page", page).setViewName("board/boardUpdateForm");
	      
	      return mv;
	      
	   }
	
	@RequestMapping("bupdate.do")
	public ModelAndView boardUpdate(ModelAndView mv,@ModelAttribute Board b,
			@RequestParam("page") Integer page,
			@RequestParam("reloadFile") MultipartFile reloadFile,
			HttpServletRequest request) throws BoardException {
			
		if(reloadFile !=null && !reloadFile.isEmpty()) {
			if(b.getRenameFileName()!=null) {
				deleteFile(b.getRenameFileName(),request);
			}
			String renameFileName = saveFile(reloadFile,request);
			
			if(renameFileName !=null) {
				b.setOriginalFileName(reloadFile.getOriginalFilename());
				b.setRenameFileName(renameFileName);
			}
			
			int result = bService.updateBoard(b);
			
			if(result >0) {
				mv.addObject("page",page)
				  .setViewName("redirect:bdetail.do?bId="+b.getbId());
			}else {
				throw new BoardException("게시글 등록을 실패하였습니다.");
			}
		}
		
		return mv;
		
	}
	
	
	public void deleteFile(String fileName,HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root +"\\buploadFiles";
		
		File f = new File(savePath +"\\" + fileName);
		
		if(f.exists()) {
			f.delete();
		}
	}
	
	@RequestMapping("bdelete.do")
	public String boardDelete(@ModelAttribute Board b) {
		
		return null;
	}
	
//	@RequestMapping("topList.do")
//	public void boardTopList(HttpServletResponse response) throws IOException {
//		response.setContentType("application/json; charset =utf-8");
//		
//		ArrayList<Board> list = bService.selectTopList();
//		
//		JSONArray jArr = new JSONArray();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		for(Board b: list) {
//			
//			JSONObject jobj = new JSONObject();
//			jobj.put("bId", b.getbId());
//			jobj.put("bTitle",b.getbTitle());
//			jobj.put("bwriter",b.getbWriter());
//			jobj.put("originalFileName",b.getOriginalFileName());
//			jobj.put("bCount",b.getbCount());
//			jobj.put("bCreateDate",sdf.format(b.getbCreateDate()));
//			
//			jArr.add(jobj);
//			
//			
//		}
//		
//		JSONObject sendJson = new JSONObject();;
//		sendJson.put("list",jArr);
//		
//		PrintWriter out = response.getWriter();
//		out.print(sendJson);
//		out.flush();
//		out.close();
//	}
	
	
//	@RequestMapping("topList.do")
//	public String boardTopList() throws UnsupportedEncodingException, JsonProcessingException{
//		
//		ArrayList<Board> list = bService.selectTopList();
//		
//		 for(Board b :list) {
//			 b.setbTitle(URLEncoder.encode(b.getbTitle(),"utf-8"));
//		 }
//		 
//		 //mapper를 이용해서 list를 String으로 바꿔서 이용
//		 ObjectMapper mapper = new ObjectMapper();
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		 mapper.setDateFormat(sdf);
//		 
//		 String jsonStr = mapper.writeValueAsString(list);
//		 
//		 return jsonStr;
//
//		}
	
	
	
	@RequestMapping("topList.do")
	public void boardTopList(HttpServletResponse response) throws IOException{
		
		ArrayList<Board> list = bService.selectTopList();
		
		 for(Board b :list) {
			 b.setbTitle(URLEncoder.encode(b.getbTitle(), "utf-8"));
		 }
		 
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 gson.toJson(list,response.getWriter());
		 


		}
	
}
