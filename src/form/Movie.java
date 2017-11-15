package form;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Movie {
	private String movieName;
	private String movieType;
	private String plotSummary;
	private String movieLength;
	//the absolute path of the nfo folder 
	private String path = "/Users/contana/Documents/workspaceEE/Assignment2.2/WebContent/movie/nfo/";
	

  public Movie(String movieName,String movieType,
		       String plotSummary,String movieLength){
	  this.movieLength=movieLength;
	  this.movieName=movieName;
	  this.plotSummary=plotSummary;
	  this.movieType=movieType;
	  
  }
public void setMovieName(String movieName){
	   this.movieName=movieName;
   }
 public void setMovieType(String movieType){
	  this.movieType=movieType;
  }
   public void setPlotSummary(String plotSummary){
	  this.plotSummary=plotSummary;
  }
   public void setMovieLength(String movielength){
	 this.movieLength=movielength;
 }
  public String getmovieName(){
	  return movieName;
 }
  public String getmovieType(){
	  return movieType;
  }
  public String getplotSummary(){
    return plotSummary;
  }
  public String getmovielength(){
	  return movieLength;
  }
  
  public void toXML(){
	  
	  
		 
		 String toxml = null;
		 toxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n" +
				 "<movie>\n" +
				    "<thumb>"+ "movie/thumbnail/" + this.getmovieName() + ".jpg" + "</thumb>\n" +
				 	"<title>" + this.getmovieName() + "</title>\n" +
				 		"<plot>" + this.getplotSummary() + "</plot>\n" +
				 	"<type>" + this.getmovieType() + "</type>\n" +
				 	"<duration>" + this.getmovielength() + "</duration>\n" +
				 "</movie>\n";
		 
		 
		 
		 
		 String filenameTemp = path + this.getmovieName() + ".nfo";
		 
		 try{
			 File file = new File(filenameTemp);
			 if(!file.exists()){
	                file.createNewFile();
			 }
			 PrintStream ps = new PrintStream(new FileOutputStream(file));
			 ps.println(toxml);
			 ps.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
}
	 
@Override
public String toString() {
	return "Movie [movieName=" + movieName + ", movieType=" + movieType
			+ ", plotSummary=" + plotSummary + ", movielength=" + movieLength
			+ "]";
}
}
