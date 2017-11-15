/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

	private String rootFolder = "/Users/contana/Documents/workspaceEE/Assignment2.2/WebContent";
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

	
	/**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
        
        Tvshow tvshow=new Tvshow(null, null, null,  null, null, null, null, null, null);
 	   
        Movie theMovie=new Movie(null, null, null, null);
 	   
     	if(request.getParameter("selection_type").equals("Movies")){
     		
     		theMovie.setMovieLength(request.getParameter("media_length"));
     		theMovie.setMovieName(request.getParameter("movie_name"));
     		theMovie.setMovieType(request.getParameter("selection_movie_type"));
     		theMovie.setPlotSummary(request.getParameter("movie_intro"));
 			theMovie.toXML();
 			
 			//upload part
 			request.setCharacterEncoding("utf-8");
 	        for (Part part : request.getParts()) {
 	            if (part.getName().startsWith("file_media")) {
 	                String fileName = rootFolder + "/movie/" + theMovie.getmovieName() + ".mp4";
 	                movieWriteTo(fileName,part);
 	            }
 	            if (part.getName().startsWith("file_thumbnail")){
 	            	String fileName = rootFolder + "/movie/thumbnail/" + theMovie.getmovieName() + ".jpg";
 	            	movieWriteTo(fileName,part);
 	            }
 	        }
 			
 	        //generate middle page
 			response.setHeader("Refresh","5;url='homePage.html'");
 			PrintWriter movie_out = response.getWriter();
 	    	response.setContentType("text/html");
 	    	String title = "Movie Upload Success!";
 	        movie_out.println("<!DOCTYPE html>\n" +
 	                    "<html>\n" +
 	                    "<head><title>"+ title + "</title></head>\n" +
 	                    "<meta charset='utf-8'>" +
 	                    "<link rel='stylesheet' type='text/css' href='css/style.css'>" +
 	                    "<header>" +
 	                    "<h1 style='color: green'>" + title + "</h1>" +
 	                    "<img src='pictures/logo.png' alt='a simple logo'>" +
 	                    "</header>" +
 	                    "<main>" +
 	                    "<table>" +
 	                    	"<thead>" +
 	                    		"<tr>" +
 	                    			"<th id='Item'>Item</th>" +
 	                    			"<th id='Value'>Value</th>" +
 	                    		"</tr>" +
 	                    	"</thead>" +
 	                    	"<tbody>" +
 	                    		"<tr>" +
 	                    			"<td>" + "Name" + "</td>" +
 	                    			"<td>" + theMovie.getmovieName() + "</td>" +
 	                    		"</tr>" +
 	                    		"<tr>" +
                     				"<td>" + "Type" + "</td>" +
                     				"<td>" + theMovie.getmovieType() + "</td>" +
                     			"</tr>" +
                     			"<tr>" +
                 					"<td>" + "Length" + "</td>" +
                 					"<td>" + theMovie.getmovielength() + "</td>" +
                 				"</tr>" +
                 				"<tr>" +
             						"<td>" + "Plot" + "</td>" +
             						"<td>" + theMovie.getplotSummary() + "</td>" +
             					"</tr>" +
 	                    	"</tbody>" +
 	                    "</table>" +
 	                    "</main>" +
 	                    "<footer>" +
 	                    "<p>" +
 	                    "5 seconds to go to Home Page!!!!!!" +
 	                    "</p>" +
 	                    "</footer>" +
 	                    "</html>");
     	}
     	
     	else if(request.getParameter("selection_type").equals("TV Series")){
     		if(request.getParameter("selection_tv_show_name").equals("Other")){
     			tvshow.setShowName(request.getParameter("tv_name_other"));
         		tvshow.setShowSummary(request.getParameter("tv_show_intro"));
         		tvshow.setTypicalLength(request.getParameter("typical_length"));
         		if(Integer.parseInt(request.getParameter("season_other")) < 10){
         			tvshow.setseasonNumber("0" + request.getParameter("season_other"));
         		}else{
         			tvshow.setseasonNumber(request.getParameter("season_other"));
         		}
         		if(Integer.parseInt(request.getParameter("episode_number")) < 10){
         			tvshow.setepisodeNumber("0" + request.getParameter("episode_number"));
         		}else{
         			tvshow.setepisodeNumber(request.getParameter("episode_number"));
         		}
         		tvshow.setepisodeName(request.getParameter("episode_name"));
         		tvshow.setPlotSummary(request.getParameter("episode_intro"));
         		tvshow.settype(request.getParameter("selection_tv_show_type"));
         		tvshow.setepisodeLength(request.getParameter("media_length"));
         		tvshow.tvShowToXML();
         		tvshow.episodeToXML();
         		
         		//upload part
         		request.setCharacterEncoding("utf-8");
     	        for (Part part : request.getParts()) {
     	            if (part.getName().startsWith("file_media")) {
     	            	String tvShowNamePath = rootFolder + "/tvshow/" + tvshow.getShowName();
     	            	String seasonNum = tvshow.getseasonNumber();
     	            	String episodeName = "s" + tvshow.getseasonNumber() + "e" + tvshow.getepisodeNumber() +
     	            			" - " + tvshow.getepisodeName() + ".mp4";
     	            	tvShowWriteTo(tvShowNamePath, seasonNum, episodeName, part);
     	            }
     	            if (part.getName().startsWith("file_thumbnail")){
     	            	String tvShowNamePath = rootFolder + "/tvshow/thumbnail/" + tvshow.getShowName();
     	            	String seasonNum = tvshow.getseasonNumber();
     	            	String episodeName = "s" + tvshow.getseasonNumber() + "e" + tvshow.getepisodeNumber() +
     	            			" - " + tvshow.getepisodeName() + ".jpg";
     	            	tvShowWriteTo(tvShowNamePath, seasonNum, episodeName, part);
     	            }
     	            if (part.getName().startsWith("file_tv_show_thumbnail")){
     	            	String tvShowNamePath = rootFolder + "/tvshow/thumbnail/" + tvshow.getShowName() + "/" + tvshow.getShowName() + ".jpg";
     	            	tvShowWriteTo(tvShowNamePath, null, null, part);
     	            }
     	        }
     		}else{
     			tvshow.setShowName(request.getParameter("selection_tv_show_name"));
     			if(Integer.parseInt(request.getParameter("season_other")) < 10){
         			tvshow.setseasonNumber("0" + request.getParameter("season_other"));
         		}else{
         			tvshow.setseasonNumber(request.getParameter("season_other"));
         		}
         		if(Integer.parseInt(request.getParameter("episode_number")) < 10){
         			tvshow.setepisodeNumber("0" + request.getParameter("episode_number"));
         		}else{
         			tvshow.setepisodeNumber(request.getParameter("episode_number"));
         		}
         		tvshow.setepisodeName(request.getParameter("episode_name"));
         		tvshow.setPlotSummary(request.getParameter("episode_intro"));
         		tvshow.setepisodeLength(request.getParameter("media_length"));
         		tvshow.episodeToXML();
         		
         		//upload part
         		request.setCharacterEncoding("utf-8");
     	        for (Part part : request.getParts()) {
     	            if (part.getName().startsWith("file_media")) {
     	            	String tvShowNamePath = rootFolder + "/tvshow/" + tvshow.getShowName();
     	            	String seasonNum = tvshow.getseasonNumber();
     	            	String episodeName = "s" + tvshow.getseasonNumber() + "e" + tvshow.getepisodeNumber() +
     	            			" - " + tvshow.getepisodeName() + ".mp4";
     	            	tvShowWriteTo(tvShowNamePath, seasonNum, episodeName, part);
     	            }
     	            if (part.getName().startsWith("file_thumbnail")){
     	            	String tvShowNamePath = rootFolder + "/tvshow/thumbnail/" + tvshow.getShowName();
     	            	String seasonNum = tvshow.getseasonNumber();
     	            	String episodeName = "s" + tvshow.getseasonNumber() + "e" + tvshow.getepisodeNumber() +
     	            			" - " + tvshow.getepisodeName() + ".jpg";
     	            	tvShowWriteTo(tvShowNamePath, seasonNum, episodeName, part);
     	            }
     	        }
     		}
     		
     		
 	        //generate middle page
     		response.setHeader("Refresh","5;url='homePage.html'");
     		PrintWriter tvShow_out = response.getWriter();
 	    	response.setContentType("text/html");
 	    	String title = "TV Show Upload Success!";
 	        tvShow_out.println("<!DOCTYPE html>\n" +
 	                    "<html>\n" +
 	                    "<head><title>"+ title + "</title></head>\n" +
 	                    "<meta charset='utf-8'>" +
 	                    "<link rel='stylesheet' type='text/css' href='css/style.css'>" +
 	                    "<header>" +
 	                    "<h1 style='color: green'>" + title + "</h1>" +
 	                    "<img src='pictures/logo.png' alt='a simple logo'>" +
 	                    "</header>" +
 	                    "<main>" +
 	                    "<table>" +
 	                    	"<thead>" +
 	                    		"<tr>" +
 	                    			"<th id='Item'>Item</th>" +
 	                    			"<th id='Value'>Value</th>" +
 	                    		"</tr>" +
 	                    	"</thead>" +
 	                    	"<tbody>" +
 	                    		"<tr>" +
 	                    			"<td>" + "Name" + "</td>" +
 	                    			"<td>" + tvshow.getShowName() + "</td>" +
 	                    		"</tr>" +
 	                    		"<tr>" +
                     				"<td>" + "Type" + "</td>" +
                     				"<td>" + tvshow.gettype() + "</td>" +
                     			"</tr>" +
                     			"<tr>" +
                 					"<td>" + "Typical Length" + "</td>" +
                 					"<td>" + tvshow.getTypicalLength() + "</td>" +
                 				"</tr>" +
                 				"<tr>" +
             						"<td>" + "Plot" + "</td>" +
             						"<td>" + tvshow.getShowSummary() + "</td>" +
             					"</tr>" +
             					"<tr>" +
         							"<td>" + "Season" + "</td>" +
         							"<td>" + tvshow.getseasonNumber() + "</td>" +
         						"</tr>" +
         						"<tr>" +
         							"<td>" + "Episode" + "</td>" +
         							"<td>" + tvshow.getepisodeNumber() + "</td>" +
         						"</tr>" +
         						"<tr>" +
     								"<td>" + "Episode Name" + "</td>" +
     								"<td>" + tvshow.getepisodeName() + "</td>" +
     							"</tr>" +
         						"<tr>" +
         							"<td>" + "Episode plot" + "</td>" +
         							"<td>" + tvshow.getplotSummary() + "</td>" +
         						"</tr>" +
         						"<tr>" +
         							"<td>" + "Episode length" + "</td>" +
         							"<td>" + tvshow.getepisodeLength() + "</td>" +
         						"</tr>" +
 	                    	"</tbody>" +
 	                    "</table>" +
 	                    "</main>" +
 	                    "<footer>" +
 	                    "<p>" +
 	                    "5 seconds to go to Home Page!!!!!!" +
 	                    "</p>" +
 	                    "</footer>" +
 	                    "</html>");
     	}
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        doGet(request, response);
    }
    
    private void movieWriteTo(String fileName, Part part) throws IOException, FileNotFoundException{
		try{
 			 File file = new File(fileName);
 			 if(!file.exists()){
 	                file.createNewFile();
 			 }
 			InputStream in = part.getInputStream();
 	        OutputStream out = new FileOutputStream(fileName);
 	        byte[] buffer = new byte[1024];
 	        int length = -1;
 	        while ((length = in.read(buffer)) != -1) {
 	            out.write(buffer, 0, length);
 	        }

 	        in.close();
 	        out.close();
 		 }catch(Exception e){
 			 e.printStackTrace();
 		 }
		
	}
	
	private void tvShowWriteTo(String tvShowNamePath, String seasonNum, String episodeName, Part part) throws IOException, FileNotFoundException{
		try{
			
			 if(seasonNum != null && episodeName != null)
			 {
				 String path = tvShowNamePath;
				 File dir1 = new File(path);
				 if(!dir1.exists()){
		                dir1.mkdirs();
				 }
				 path = path + "/Season_" + seasonNum;
				 File dir2 = new File(path);
				 if(!dir2.exists()) {  
			           dir2.mkdirs();
			     }  
				 path = path + "/" + episodeName;
				 String fileName = path;
				 File file = new File(fileName);
	 			 if(!file.exists()){
	 	                file.createNewFile();
	 			 }
	 			InputStream in = part.getInputStream();
	 	        OutputStream out = new FileOutputStream(fileName);
	 	        byte[] buffer = new byte[1024];
	 	        int length = -1;
	 	        while ((length = in.read(buffer)) != -1) {
	 	            out.write(buffer, 0, length);
	 	        }
	
	 	        in.close();
	 	        out.close();
			 }else{
				 File file = new File(tvShowNamePath);
	 			 if(!file.exists()){
	 	                file.createNewFile();
	 			 }
	 			InputStream in = part.getInputStream();
	 	        OutputStream out = new FileOutputStream(tvShowNamePath);
	 	        byte[] buffer = new byte[1024];
	 	        int length = -1;
	 	        while ((length = in.read(buffer)) != -1) {
	 	            out.write(buffer, 0, length);
	 	        }

	 	        in.close();
	 	        out.close();
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}