public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        
        //return RecursiveUniquePath(m-1,n-1,obstacleGrid);

        // int dp[][]=new int[m][n];
        // for(int a[]:dp)
        // {
        //     Arrays.fill(a,-1);
        // }
        // return MemoizationUniquePath(m-1,n-1,obstacleGrid,dp);

      //  return DPUniquePath(obstacleGrid);

      return SpaceOptimizedDPUniquePath(obstacleGrid);
    }


    //Recursive Approach
    public static int RecursiveUniquePath(int m,int n,int obstacleGrid[][])
    {
         if(m==0 && n==0 && obstacleGrid[m][n]==0)
        {
            return 1;
        }
         if(m==0 && n==0 && obstacleGrid[m][n]==1)
        {
            return 0;
        }
        if(m<0 || n<0)
        {
            return 0;
        }
        if(obstacleGrid[m][n]==1)
        {
            return 0;
        }
        int up=RecursiveUniquePath(m-1,n,obstacleGrid);
        int left =RecursiveUniquePath(m,n-1,obstacleGrid);
        return up+left;
    }

    //Memoization Approach
    public static int MemoizationUniquePath(int m,int n,int obstacleGrid[][], int dp[][])
    {
         if(m==0 && n==0 && obstacleGrid[m][n]==0)
        {
            return 1;
        }
         if(m==0 && n==0 && obstacleGrid[m][n]==1)
        {
            return 0;
        }
        if(m<0 || n<0)
        {
            return 0;
        }
        if(obstacleGrid[m][n]==1)
        {
            return 0;
        }
        if(dp[m][n]!=-1)
        {
            return dp[m][n];
        }
        int up=MemoizationUniquePath(m-1,n,obstacleGrid,dp);
        int left =MemoizationUniquePath(m,n-1,obstacleGrid,dp);
        return dp[m][n]=up+left;
    }

    //Dynamic Programming Approach
    public static int DPUniquePath(int obstacleGrid[][])
    {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int dp[][]=new int[m][n];
        dp[0][0]=1;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(obstacleGrid[i][j]==1) dp[i][j]=0;
                else if(i==0 && j==0) dp[i][j]=1;
                else
                {
                    int down=0;
                    int right=0;
                    if(i>0) down=dp[i-1][j];
                    if(j>0) right=dp[i][j-1];
                    dp[i][j]=(down+right);
                }
        }
        
    }
    return dp[m-1][n-1];
}


 //Space Optimization Approach
 public static int SpaceOptimizedDPUniquePath(int obstacleGrid[][])
    {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int prev[]=new int[n];
        for(int i=0;i<m;i++)
        {
            int curr[]=new int[n];
            for(int j=0;j<n;j++)
            {
                if(obstacleGrid[i][j]==1) curr[j]=0;
                else if(i==0 && j==0) curr[j]=1;
                else
                {
                    int down=0;
                    int right=0;
                    if(i>0) down=prev[j];
                    if(j>0) right=curr[j-1];
                    curr[j]=(down+right);
                }
        }
        prev=curr;
        
    }
    return prev[n-1];
}
} 