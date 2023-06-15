
***Recursion***

static int f(int n, int[] dp){
    if(n<=1) return n;


    return  f(n-1,dp) + f(n-2,dp);
}

Time Complexity: O(N)

Reason: The overlapping subproblems will return the answer in constant time O(1). Therefore the total number of new subproblems we solve is ‘n’. Hence total time complexity is O(N).

Space Complexity: O(N)

Reason: We are using a recursion stack space(O(N)) and an array (again O(N)). Therefore total space complexity will be O(N) + O(N) ≈ O(N)


***Memoization***

static int f(int n, int[] dp){
    if(n<=1) return n;

    if(dp[n]!= -1) return dp[n];
    return dp[n]= f(n-1,dp) + f(n-2,dp);
}

***DP***

static int f(int n){
  int n=5;
  int dp[]=new int[n+1];
  Arrays.fill(dp,-1);
  dp[0]= 0;
  dp[1]= 1;

  for(int i=2; i<=n; i++){
      dp[i] = dp[i-1]+ dp[i-2];
  }
  return dp[n];
}

public static int fibDPOptimized(int n)
        {
            int prev=0;
            int curr=1;
            for(int i=2;i<=n;i++)
            {
                int temp=curr;
                curr=prev+curr;
                prev=temp;
            }
            return curr;
        }




