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
			System.out.print("enter the transfer time:");
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
	void deleteroute()
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
		System.out.println("Connection successfully disabled,will not be considered for further communications");
		}
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
	}
	void initialisevisited()
	{
		for(int i=1;i<=v;i++)
		{
			visited[i]=0;
		}
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
			System.out.print("-"+minind);	
		}//end of while
		System.out.print("\n\nMinimum Weight:"+sum);
}
}

public class networkrouting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		graph g=new graph();
		int s,d;
		g.accept();
		System.out.println("enter source and dest");
		s=sc.nextInt();
		d=sc.nextInt();
		g.dj(s, d);
		g.getpath(d,s);
		g.deleteroute();
		g.broadcastprims();

	}

}
