class Solution {
    public int minPathSum(int[][] grid) {
        
        int m=grid.length;
        int n=grid[0].length;
      //  return recursiveApproachminPathSum(m-1,n-1,grid);

    // int dp[][]=new int[m][n];
    // for(int a[]:dp)
    // {
    //     Arrays.fill(a,-1);
    // }
    // return MemoizationminPathSum(m-1,n-1,grid,dp);

    //return DPminPathSum(grid);

    return DPminPathSumSpaceOtmized(grid);
    }

    //Recursive Approach
    public static int recursiveApproachminPathSum(int m,int n,int [][] grid)
    {

        if(m==0 && n==0)
        {
            return grid[m][n];
        }
        if(m<0 || n<0)
        {
            return Integer.MAX_VALUE;
        }
        int up=Integer.MAX_VALUE;
        int left=Integer.MAX_VALUE;
        if(m-1>=0)
        {
            up=recursiveApproachminPathSum(m-1,n,grid);
        }
        if(n-1>=0)
        {
            left=recursiveApproachminPathSum(m,n-1,grid);
        }
        return grid[m][n]+Math.min(up,left);

    }
    //Memoization Approach
    public static int MemoizationminPathSum(int m,int n,int [][] grid,int dp[][])
    {

        if(m==0 && n==0)
        {
            return grid[m][n];
        }
        if(m<0 || n<0)
        {
            return Integer.MAX_VALUE;
        }
        if(dp[m][n]!=-1)
        {
            return dp[m][n];
        }
        int up=MemoizationminPathSum(m-1,n,grid,dp);
        int left=MemoizationminPathSum(m,n-1,grid,dp);
        return dp[m][n]=grid[m][n]+Math.min(up,left);

    }

    //Dynamic Programming Approach
    public static int DPminPathSum(int [][]grid)
    {   
        int m=grid.length;
        int n=grid[0].length;
        int dp[][]=new int[m][n];
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i==0 && j==0)
                {
                    dp[i][j]=grid[i][j];
                }
                else
                {
                    int right=Integer.MAX_VALUE;
                    int down=Integer.MAX_VALUE;
                    if(j>0)
                    {
                        right=dp[i][j-1];
                    }
                    if(i>0)
                    {
                        down=dp[i-1][j];
                    }
                    dp[i][j]=grid[i][j]+Math.min(right,down);
                }
            }
        }
        return dp[m-1][n-1];
    }

    //Space Optimized Dynamic programming Approach
     public static int DPminPathSumSpaceOtmized(int [][]grid)
    {   
        int m=grid.length;
        int n=grid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<m;i++)
        {
            int curr[]=new int[n];
            for(int j=0;j<n;j++)
            {
                if(i==0 && j==0)
                {
                    curr[j]=grid[i][j];
                }
                else
                {
                    int right=Integer.MAX_VALUE;
                    int down=Integer.MAX_VALUE;
                    if(j>0)
                    {
                        right=curr[j-1];
                    }
                    if(i>0)
                    {
                        down=prev[j];
                    }
                    curr[j]=grid[i][j]+Math.min(right,down);
                }
            }
            prev=curr;
        }
        return prev[n-1];
    }

}