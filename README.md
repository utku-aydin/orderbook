# Orderbook
 An order book for traders. A CRUD web application with a Java + MySQL back end, and a React front end. Allows order addition and modification, and tracks the history of each order. Automatic matching is performed, and a ticker feed is available alongside a simple trade history graph.  
 
# Implementation Plan

**Prerequisites for this release:**

- You will need WinRAR or 7-Zip

- You will need a JRE of at least Java 11.0

- You will need Node.js installed (how to below) [https://phoenixnap.com/kb/install-node-js-npm-on-windows](https://phoenixnap.com/kb/install-node-js-npm-on-windows)

- MySQL or similar database management system

- Any available browser (The application was tested on Google Chrome)

**Environment Variables:**

- SERVER\_SERVICE\_URL
- SERVER\_HOSTNAME
- SERVER\_PORT
- SERVER\_USERNAME
- SERVER\_PASSWORD

[**https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/**](https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/) - To Add Environement Variables

## **Instructions to install 7-zip**

1. Go to 7-zip home page at: https://www.7-zip.org/

2. You will see two rectangles with blue hyperlinks. Download the latest stable version of 7zip which matches your version of Windows. The text above the rectangles highlights which one is the latest stable version.

- To check version of windows:

1. Open &quot;This PC&quot;

2. Right-click on empty space on the screen and click Properties

3. A window will pop up and one of lines should read &quot;System Type&quot;

4. If it says 32-bit, download the 32-bit version of 7-zip. Otherwise,

download the 64-bit version.

- Direct links to .exe files

32-Bit: https://www.7-zip.org/a/7z1900.exe

64-Bit: https://www.7-zip.org/a/7z1900-x64.exe

3. After downloading the correct .exe file, double-click on it

4. Save it in a directory on your machine and follow the install wizard.

5. At the end, click finish and 7zip should be on your system.

## **Instructions to verify JRE**

1. Open the command prompt. Follow the menu path: Start \&gt; Programs \&gt; Accessories \&gt; Command Prompt

2. Type: java -version and press Enter on your keyboard

3. Make sure the output looks something like this:

java version &quot;11.0.2&quot; 2019-01-15

Java(TM) SE Runtime Environment (build 11.0.2+9)

Java HotSpot(TM) 64-Bit Server VM (build 11.0.2+9, mixed mode, sharing)

- Your java version should be at least 11.0

If you don&#39;t have the correct Java version installed in your machine, please follow the instructions from this web page: https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK\_HowTo.html

They can be found at section 1. How to Install JDK on Windows.

Install Tomcat on Linux: [https://www.mulesoft.com/tcat/tomcat-linux](https://www.mulesoft.com/tcat/tomcat-linux) (Alternative).

# **Instructions to run the web application using XAMMP as database management tool**

1.Install XAMPP with the following guide:[https://www.wikihow.com/Install-XAMPP-for-Windows](https://www.wikihow.com/Install-XAMPP-for-Windows).

2.Ensure XAMPP is running (the guide will show you how to run it).

3.Click the start buttons to the right of Apache and MySQL to start them (shown below).

![](RackMultipart20210912-4-1ivmykj_html_c1d816012a1d5353.png)

4.Click on this link then click the either the icon or link that pops up:https://drive.google.com/file/d/1LywTSyA62YUF5oHejpvsi\_XcHYHU32kh/view?usp=sharing

5.Click the download button on the top right (shown below).

![](RackMultipart20210912-4-1ivmykj_html_8fe4cb302928d3f0.png)

6.Click on download anyway (show below).

7.Go to the directory to which the file (orderbook\_release\_go.zip) was downloaded to – your &#39;Downloads&#39; folder is the default.

a.Right click the file and click cut.

b.Go to your documents.

c.Right-click in the folder and select &quot;New Folder&quot; – call it &quot;Orderbook\_Releases&quot;. Double click on it to enter it.

d.Right click in the folder and click paste.

8.Right click on the pasted zip file, and click extract all.

9.On the pop-up menu, ensure the box to the left of &#39;Show extracted files when complete&#39; is unchecked – if it is checked, click it to uncheck it.

a.It is unchecked if there is no tick in it.

10.Click the &#39;Extract&#39; button near the bottom right of the menu that has popped up, leaving everything else at the default.

11.Delete the zip file once extraction is complete, by right clicking on it and clicking delete.

a.Click on the search icon near the bottom left of your Windows taskbar.

b.Type &#39;recycle bin&#39; and press enter.

c.Find and right click &#39;orderbook\_release\_go.zip&#39;, then click delete in the pop-up menu.

12.Double click on the &#39;orderbook\_release\_go&#39; folder that has been extracted.

13.Go back to XAMMP.

a.Click on the &quot;Admin&quot; button to the right of MySQL.

b.A page should open on your default browser. Once it loads, click on the &quot;Import&quot; button (shown below)

c.Click the &quot;Choose File&quot; button.

d.On the pop-up window, go to your document -\&gt; Orderbook\_Releases -\&gt; orderbook\_release\_go -\&gt; SQL

e.Double click &quot;orderbook-schema.sql&quot;

f.Go to the bottom of the page (scroll down if necessary) and click on the &quot;Go&quot; button.

g.Wait for the execution to complete. Once it has completed, you should see this near the top of the page:

h.Now go back to your &quot;orderbook\_release\_go&quot; folder.

Repeat b to h for order-data.sql

14. Go to the config folder and open the file &#39;application.properties&#39; with a text editor

15. Inside the file, change the &#39;localhost:3306&#39; to the url and port for your database; adjust the username and password accordingly

16. Go back to the &#39;orderbook\_release\_go&#39; folder and go to Front\_End -\&gt; order-book then open &quot;.env&quot; with your preferred text editor and replace &quot;REACT\_APP\_SERVICE\_URL=[http://localhost:8080/api](http://localhost:8080/api)&quot; with &quot;REACT\_APP\_SERVICE\_URL=YOUR\_URL&quot;, substituting YOUR\_URL with your preferred url.

17.Go back to the &#39;orderbook\_release\_go&#39; folder and run linux\_run.sh and this should load both the backend and frontend. The script would download all required dependencies, which would take around 2-3 minutes. The frontend would be accessible from {YOUR\_URL}.

18.If you don&#39;t have Google Chrome, install it here with this guide:[https://support.google.com/chrome/answer/95346?co=GENIE.Platform%3DDesktop&amp;hl=en&amp;oco=0](https://support.google.com/chrome/answer/95346?co=GENIE.Platform%3DDesktop&amp;hl=en&amp;oco=0)

19.Start Google Chrome

20.Type{YOUR\_URL} (or copy and paste it) into the chrome search bar, and press enter. This should lead you to the home page of the site.

21.You may now reference the Release Plan included as an attachment to the same email this Implementation Plan is included in, to find out about the features of this application and how to use them.

# Release Notes (27.08.2020)

### Order Book - v1.0

**Overview**

The purpose of this web application is to manage stock trading. The users of the system can create new buy/sell orders, update or cancel existing orders, commit trades, follow stock market trends using a ticker or observe changes in trade prices over time using a graph. Server data is kept in a MySQL database, which is dynamically updated as users interact with the web application.

**Functionality of Order Book v1.0**

- Create buy/sell orders
- Update/cancel existing orders
- Commit trades
- Stock ticker
- Graph with trades
- MySQL database connectivity

**Implementation Details**
  - Backend Tools
    - Java with Spring Boot used for backend server implementation
    - JUnit used for testing
    - MySQL database used for storing the data
    - JPA used for managing the relationship with the database
    - NetBeans was used as main IDE
  - Frontend Tools
    - React with JavaScript
    - VSCode was used as main IDE
  - Version Control
    - GitHub desktop

Further details regarding implementing and running the web application would be available in the Implementation Plan file.

**Supporting Files**
  - Implementation Plan: Specifies how to install the application and run it
  - Rollback Plan: Specifies how to roll back to a previous stable release in case of delivery failure
  - Change log: Has information regarding new additions to each release. Not available for Order Book v1.0 (current release) because it is the first release.
  - Wireframes: Act as a map for the web page
  - Entity Relationship Diagram: Visual representation of the structure of the MySQL database
  - Class Diagram: Visual representation of the relationships between classes in the backend
  - Flow Chart: Visual representation of the flow of operations of the web application
