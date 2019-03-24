
<p><strong>DSA LAB-II MINI-PROJECT</strong></p>

<p><strong>NETWORK BROADCASTING</strong></p>



<ul><li><strong>Year: Second Year</strong></li></ul>
<li><strong>Branch: Computer</strong></li>
<li><strong>Division: A</strong></li>
<li><strong>Batch: A3</strong></li>
<li><strong>Data Structure Implemented: Graph (Bidirectional and Unidirectional)            using an adjacency matrix representation</strong></li></ul>
<p>A network can be modeled by a graph</p>
<p> - Servers are represented by nodes.</p>
<p> - Physical links between servers are represented by edges (can be unidirectional or bidirectional)</p>
<li><strong>Algorithms Used: Dijkstra's Algorithm and Prim’s Algorithm </strong></li>
<li><strong>Team Members:</strong></li>
<ul><li><strong>Neha Tirumalasetti (2358)</strong></li></ul>
<li><strong>Pooja Raut (2344)</strong></li>
<li><strong>Shreya Rao (2353)</strong></li>
<li><strong>Tanya Sikarwar (2354)</strong></li></ul>


<p><strong>ALGORITHMS</strong></p>

<p><strong>void accept()</strong></p>
<ol><li>Accept the number of serves in v</li>
<li>Initialise the adjacency matrix adjMat[][] to 0</li>
<li>Accept the IP address of each server</li>
<li>Accept the number of wires in e</li>
<li>Accept the type of wire 1-Twisted pair and 2- Coaxial cable</li>
<li>Repeat for i=0 to v</li></ol>
<p>i. Accept the 2 servers (u,v) it connects and the transfer time (time)</p>
<p>ii. if(type ==1) ie bidirectional</p>
<ol><li>Set adjMat[u][v]=time</li>
<li>Set adjMat[v][u]=time</li></ol>
<p>                      iii . else if(type==2)</p>
<p>                                      a.  Set adjmat[u][v]=time</p>
<p>    7. Set all the entries in adjMat that are 0 to 9999 indicating no connection is present</p>

<p><strong>void displaymatrix()</strong></p>
<ol><li>Repeat for i=1 to v</li></ol>
<p>i. Repeat for j=i to v</p>
<p>	Display adjMat[i][j]</p>

<p><strong>void insertEdge()</strong></p>
<ol><li>Accept Server1 index in x.</li>
<li>Accept Server2 index in y.</li>
<li>if(x>=1 and x<=v and y>=1 and y<=v) then:</li>
<ol><li>if(adjMat[x][y]==9999) then:</li>
<ol><li>if(type_cable==1) then:</li>
<ol><li>Accept transfer time in t.</li>
<li>Set adjMat[x][y]=t</li>
<li>Set adjMat[y][x]=t</li>
<li>Increment num_of_edges</li></ol>
<p>		   ii.	else if(type_cable==2) then:</p>
<p>			     1.	Accept transfer time in t.</p>
<p>     2.   Set adjMat[x][y]=t</p>
<p>     3.   Increment num_of_edges</p>
<p>  iii.     Else</p>
<p>                1.   Print “Invalid Type”</p>
<p>	   b.      Else</p>
<p>		I.	Print “A cable already connects the servers, still want to update?”</p>
<p>Ii.	Accept the answer in c</p>
<p>Iii.	if(c==’Y’ or c==’y’) then:</p>
<p>	1.	Accept transfer time in t</p>
<p>	2.	if(t>adjMat[x][y]) then:</p>
<ol><li>Print “Existing transfer time is lesser, still want to update?”</li>
<li>Accept answer in cc</li>
<li>if(cc==’Y’ or cc==’y’) then:</li></ol>
<p>	i.if(type==1) then:</p>
<ol><li>Set adjMat[x][y]=t</li>
<li>Set adjMat[y][x]=t</li></ol>
<p>		               		Ii.else if(type==2) then:</p>
<ol><li>Set adjMat[x][y]=t </li></ol>
<p>                                                      Iii.else Print “Invalid Type”</p>
<p>4. Else Print “Invalid Server Number”</p>



<p><strong>void insertNode()</strong></p>
<ol><li>Accept server number to be added in newnode</li>
<li>Set value of c to ‘y’</li>
<li>If (newnode>v and newnode<10) then:</li>
<ol><li>Increment number of servers</li>
<li>Loop till (c== ‘Y’ or c== ‘y’)</li>
<ol><li>Accept Server number to connect to in x</li>
<ol><li>if(x>=1 and x<=v and x!=newnode) then:</li>
<ol><li>if(type_cable==1) then:</li>
<ol><li>Accept transfer time in t</li>
<li>Set adjMat[newnode][x]=t</li>
<li>Set adjMat[x][newnode]=t</li></ol>
<p>			                 b.  Else if(type_cable==2) then:</p>
<p>                                                	  i.       Accept Transfer time in t</p>
<p>                                                 	 ii.       Set adjMat[newnode][x]=t   </p>
<p>                 c.  Else print “Invalid type”</p>
<p>                                     2.    else print “Invalid server number”</p>
<p>      	             ii.  Print “Do you want to add more cables to the new server?[Y/N]”</p>
<p>                       iii.  Accept answer in c</p>
<p>         4.   Else print “Illegal Request”</p>
<p>		</p>



<p><strong>void dijkstra(int s, int d)</strong></p>

<ol><li>Initialize min to 9999 and next to 0 </li>
<li> Repeat while i=1 to v</li></ol>
<p> 	i.time[i]=adjMat[s][i]</p>
<p>ii.visited[i]=0</p>
<p>iii.parent[i]=s</p>
<p>      3.  Traverse time array to find the minimum time from the source,store time in in                          and node in next</p>
<p>      4.    Mark next as visited</p>
<p>      5.     Repeat for i=1 to v</p>
<p>		5.1.  if(adjMat[next][i]!=9999 && visited[i]==0)</p>
<p>                         	 i.  if(time[i]>time[next]+adjMat[next][i])</p>
<p>                                      	    1. time[i]=time[next]+adjMat[next][i]</p>
<p>				    2. parent[i]=next;</p>
<p>     6. Repeat steps 3,4,5 while all nodes are not visited</p>



<p><strong>void getpath(int s, int d)</strong></p>
<ol><li>Set str to “ “ and set d1 to d and i to 0</li>
<li>Repeat while (d!=s)</li></ol>
<p>	i.  Store d in str[i]</p>
<p>           ii.  Increment i</p>
<p>           iii.  Set d=parent[d]</p>
<p>    3. Reverse the string str to get shortest path from s to d</p>
<p>    4. Display reversed str</p>


<p><strong>void deleteedge()</strong></p>
<ol><li>Accept source and destination vertex for the edge to be deleted in a, b.</li>
<li>If adjmat[a][b] is 9999</li>
<ol><li>Display edge does not exist and cannot be deleted.</li>
<li>Else</li>
<li>If cable type is twisted pair cable</li>
<ol><li>Adjmat[a][b] value should be changed to 9999.</li>
<li>Adjmat[b][a] value should be changed to 9999.</li>
<li>Else if cable type is coaxial cable </li>
<li>Adjmat[a][b] value should be changed to 9999.</li>
<li>Decrement edge count e by 1.</li>
<li>Display Connection successfully disabled, will not be considered for further communication.</li></ol>


<p><strong>void deleteserver()</strong></p>
<ol><li>Initialize integer variable count to 0.</li>
<li>Accept server no. to be deleted in integer variable a.</li>
<li>If a is a valid server number i.e a<=number of servers and a>0.</li>
<ol><li>Initialize integer variable k to 1.</li>
<li>Loop until k<=number of servers</li>
<ol><li>If adjmat[a][k] is 9999</li>
<ol><li>Increment count by 1.</li>
<li>Increment k by 1.</li>
<li>Initialize integer variable i to a.</li>
<li>Loop until i<number of servers</li>
<li>Initialize integer variable j to 1.</li>
<li>Loop until j<=number of servers</li>
<li>Set adjmat[j][i]=adjmat[j][i+1]</li>
<li>If type of cable is twisted pair cable </li>
<li>If i!=j</li>
<ol><li>Set adjmat[i][j]=adjmat[i+1][j]</li>
<li>Increment len by 1</li>
<li>Set delserv[len] = a</li>
<li>Decrement number of servers v by 1</li>
<li>Set edge count e = e-count</li>
<li>Display Server successfully disabled,will not be considered for further communications</li>
<li>Else print server not found</li></ol>


<p><strong>void </strong><strong>initialisevisited()</strong></p>
<ol><li>Initialize i to 1</li>
<li>Loop until i<=number of servers</li>
<ol><li>Set visited[i]=0</li></ol>


<p><strong>boolean</strong><strong> </strong><strong>isdeleted</strong><strong>(</strong><strong>int</strong><strong> </strong><strong>k</strong><strong>)</strong></p>
<ol><li>Set boolean variable ret to false.</li>
<li>Set integer variable i to 1.</li>
<li>Loop until i <=len</li>
<ol><li>If k>=delserv[i]</li>
<ol><li>Set ret=true</li>
<li>Return ret.</li></ol>


<p><strong>void</strong><strong> </strong><strong>broadcastprims</strong><strong>()</strong></p>
<ol><li>Call initialisevisited()</li>
<li>If (type==2)</li>
<ol><li>jump to 10</li>
<li>Initialize integer array of size 10.</li>
<li>Initialize integer variables src, cnt=0, min, minind=0, sum=0, k=1.</li>
<li>Accept source vertex in src.</li>
<li>Set visited[src]=1, arr[k]=src.</li>
<li>Increment cnt and k.</li>
<li>Loop until cnt<number of servers (v).</li>
<ol><li>Set min=9999</li>
<li>Set variable i to 1 and loop until i<=number of servers</li>
<ol><li>If vertex i has been visited then set variable j to and loop until j<=number of servers</li>
<ol><li>If adjmat[i][j]<min and visited[j]=0</li>
<ol><li>Set min=adjmat[i][j] </li>
<li>Set minid=j</li>
<li>Set sum=sum+min</li>
<li>Set visited[minind]=1</li>
<li>Increment cnt by 1</li>
<li>Set arr[k]=minind</li>
<li>Display "PATH FROM "+src +" TO "+arr[k]</li>
<li>Set i to 1 and loop until i<=number of servers</li>
<li>Display arr[i]+"-"</li>
<li>Change line</li>
<li>Increment k by 1</li>
<li>Display "Time taken to broadcast the message from "+src+" is "+sum+" seconds"</li>
<li>Repeat i=1 to v</li>
<ol><li>if(i!=s)</li></ol>
<p>i. call dijkstra(s,i) </p>
<p>Ii.call getpath(s,i)</p>
<p><strong>void display()</strong></p>
<ol><li>Display "****************INFORMATION **************".</li>
<li>Display number of servers and wired connections.</li>
<li>Display type of cables.</li>
<li>Display "\nSERVER \t\tSERVER \t\tTRANSFER TIME".</li>
<li>Set i=1 and loop until i < = number of servers</li>
<ol><li>Set j=1 and loop until j<=number of servers</li>
<ol><li>If adjmat[i][j]!=9999</li>
<ol><li>Display "Server "+i+"\tServer "+j+"\t "+adjmat[i][j]+" seconds"</li>
<li>Change line.</li></ol>

<p><strong>TIME COMPLEXITIES</strong></p>
<ol><li>Create : O(v)</li>
<li>Display: O(v^2)</li>
<li>insertEdge: O(1)</li>
<li>insertNode: O(1)</li>
<li>dijkstra:O(v^2)</li>
<li>Prims: O(ElogV)</li>
<li>Deleteedge: O(1)</li>
<li>Deleteserver: O(v^2)</li></ol>



