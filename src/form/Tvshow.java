package form;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class Tvshow {
	private String showName;
	private String showSummary;
	private String typicalLength;
	private String  type;
	private String episodeName;
	private String plotSummary;
	private String episodeLength;
   private  String seasonNumber;
   private String episodeNumber;
   
   //the absolute path of the nfo folder
   private String path = "/Users/contana/Documents/workspaceEE/Assignment2.2/WebContent/tvshow/tvshownfo/";
   
	public Tvshow(String showName,String showSummary,String typicalLength,
			     String  type,String episodeName,String plotSummary,
			     String episodeLength,String  seasonNumber, String episodeNumber) {
		    this.showName=showName;
		    this.showSummary=showSummary;
		    this.typicalLength=typicalLength;
		    this.type=type;
		    this.episodeName=episodeName;
		    this.plotSummary=plotSummary;
		    this.episodeLength=episodeLength;
		    this.seasonNumber=seasonNumber;
		    this.episodeNumber=episodeNumber;
	}
    public void setShowName( String showName){
         this.showName=showName;
    }
    public void setShowSummary(String showSummary){
    	 this.showSummary=showSummary;
    }
    public void setTypicalLength(String typicalLength) {
    	 this.typicalLength=typicalLength;
	}
    public void settype(String type){
	    this.type=type;
    }
   public void setPlotSummary(String  plotSummary){
	   this.plotSummary=plotSummary;
   }
   public void setepisodeLength(String episodeLength){
	   this.episodeLength=episodeLength;
   }
   public void setseasonNumber(String seasonNumber){
	   this.seasonNumber=seasonNumber;
   }
   public void setepisodeNumber(String episodeNumber){
		  this.episodeNumber=episodeNumber;
	  }
   public void setepisodeName(String episodeName){
	   this.episodeName=episodeName;
   }
    public String getShowName(){
    	return showName;
    }
    public String getShowSummary(){
    	return showSummary;
    }
    public String getTypicalLength(){
    	return typicalLength;
    }
    public String gettype(){
 	    return type;
    }
    public String getepisodeName(){
    	return episodeName;
    }
    public String   getplotSummary(){
    	return plotSummary;
    }
     public String getepisodeLength(){
    	 return episodeLength;
     }
    public String getseasonNumber(){
    	return seasonNumber;
    }
    public String getepisodeNumber(){
    	return episodeNumber;
    }
    
    public void episodeToXML(){
    	String episodeToxml = null;
    	
    	episodeToxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n" +
				 "<episodedetails>\n" +
				 	"<thumb>" + "tvshow/thumbnail/" + this.getShowName() + "/" + "Season_" + this.getseasonNumber() + 
				 		"/" + "s" + this.getseasonNumber() + "e" + this.getepisodeNumber() + 
				 		" - " + this.getepisodeName() + ".jpg" +"</thumb>\n" +
				 	"<title>" + "s" + this.getseasonNumber() + "e" + this.getepisodeNumber() + 
			 		" - " + this.getepisodeName() + "</title>\n" +
				 		"<season>" + this.getseasonNumber() + "</season>\n" +
				 		"<episode>" + this.getepisodeNumber() + "</episode>\n" +
				 		"<plot>" + this.getplotSummary() + "</plot>\n" +
				 	"<duration>" + this.getepisodeLength() + "</duration>\n" +
				 "</episodedetails>\n";
    	
    	String tvShow_path = path + this.getShowName();
    	
    	try{
			 File dir1 = new File(tvShow_path);
			 if(!dir1.exists()){
	                dir1.mkdirs();
			 }
			 
			 String episode_path = tvShow_path + "/Episodes";
			 File dir2 = new File(episode_path);
			 if(!dir2.exists()){
	                dir2.mkdirs();
			 }
			 String episode_path_1 = episode_path + "/" + this.getepisodeName() + ".nfo";
			 File file2 = new File(episode_path_1);
			 if(!file2.exists()) {   
		           file2.createNewFile();
		     }  
			 PrintStream ps2 = new PrintStream(new FileOutputStream(file2));
			 ps2.println(episodeToxml);
			 ps2.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    }
    
    public void tvShowToXML(){
		 
		 String tvShowToxml = null;
		 
		 
		 tvShowToxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n" +
				 "<tvshow>\n" +
				 	"<thumb>" + "tvshow/thumbnail/" + this.getShowName() + "/" + this.getShowName() + ".jpg" + "</thumb>\n" + 
				 	"<title>" + this.getShowName() + "</title>\n" +
				 		"<plot>" + this.getShowSummary()+ "</plot>\n" +
				 	"<runtime>" + this.getTypicalLength() + "</runtime>\n" +
				 	"<type>" + this.gettype() + "</type>\n" +
				 "</tvshow>\n";
		 
		 
		 //System.out.println(tvShowToxml);
		 
		 String tvShow_path = path + this.getShowName();
		 
		 try{
			 File dir1 = new File(tvShow_path);
			 if(!dir1.exists()){
	                dir1.mkdirs();
			 }
			 String tvShow_path_1 = tvShow_path + "/" + this.getShowName() + ".nfo";
			 File file1 = new File(tvShow_path_1);
			 if(!file1.exists()) {  
		           file1.createNewFile();
		     }  
			 PrintStream ps1 = new PrintStream(new FileOutputStream(file1));
			 ps1.println(tvShowToxml);
			 ps1.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	@Override
	public String toString() {
		return "Tvshow [showName=" + showName + ", showSummary=" + showSummary
				+ ", typicalLength=" + typicalLength + ", type=" + type
				+ ", episodeName=" + episodeName + ", plotSummary="
				+ plotSummary + ", episodeLength=" + episodeLength
				+ ", seasonNumber=" + seasonNumber + ", episodeNumber="
				+ episodeNumber + "]";
	}
}
   