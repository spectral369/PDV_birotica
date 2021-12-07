PDV_BIROTICA
==============

Basic romanian paper-work digitalization.

THIS IS INTENTED TO BE USED AS A TRANSITION!!!!

Feel free to add and improve but not sell

![Image](https://imgur.com/bpY6ozf.png)

Info on setup
	
	Requires openjdk 17
	Set in tomcat/conf/server.xml after "<Host name="localhost" .... " path to static content : <Context docBase="ROOT/WEB-INF/classes/META-INF/resources/pdfs"  path="/pdfs" /> 
	Run mvn for production to build war file:  package -Pproduction


