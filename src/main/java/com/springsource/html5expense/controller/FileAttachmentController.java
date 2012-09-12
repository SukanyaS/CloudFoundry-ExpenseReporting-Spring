package com.springsource.html5expense.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.service.AttachmentService;
import com.springsource.html5expense.service.ExpenseService;



@Controller
public class FileAttachmentController {

	 	@Autowired
	    private ExpenseService expenseService;
	 	
	 	@Autowired
	 	private AttachmentService attachmentService;
	     
	    @RequestMapping("/index")
	    public String index(Map<String, Object> map) {
	        try {
	            map.put("attachment", new Attachment());
	            map.put("documentList", attachmentService.getAttachmentByExpenseId(new Long(1)));
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	 
	        return "documents";
	    }
	 
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(@RequestParam("file") MultipartFile file) {
	         
	         
	        try {
	        	
	            @SuppressWarnings("deprecation")
				Blob content = Hibernate.createBlob(file.getInputStream());
	            String fileName =file.getOriginalFilename();
	            String contentType = file.getContentType();
	            Attachment attachment = new Attachment(fileName, contentType, content);
	            attachmentService.save(attachment);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	         
	        return "redirect:/index.html";
	    }
	    
	    @RequestMapping(value="/download")
	    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    	String attachmentId = request.getParameter("attachmentId");
	    	 //int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
	    	 
	    	  
	    	 
	    	         Attachment  attachment = this.attachmentService.getAttachment(new Long(attachmentId));
	    	 
	    	  
	    	 
	    	         response.setContentType(attachment.getContentType());
	    	 
	    	        // response.setContentType("BLOB");
	    	 
	    	         response.setHeader("Content-Disposition","attachment; filename=\"" + attachment.getFileName() +"\"");
	    	 
	    	  
	    	 
	    	         FileCopyUtils.copy(attachment.getContent().getBinaryStream(), response.getOutputStream());
	    	 
	    	  
	    	 
	    	         //return null;

	    }
	    
	    /*@RequestMapping(value="/",method = RequestMethod.GET)
	    public String displayMessage(Map<String, Object> map, Principal principal ){
	    	
	    	return "login";
	    }*/
	 
	   
	
}
