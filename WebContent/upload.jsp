<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*, form.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TV Series</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
<script src="jquery.js"></script>
</head>
<body>
<header>
      <h1>Media Vault</h1>
      <img src="pictures/logo.png" alt="a simple logo">

      <nav>
        <ul>
          <li><a href="homePage.html">Welcome Page</a></li>
          <li><a href="tvShow.jsp">TV series</a></li>
          <li><a href="movie.jsp">Movies</a></li>
          <li><span>Upload</span></li>
        </ul>
      </nav>
    </header>
      
  <main>
    <form action="UploadServlet" method="POST" class="smart-green" enctype="multipart/form-data" onsubmit="return checkInput()">
    <h2 class="upload">Upload Form
        <span>Please fill all the texts in the fields.</span>
        </h2>
    <label>
        <span>Media Thumbnail(ONLY JPEG IS AVILIABLE) :</span>
        <input id="Thumbnail" type="file" name="file_thumbnail" accept="image/jpeg,image/gif,image/png"/>
    </label>
    <label>
    	<span>File(ONLY MP4 IS AVILIABLE):</span>
    	<input id="upload_file" type="file" name="file_media" accept="audio/mp4, video/mp4"/>
    </label>
    <label>
    <span>Media Type :</span><select autocomplete="off" id="selection_type" name="selection_type" >
    <option value="None" selected="selected">None</option>
    <option value="TV Series">TV Series</option>
    <option value="Movies">Movies</option>
    </select>
    </label>
    <div id="type"></div>
    <label>
    <span>&nbsp;</span>
    <input name="upload" type="submit" class="button_1"  id="submit-button" value="Upload">
    </label>
    </form>
  </main>
     
  <footer>

    <p>Designed by Group Kepler</p>
      
    </footer>

      
</body>
<script>
	$(function(){
		$("#selection_type").change(selectMediaType);
		$("#selection_tv_name").change(selectTvName);
		$("#submit-button").click(checkInput);
	});
	
	function selectTvName(){
		var item = $("#selection_tv_name").val();
		if(item == "Other"){
			$("#tv_name").html("<label><textarea id='other' class='intro' name='other' placeholder='name of the TV Series'></textarea></label>");
		}else{
			$("#tv_name").empty();
		}
	}
	
	 function checkInput(){
		var item = $("#selection_type").val();
		if(item == "Movies"){
			var thumbnail = document.getElementById("Thumbnail").value;
			var uploadFile = document.getElementById("upload_file").value;
			var movieName = document.getElementById("MovieName").value;
			var movieType = $("#selection_movie_type").val();
			var length = document.getElementById("length").value;
			var plot = document.getElementById("movie_intro").value;
			
			if(thumbnail == "" || thumbnail == null || uploadFile == "" || uploadFile == null || movieName.trim() == "" || movieName == null || movieType == "None" || length.trim() == "" || length == null || plot.trim() == "" || plot == null)
			{
				alert("Submission failed!!\nERROR: Please complete the form!");
				return false;
			}
			else{
				alert("Submission success!");
				return true;
			}
		}else if(item == "TV Series"){
			var thumbnail = document.getElementById("Thumbnail").value;
			var uploadFile = document.getElementById("upload_file").value;
			var tvShowName = $("#selection_tv_show_name").val();
			var season = $("#selection_season").val();
			var episode = document.getElementById("episode_number").value;
			var episodeName = document.getElementById("episode_name").value;
			var episodePlot = document.getElementById("episode_intro").value;
			var tvShowType = $("#selection_tv_show_type").val();
			var length = document.getElementById("length").value;
			/* var tvNameOther = document.getElementById("tv_name_other").value;
			var tvShowIntro = document.getElementById("tv_show_intro").value;
			var typicalLength = document.getElementById("typical_length").value; */
			var seasonOther = document.getElementById("season_other").value;
			var flag = true;
			
			if(seasonOther.trim() == "" || seasonOther == null || thumbnail == "" || thumbnail == null || uploadFile == "" || uploadFile == null || episodePlot.trim() == "" || episodePlot ==null || length.trim() == "" || length == null || tvShowType == "None" || episode.trim() == "" || episode == null || episodeName.trim() == "" || episodeName == null)
			{
				
				flag = false;
				alert("Submission failed!!\nERROR: Please complete the form!");
				return flag;
			}
			/* else{
				alert("Submission success!");
				return true;
			} */
			
		 	if(tvShowName == "None"){
				alert("Submission failed!!\nERROR: Please complete the form!");
				flag = false;
				return flag;
			}else if(tvShowName == "Other"){
				var tvShowThumbnail = document.getElementById("tv_show_thumbnail").value;
				var tvNameOther = document.getElementById("tv_name_other").value;
				var tvShowIntro = document.getElementById("tv_show_intro").value;
				var typicalLength = document.getElementById("typical_length").value;
				if(tvShowThumbnail == "" || tvShowThumbnail == null || tvNameOther.trim() == "" || tvNameOther == null || tvShowIntro.trim() == "" || tvShowIntro == null || typicalLength.trim() == "" || typicalLength == null){
					alert("Submission failed!!\nERROR: Please complete the form!");
					flag = false;
					return flag;
				}
			}
			
			/* if(season == "None"){
				alert("Submission failed!!\nERROR: Please complete the form!");
				flag = false;
				return flag;
			}else if(season == "Other"){
				var seasonOther = document.getElementById("season_other").value;
				if(seasonOther.trim() == "" || seasonOther == null){
					alert("Submission failed!!\nERROR: Please complete the form!");
					flag = false;
					return flag;
				}
			} */
			
			if(flag){
				alert("Submission success!");
				return true;
			}
		}else{
			alert("Please Choose Media Type!");
			return false;
		} 
		
	} 
	
	function selectMediaType(){
		
		<%! String rootFolder = "/Users/contana/Documents/workspaceEE/Assignment2.2/WebContent/tvshow/tvshownfo"; %>
			  
			  
		
			  var tvShowName = "<label>"+
			  "<span>TV Show Name: </span>"+
			  "<select autocomplete='off' id='selection_tv_show_name' name='selection_tv_show_name'>"+
			  "<option value='None' selected='selected'>None</option>"+
			  
     		  <% File dir_1 = new File(rootFolder); %>
     		  <% File[] files_1 = dir_1.listFiles(); %>
     		  <% if(files_1 != null){ %>
     		  <% for(int i = 0;i<files_1.length;i++){ String fileName_1 = files_1[i].getName(); %>
     		 		<% if(files_1[i].isDirectory()){ %>
     		 			"<option value='<%=fileName_1 %>'><%=fileName_1 %></option>" +
     		 		<%}%>
     		  <%}%>
     		  <%}%>
			  "<option value='Other'>Other</option>"+
			  "</select>" + 
			  "<div id='tv_name'></div>";
			  
			  /* "</label>" +
			  "<label>" +
			  "<input id='tv_name_other' class='MediaName' name='tv_name_other' placeholder='name of the TV Series'>" +
			  "</label>" +
			  "<label>" +
			  "<span>Plot :</span>" +
			  "<textarea id='tv_show_intro' class='intro' name='tv_show_intro' placeholder='Introduce the TV Series briefly'></textarea>" +
			  "</label>" +
			  "<label>" +
			  "<span>Typical Length: </span>" +
			  "<input id='typical_length' name='typical_length' class='MediaName' placeholder='typical length of the TV Shows'>" +
			  "</label>"; */
			  

			  
			  var movieName = "<label>" +
      		  "<span>Movie Name: </span>" +
      		  "<input id='MovieName' name='movie_name' type='text' class='MediaName'><br>" +
     		  "</label>";
		
			  var typeOptions = 
		      "<option value='None' selected='selected'>None</option>" +
			  "<option value='Comedy'>Comedy</option>"+
    		  "<option value='Adventure'>Adventure</option>"+
    		  "<option value='Fantasy'>Fantasy</option>"+
    		  "<option value='Mystery'>Mystery</option>"+
    		  "<option value='Thriller'>Thriller</option>"+
    		  "<option value='Documentary'>Documentary</option>"+
    		  "<option value='War'>War</option>"+
    		  "<option value='Western'>Western</option>"+
    		  "<option value='Romance'>Romance</option>"+
    		  "<option value='Drama'>Drama</option>"+
    		  "<option value='Horror'>Horror</option>"+
    		  "<option value='Action'>Action</option>"+
    		  "<option value='Sci-Fi'>Sci-Fi</option>"+
    		  "<option value='Music'>Music</option>"+
    		  "<option value='Crime'>Crime</option>";
    		  
    		  var tvShowType = "<label>" +
    		  "<span>TV Show Type :</span>"+
    		  "<select id='selection_tv_show_type' name='selection_tv_show_type'>"+
    		  typeOptions +
    		  "</select>" +
    		  "</label>";
    		  
    		  var movieType = "<label>"+
    		  "<span>Movie Type :</span>"+
    		  "<select id='selection_movie_type' name='selection_movie_type'>"+
    		  typeOptions +
    		  "</select>" +
    		  "</label>";
    		  
    		  var moviePlot = "<label>" +
      		  "<span>Plot :</span>" +
      		  "<textarea id='movie_intro' class='intro' name='movie_intro' placeholder='Introduce the movie briefly'></textarea>" +
      		  "</label>";
    		  
    		  var length = "<label>" +
    		  "<span>Length: </span>" +
    		  "<input id='length' name='media_length' class='MediaName' placeholder='length of the media'>" +
    		  "</label>";
    		  
    		 
    		  
    		  var episode = "<label>" +
			  "<span>Episode: </span>" +
			  "<div id='episode_have'></div>" +
			  "<label>" +
			  "<input id='episode_number' class='MediaName' name='episode_number' placeholder='number of the episode'>" + 
			  "</label>" +
			  "<label>" +
			  "<span>Episode Name: </span>" +
			  "<input id='episode_name' class='MediaName' name='episode_name' placeholder='name of the episode'>" +
			  "</label>" +
			  "<label>" +
      		  "<span> Episode Plot :</span>" +
      		  "<textarea id='episode_intro' class='intro' name='episode_intro' placeholder='Introduce the episode briefly'></textarea>" +
      		  "</label>";
      		  
      		  var season = "<label>" +
			  "<span>Season: </span>" +
			  /* "<select autocomplete='off' id='selection_season' name='selection_season'>" +
			  "<option value='None' selected='selected'>None</option>" +
			  "<div id='season_option'></div>" +
			  "<option value='Other'>Other</option>" +
			  "</select>" + */
			  "</label>" +
			  "<label> "+
			  "<input id='season_other' class='MediaName' name='season_other' placeholder='number of the season'>" +
			  "</label>";
			  
			  //"<div id='season_number'></div>";
			  
			   var tvShowScript = "<script>" +
				  "$(function(){" +
			   	  "$('#selection_tv_show_name').change(selectTvName);" +
			   	  //"$('#selection_season').change(selectSeason);" +
	              "});" +
		          "function selectTvName(){" +
			      "var item = $('#selection_tv_show_name').val();" +
			      "if(item == 'Other'){" +
				  "$('#tv_name').html(\"<label><span>TV Show Thumbnail(ONLY JPEG IS AVILIABLE) :</span><input id='tv_show_thumbnail' type='file' name='file_tv_show_thumbnail' accept='image/jpeg,image/gif,image/png'/></label><label><input id='tv_name_other' class='MediaName' name='tv_name_other' placeholder='name of the TV Series'></label><label><span>TV Show Type :</span><select id='selection_tv_show_type' name='selection_tv_show_type'><option value='None' selected='selected'>None</option><option value='Comedy'>Comedy</option><option value='Adventure'>Adventure</option><option value='Fantasy'>Fantasy</option><option value='Mystery'>Mystery</option><option value='Thriller'>Thriller</option><option value='Documentary'>Documentary</option><option value='War'>War</option><option value='Western'>Western</option><option value='Romance'>Romance</option><option value='Drama'>Drama</option><option value='Horror'>Horror</option><option value='Action'>Action</option><option value='Sci-Fi'>Sci-Fi</option><option value='Music'>Music</option><option value='Crime'>Crime</option></select></label><label><span>Plot :</span><textarea id='tv_show_intro' class='intro' name='tv_show_intro' placeholder='Introduce the TV Series briefly'></textarea></label><label><span>Typical Length: </span><input id='typical_length' name='typical_length' class='MediaName' placeholder='typical length of the TV Shows'></label>\");" +
			      "}else{" +
				  "$('#tv_name').empty();" +
			      "}" +
		          "}" +
		          /* "function selectSeason(){" +
		          "var item = $('#selection_season').val();" +
		          "if(item == 'Other'){" +
		          "$('#season_number').html(\"<label><input id='season_other' class='MediaName' name='season_other' placeholder='number of the season'</label>\")" +
		          "}else{" +
		          "$('#season_number').empty();" +
		          "}" +
		          "}" + */
   	              "</script"+ ">"; 
			  
      		 
	      
	          var item = $("#selection_type").val();
	          if(item == "Movies"){
	              $("#type").html(
	            		  movieName +
	            		  movieType +
	            		  length +
	            		  moviePlot
	            		  );
	          }
	          else if(item == "TV Series"){
	        	  $("#type").html( 
	        			  tvShowName +
	        			  season +
	        			  episode +
	            		  length +
	            		  tvShowScript
	        	  )
	          }else{
	        	  $("#type").empty();
	          }
	}

</script>
</html>