class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        
        int dp[][]=new int[triangle.size()][triangle.size()];
        for(int a[]:dp)
        {
            Arrays.fill(a,-1);
        }
        //return minimumTotal(0,0,triangle,triangle.size());
        //return minimumTotalMemoization(0,0,triangle,triangle.size(),dp);
        //return minimumTotalDP(triangle);
        return minimumTotalDPOptimized(triangle);
    }

    //Recursive Approach
    public int minimumTotal(int i,int j,List<List<Integer>> triangle,int n)
    {
        if(i==n-1)
        {
            return triangle.get(i).get(j);
        }
        int d=triangle.get(i).get(j)+minimumTotal(i+1,j,triangle,n);
        int dg=triangle.get(i).get(j)+minimumTotal(i+1,j+1,triangle,n);
        return Math.min(d,dg);
    }

    //Memoization Approach
    public int minimumTotalMemoization(int i,int j,List<List<Integer>> triangle,int n,int dp[][])
    {
        if(i==n-1)
        {
            return triangle.get(i).get(j);
        }
        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }
        int d=triangle.get(i).get(j)+minimumTotalMemoization(i+1,j,triangle,n,dp);
        int dg=triangle.get(i).get(j)+minimumTotalMemoization(i+1,j+1,triangle,n,dp);
        return dp[i][j]=Math.min(d,dg);
    }

    //DP Approach
    public int minimumTotalDP(List<List<Integer>> triangle)
    {
        int n=triangle.size();
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++)
        {
            dp[n-1][i]=triangle.get(n-1).get(i);
        }
        for(int i=n-2;i>=0;i--)
        {
            for(int j=i;j>=0;j--)
            {
                int d=triangle.get(i).get(j)+dp[i+1][j];
                int dg=triangle.get(i).get(j)+dp[i+1][j+1];
                dp[i][j]=Math.min(d,dg);
            }
        }
        return dp[0][0];

    }

    //DP Approach Space Optimized
       public int minimumTotalDPOptimized(List<List<Integer>> triangle)
    {
        int n=triangle.size();
       int front[]=new int[n];
        for(int i=0;i<n;i++)
        {
            front[i]=triangle.get(n-1).get(i);
        }
        for(int i=n-2;i>=0;i--)
        {
            int curr[]=new int[n];
            for(int j=i;j>=0;j--)
            {
                int d=triangle.get(i).get(j)+front[j];
                int dg=triangle.get(i).get(j)+front[j+1];
                curr[j]=Math.min(d,dg);
            }
            front=curr;
        }
        return front[0];

    }
    

}