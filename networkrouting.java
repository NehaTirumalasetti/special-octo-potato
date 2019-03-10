package networkrouting;
import java.util.Scanner;
class graph
{
	int v;
	int e;
	int wt;
	int type;
	Scanner sc=new Scanner(System.in);
	int adjmat[][]=new int[10][10];
	int dist[]=new int[10];
	int visited[]=new int[10];
	int parent[]=new int[10];
	int delserv[]=new int [10];
	int len=0;
	void accept()
	{
		System.out.println("Enter number of servers:");
		v=sc.nextInt();
		for(int i=1;i<=v;i++) //initialising the matrix
		{ 
			for(int j=1;j<=v;j++)
			{
				adjmat[i][j]=0;
			}
		}
		int a=0,b=0;
		System.out.println("Enter number of wires:");
		e=sc.nextInt();
		System.out.println("Type of wire 1:twisted pair cable 2:coaxial cable");
		type=sc.nextInt();
		for(int i=0;i<e;i++)
		{ 
			System.out.println("enter the servers that are connected:");
			System.out.print("server 1:");
			a=sc.nextInt();
			System.out.print("server 2:");
			b=sc.nextInt();
			System.out.print("enter the transfer time(in seconds):");
			wt=sc.nextInt();
			if(type==1)
			{
			    adjmat[a][b]=wt;
			    adjmat[b][a]=wt;
			}
			else if(type==2)
			{
				adjmat[a][b]=wt;
			}
			}
		for(int i=1;i<=v;i++) 
		{ 
			for(int j=1;j<=v;j++)
			{
				if(adjmat[i][j]==0)
					adjmat[i][j]=9999;
			}
		}
		
		}
	void displaymatrix()
	{
		for(int i=1;i<=v;i++) 
		{ 
			for(int j=1;j<=v;j++)
			{
				System.out.print(adjmat[i][j]+"   ");
			}
			System.out.println();
		}
	}
	void deleteedge()
	{
		System.out.println("Enter the servers the faulty wire connects");
		int a,b;
		a=sc.nextInt();
		b=sc.nextInt();
		if(adjmat[a][b]==9999)
		{
			System.out.println("No wire connection present");
		}
		else
		{
		if(type==1)
		{
			adjmat[a][b]=9999;
			adjmat[b][a]=9999;
		}
		else if(type==2)
		{
			adjmat[a][b]=9999;
		}
		e--;
		System.out.println("Connection successfully disabled,will not be considered for further communications");
		}
	}
	void deleteserver()
	{
		int count=0;
		System.out.println("Enter faulty server");
		int a=sc.nextInt();
		
		if(a<=v && a>0)
		{
			for(int k=1;k<=v;k++)
			{
				if(adjmat[a][k]!=9999)
					count++;
			}
		   for(int i=a;i<v;i++)
		   {
			   for(int j=1;j<=v;j++)
			   {
				   adjmat[j][i]=adjmat[j][i+1];
			   }
			   if(type==1)
			   {
				   for(int j=1;j<=v;j++)
					   if(i!=j)
					   adjmat[i][j]=adjmat[i+1][j];
			   }
		   }
		   len++;
		   delserv[len]=a;
		    v--;
		    e=e-count;
			System.out.println("Server successfully disabled,will not be considered for further communications");
		}
		else
			System.out.println("Server not found");
	}
	void dj(int s,int d)
	{
		initialisevisited();
		for(int i=1;i<=v;i++)
		{
			dist[i]=adjmat[s][i];
			parent[i]=s;
		}
		
		int n=1;
		while(n<=v)
		{
		int min =9999;
		int next=1;
		for(int i=1;i<=v;i++) //to find the min distance from source
		{
			if(dist[i]<min && visited[i]==0)
			{
				min=dist[i];
				next=i;
			}
		}
		visited[next]=1;
		n++;
		for(int i=1;i<=v;i++)
		{
			if(adjmat[next][i]!=9999&&visited[i]!=1&& i!=s)
			{
				if(dist[i]>(dist[next]+adjmat[next][i]))
				{
					dist[i]=dist[next]+adjmat[next][i];
					parent[i]=next;
				}
			}
		}
		}//end of while
	}
	void getpath(int d,int s)
	{
	    int str[]=new int[10];
	    int i=0;
		do
		{
			str[i]=d;
			i++;
			d=parent[d];
		}while(d!=s);
		str[i]=s;
		for(int j=i;j>=0;j--)
		{
			System.out.print(str[j]+" ");
		}
		System.out.println("\nTime required to send the message from server "+s+" to server "+d+" is "+dist[d]+" seconds");
	}
	void initialisevisited()
	{
		for(int i=1;i<=v;i++)
		{
			visited[i]=0;
		}
	}
	boolean isdeleted(int k)
	{
		boolean ret=false;
		for(int i=1;i<=len;i++)
		{
			if(k>=delserv[i])
				ret=true;
		}
		return ret;
	}
	void broadcastprims()
	{
		initialisevisited();
		int src,cnt=0,min,minind=0,sum=0;
		System.out.println("\nEnter Source vertex:");	
		src=sc.nextInt();
		
		visited[src]=1;
		cnt++;
		System.out.print("\nOrder:"+src);
		while(cnt<v)
		{
			min=9999;
			
			for(int i=1;i<=v;i++)
			{
				if(visited[i]==1)//for all vertices already included in mst
				{
					for(int j=1;j<=v;j++)
					{
						if(adjmat[i][j]<min && visited[j]==0)
						{
						min=adjmat[i][j];
						minind=j;
						}
					}//traversing one row and finding min
				
				}//Finding min amongst all mins in mst	
		    }
			sum=sum+min;
			visited[minind]=1;
			cnt++;
			if(isdeleted(minind)==true)
			System.out.print("-"+(minind+1));
			else
				System.out.print("-"+minind);

		}//end of while
		System.out.println("Time taken to broadcast the messgae from "+src+" is "+sum+" seconds");
}
	void insertEdge() {
		System.out.println("Server 1:");
		int x=sc.nextInt();
		System.out.println("Server 2:");
		int y=sc.nextInt();
		if(x>=1 && x<=v && y>=1 && y<=v) {
			if(adjmat[x][y]==9999) { //new edge to be inserted
				if(type==1) { //twisted pair
				System.out.println("Enter transfer time(in seconds):");
				adjmat[x][y]=sc.nextInt();
				adjmat[y][x]=adjmat[x][y];
				System.out.println("Twisted Pair cable succesfully added!!");
			    e++;
				}
				else if(type==2) { //coaxial cable
					System.out.println("Enter transfer time:");
					adjmat[x][y]=sc.nextInt();
					e++;
					System.out.println("Coaxial cable succesfully added!!");
				}
				else { //validation for type of cable.
					System.out.println("Invalid cable type!!");
				}
			}
			else { //edge already there
				System.out.println("A cable already connects the given servers, do you still want to update?[Y/N]");
				char c=sc.next().charAt(0);
				char cc='y';
				if(c=='Y' || c=='y') {
					System.out.println("Enter transfer time(in seconds):");
					int time=sc.nextInt();
					if(time>adjmat[x][y]) {
						System.out.println("The exsisting cable transfers faster, do you still want to replace the wire?[Y/N]");
						cc=sc.next().charAt(0);
					}
					if(cc=='y' || cc=='Y') {
						if(type==1) { //twisted pair
						adjmat[x][y]=time;
						adjmat[y][x]=adjmat[x][y];
						System.out.println("Twisted Pair cable succesfully repalced!!");
						}
						else if(type==2) { //coaxial cable
							System.out.println("Enter transfer time(in seconds):");
							adjmat[x][y]=sc.nextInt();
							System.out.println("Coaxial cable succesfully replaced!!");
						}
						else { //validation for type of cable.
							System.out.println("Invalid cable type!!");
						}
					}
				}
			}
		}
		else { //validation for invalid servers
			System.out.println("Invalid Server Numbers!!");
		}
	}
	
	void insertNode() {
		System.out.println("Enter new server number to be added:");
		int newnode=sc.nextInt();
		char c='y';
		if(newnode>v && newnode<10) {
			v++; //incrementing total number of servers.
			while(c=='Y' || c=='y') 
			{
				System.out.println("Server to connect to:");
				int x=sc.nextInt();
				if(x>=1 && x<=v && x!=newnode){       //self loop not allowed
					if(type==1) { //twisted pair
					System.out.println("Enter transfer time:");
					adjmat[x][newnode]=sc.nextInt();
					adjmat[newnode][x]=adjmat[x][newnode];
					System.out.println("New Server with twisted pair cables succesfully added!!");
					v++;
					}
					else if(type==2) { //coaxial cable
						System.out.println("Enter transfer time:");
						adjmat[x][newnode]=sc.nextInt();
						System.out.println("New Server with Coaxial cable succesfully added!!");
						v++;
					}
					else { //validation for type of cable.
						System.out.println("Invalid cable type!!");
					}
				}
				else {//validation for server number
					System.out.println("Invlaid Server Number!!");
				}
				System.out.println("Do you want to add more cables for the new server?[Y/N]");
				c=sc.next().charAt(0);
			}
		}
		else {
			System.out.println("Illegal Request!!");
		}
	}
	void display()
	{
		System.out.println("Number of Servers:"+v);
		System.out.println("Number of Wired Connections:"+e);
		if(type==1)
			System.out.println("Type of Wires:Twisted Pair Cables");
		else
			System.out.println("Type of Wires:Coaxial Cables");
		System.out.println("Server \tServer \tTransfer Time");
		for(int i=1;i<=v;i++)
		{
			for(int j=1;j<=v;j++)
			{
				if(adjmat[i][j]!=9999)
				{
					System.out.println("Server "+i+"\tServer "+j+"\t "+adjmat[i][j]+"seconds");
				}
			}
			System.out.println();
		}
	}
}

public class networkrouting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		graph g=new graph();
		int s,d;
		g.accept();
		int x,y;
		do
		{
		System.out.println("\n1.Send data \n2.Broadcast data \n3.Update the network\n4.Display network information\n5.Exit");
		x=sc.nextInt();
		switch (x)
		{
			case 1:System.out.println("Enter source and destination for the data:");
				   s=sc.nextInt();
				   d=sc.nextInt();
				   g.dj(s, d);
				   g.getpath(d,s);
				   break;
			case 2:g.broadcastprims();
				   break;
			case 3:System.out.println("1.Remove wire \n2.Add wire \n3.Delete server \n4.Add server");
				   y=sc.nextInt();
				   switch(y)
				   {
				   case 1:g.deleteedge();
				   		  break;
				   case 2:g.insertEdge();
				          break;
				   case 3:g.deleteserver();
				   		  break;
				   case 4:g.insertNode();
				          break;
				   }
				   break;
			case 4:g.displaymatrix();
				   g.display();
			       break;
			case 5:System.out.println("Exit!");
			       break;
			default:System.out.println("invalid input");		
		}
		}while(x!=5);
	}

}
