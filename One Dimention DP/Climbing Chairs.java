Climbimg Chairs

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


	// ***Recursion***

 public int climbStairsRec(int n)
    {
        if(n<=2)
        {
            
            return n;
        }
        return climbStairsRec(n-1)+climbStairsRec(n-2);

    }
	
	
	
	//***Memoization***
	
	
	 public int climbStairsMem(int n,int dp[] )
    {
        if(n<=2)
        {
            dp[n]=n;
            return dp[n];
        }
        if(dp[n]!=-1)
        {
            return dp[n];
        }
        return dp[n]=climbStairsMem(n-1,dp)+climbStairsMem(n-2,dp);

    }
	
	//****Dp****
	
		 public int climbStairsDP(int n)
    {
        if(n==0 || n==1)
        {
           return 1;
        }
		int dp[]=new int[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++)
		{
			dp[i]=dp[i-1]+dp[i-2];
		}
        return dp[n];

    }

	///***Optimized Dp***

	 public int climbStairsDPOptimized(int n)
	{
		if(n==0 || n==1)
		{
		   return 1;
		}
		int prev=1;
		int curr=1;
		for(int i=2;i<=n;i++)
		{
			int temp=curr;
			curr=prev+curr;
			prev=temp;
		}
		return curr;

	}// Path: Climbing Chairs.java