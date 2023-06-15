/*
Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair. 
At a time the frog can climb either one or two steps. A height[N] array is also given. 
Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference.
We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1.

*/

//"***Recursion***"

static int solve(int ind,int[] height){
    if(ind==0) return 0;

    int jumpTwo = Integer.MAX_VALUE;
    int jumpOne= solve(ind-1, height)+ Math.abs(height[ind]-height[ind-1]);
    if(ind>1)
        jumpTwo = solve(ind-2, height)+ Math.abs(height[ind]-height[ind-2]);
    
    return Math.min(jumpOne, jumpTwo);
}




//"***Memoization***"

static int solve(int ind,int[] height,int[] dp){
    if(ind==0) return 0;
    if(dp[ind]!=-1) return dp[ind];
    int jumpTwo = Integer.MAX_VALUE;
    int jumpOne= solve(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
    if(ind>1)
        jumpTwo = solve(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
    return dp[ind]=Math.min(jumpOne, jumpTwo);
}

//"***DP***"

static int solve(int height[])
{
 int n=height.length;
  int dp[]=new int[n];
  Arrays.fill(dp,-1);
  dp[0]=0;
  for(int ind=1;ind<n;ind++){
      int jumpTwo = Integer.MAX_VALUE;
        int jumpOne= dp[ind-1] + Math.abs(height[ind]-height[ind-1]);
        if(ind>1)
            jumpTwo = dp[ind-2] + Math.abs(height[ind]-height[ind-2]);
    
        dp[ind]=Math.min(jumpOne, jumpTwo);
  }
  return dp[n-1];
}
  
  
//"***OptimizedDP***"
 static int solve(int height[])
{
 int n=height.length;
   int prev=0;
  int prev2=0;
  for(int i=1;i<n;i++){
      
      int jumpTwo = Integer.MAX_VALUE;
      int jumpOne= prev + Math.abs(height[i]-height[i-1]);
      if(i>1)
        jumpTwo = prev2 + Math.abs(height[i]-height[i-2]);
    
      int cur_i=Math.min(jumpOne, jumpTwo);
      prev2=prev;
      prev=cur_i;
        
  }
 return prev;
 }
 