<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.io.*, form.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movies</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script src="jquery.js"></script>
  <script src="jquery.tablesorter.js"></script>
</head>
<body>
<header>
      <h1>Media Vault</h1>
      <img src="pictures/logo.png" alt="a simple logo">
     
      
      <nav>
        <ul>
          <li><a href="homePage.html">Welcome Page</a></li>
          <li><a href="tvShow.jsp">TV series</a></li>
          <li><span>Movies</span></li>
          <li><a href="upload.jsp">Upload</a></li>
        </ul>
      </nav>
    </header>
    <main>
    <table id="td1">
    <thead>
      <tr>
      	<th id="Name">Name</th>
        <th id="Thumbnails">Tumbnails</th>
        <th id="Introduction">Brief Introduction</th>
        <th id="Type">Type</th>
        <th id="length">Length</th>
        <th></th>
        <th></th>
      </tr>
     </thead>
     <tbody>
     <%! String rootFolder = "/Users/contana/Documents/workspaceEE/Assignment2.2/WebContent/movie/nfo"; %>
     <% File dir = new File(rootFolder); %>
     <% File[] files = dir.listFiles(); %>
     <%! String path = null; %>
     <%! readNfo text = new readNfo(); %>
     <% if(files != null){ %>
     	<% for(int i = 0;i<files.length;i++){ String fileName = files[i].getName(); %>
     		<% if(fileName.endsWith("nfo")){ %>
     			<% path = rootFolder + "/" + fileName; %>
			     <tr>
			     <td><%=text.readAttribute("title", path) %></td>
			     <td><img src="<%=text.readAttribute("thumb", path) %>" alt="<%=text.readAttribute("title", path) %>" width=180px height=240px></td>
			     <td><%=text.readAttribute("plot", path) %></td>
			     <td><%=text.readAttribute("type", path) %></td>
			     <td><%=text.readAttribute("duration", path) %></td>
			     <td><a href="movie/<%=text.readAttribute("title", path) %>.mp4"><button class="button_1" style="vertical-align:middle"><span>Watch</span></button></a></td>
			     <td><a href="movie/<%=text.readAttribute("title", path) %>.mp4"download="" ><button class="button_1" style="vertical-align:middle"><span>Download</span></button></a></td>
			     </tr>
     		<%} %>
     	<%} %>
     <%} %>
     </tbody>
     </table>
    </main>
    <footer>

    <p>Designed by Group Kepler</p>
      
    </footer>
</body>
<script type="text/javascript">
		$("#td1").tablesorter({headers:{1:{sorter:false},2:{sorter:false},5:{sorter:false},6:{sorter:false}}});
		
		
	</script>
</html>