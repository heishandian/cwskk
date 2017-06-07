package com.yaoli.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.util.GetVideoUrlTokenUtil;
import com.yaoli.util.JsonMessage;
import com.yaoli.vo.UeditorVO;
import com.yaoli.vo.VideoUrlTokenVO;

@Controller
@RequestMapping("/util")
public class UtilController {
	
	@RequestMapping("/gotoGetVideoToken.do")
	public String getAdminsByPaing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "/util/getvideotoken";
	}
	
	@RequestMapping("/getVideoToken.do")
	public void getVideoToken(@RequestBody VideoUrlTokenVO videoUrlTokenVO,HttpServletRequest request,HttpServletResponse response) throws IOException {
		videoUrlTokenVO.setDateString(String.valueOf(System.currentTimeMillis()/1000L));
		
		GetVideoUrlTokenUtil util = new GetVideoUrlTokenUtil();
		util.setVideoUrlTokenVO(videoUrlTokenVO);
		
		//获取url
		String url = util.getURL();
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		jsonMessage.setData(url);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/gotoFormatUrl.do")
	public String gotoFormatUrl(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "/util/formatsql";
	}
	
	@RequestMapping("/formatsql.do")
	public void gotoFormatUrl(@RequestBody UeditorVO ueditorVO, HttpServletRequest request,HttpServletResponse response) throws IOException{
		String content = ueditorVO.getContent();
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		jsonMessage.setData(content);
		String codeString = 
				 "		--断电断线"
				+"		declare InsertCursor cursor    --声明一个游标，查询满足条件的数据"
				+"	        for "
				+"	        "
				+"			select distinct temp1.sewageID from "
				+"			("
				+"				(select sewageid from "
				+"				(select temp1.sewageID,temp1.testingtime,sewage.areaID from "
				+"					sewage left join  "
				+"					(select sewageid,max(testingtime) as testingtime from detection_data group by sewageid ) as temp1 "
				+"				on sewage.sewageID = temp1.sewageId and CONVERT(varchar(12),testingtime,111) != CONVERT(varchar(12),getdate() ,111) "
				+"				) "
				+"				as tmp)"
				+"				union all"
				+"				(select sewage.sewageid from (select distinct detection_data.sewageid from detection_data) "
				+"				tmp1 right join sewage on  "
				+"				sewage.sewageid = tmp1.sewageid where tmp1.sewageid is null "
				+"				)"
				+"			) as temp1,sewage where temp1.sewageID = sewage.sewageID"
				+"		"
				+"	    "
				+"	    open InsertCursor    --打开"
				+"	    "
				+"	    declare @sewageid varchar(20)    --声明一个变量，用于读取游标中的值"
				+"	        fetch next from InsertCursor into @sewageid"
				+"	    "
				+"	    while @@fetch_status=0    --循环读取"
				+"	        begin"
				+"				declare @hour int"
				+"				set @hour = 0"
				+"				while @hour <= 23"
				+"					begin"
				+"	        			print @sewageid"
				+"						insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection4,detection5,detection6) values("
				+"						@sewageid,"
				+"						convert(datetime,CONVERT(varchar(11),GETDATE(),120)+CAST(@hour as varchar)+':00:00',120),"
				+"						21+cast(RAND() as numeric(18,2)),"
				+"						7+cast(RAND() as numeric(18,2)),"
				+"						cast(RAND() as numeric(18,4))*100+450,"
				+"						1,"
				+"						cast(RAND() as numeric(18,2))+5,"
				+"						cast(RAND() as numeric(18,2))+2"
				+"						);"
				+"						insert into run_data(sewageid,testingtime,equipment1state,equipment2state,equipment3state,equipment4state,equipment5state) values("
				+"						@sewageid,"
				+"						convert(datetime,CONVERT(varchar(11),GETDATE(),120)+CAST(@hour as varchar)+':00:00',120),"
				+"						1,"
				+"						1,"
				+"						1,"
				+"						1,"
				+"						1"
				+"						); "
				+"						set @hour = @hour + 1 "
				+"					end "
				+"	"
				+"				fetch next from InsertCursor into @sewageid"
				+"	        end"
				+"	    "
				+"	    close InsertCursor    --关闭"
				+"	    "
				+"	    deallocate InsertCursor    --删除";
		
		response.setContentType("text/html; charset=UTF-8");
		String result = JSON.toJSONString(jsonMessage);
		response.getWriter().write(result);
	}
	
	@RequestMapping("/resources/plugin/ueditor.do")
	public void baiduEdit(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			
/*			//String rootPath = request.getServletContext().getRealPath("/");
			String rootPath = request.getServletContext().getRealPath("/");
			//针对配置百度上传附件读取配置文件
			ActionMap.mapping.put("config", ActionMap.CONFIG);
			//上传文件
			ActionMap.mapping.put("uploadfile", ActionMap.UPLOAD_FILE);
			ActionEnter actionEnter = new ActionEnter(request, rootPath);
			response.getWriter().write(actionEnter.exec());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/gotoUploadFile.do")
	public String gotoUploadFile(){
		return "/util/uploadFileTest";
	}


	
}
