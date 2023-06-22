class Solution {
    public int minFallingPathSum(int[][] matrix) {
        
        int min=Integer.MAX_VALUE;
        int n=matrix.length;
        int dp[][]=new int[n][n];
        for(int a[]:dp)
        {
            Arrays.fill(a,-1);
        }
        // for(int i=0;i<matrix.length;i++)
        // {
        //     min=Math.min(min,minFallingaPathSumRecursive(0,i,matrix,matrix.length));
        // }
      
        //   for(int i=0;i<matrix.length;i++)
        // {
        //     min=Math.min(min,minFallingaPathSumMemoization(0,i,matrix,matrix.length,dp));
        // }
        // return min;

       // return minFallingaPathSumDP(matrix);

       return minFallingaPathSumDPSpaceOptimized(matrix);

    }
    public static int minFallingaPathSumRecursive(int i,int j,int matrix[][],int n)
    {
        if(i==n-1)
        {
            return matrix[i][j];
        }
        int dl=Integer.MAX_VALUE;
        int d=Integer.MAX_VALUE;
        int dr=Integer.MAX_VALUE;
        if(j-1>=0)
        {
            dl=matrix[i][j]+minFallingaPathSumRecursive(i+1,j-1,matrix,n);
        }
        d=matrix[i][j]+minFallingaPathSumRecursive(i+1,j,matrix,n);
        if(j+1<n)
        {
            dr=matrix[i][j]+minFallingaPathSumRecursive(i+1,j+1,matrix,n);
        }
        return Math.min(dl,Math.min(dr,d));
    }
     public static int minFallingaPathSumMemoization(int i,int j,int matrix[][],int n,int dp[][])
    {
        if(i==n-1)
        {
            return matrix[i][j];
        }
        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }
        int dl=Integer.MAX_VALUE;
        int d=Integer.MAX_VALUE;
        int dr=Integer.MAX_VALUE;
        if(j-1>=0)
        {
            dl=matrix[i][j]+minFallingaPathSumMemoization(i+1,j-1,matrix,n,dp);
        }
        d=matrix[i][j]+minFallingaPathSumMemoization(i+1,j,matrix,n,dp);
        if(j+1<n)
        {
            dr=matrix[i][j]+minFallingaPathSumMemoization(i+1,j+1,matrix,n,dp);
        }
        return dp[i][j]=Math.min(dl,Math.min(dr,d));
    }

    public static int minFallingaPathSumDP(int matrix[][])
    {
        int n=matrix.length;
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++)
        {
            dp[0][i]=matrix[0][i];
        }
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
               int dl=Integer.MAX_VALUE;
                int dr=Integer.MAX_VALUE;
                int d=0;
                 if(j-1>=0)
                {
                     dl=matrix[i][j]+dp[i-1][j-1];
                }
                d=matrix[i][j]+dp[i-1][j];
                if(j+1<n)
                {
                    dr=matrix[i][j]+dp[i-1][j+1];
                }
                 dp[i][j]=Math.min(dl,Math.min(dr,d));
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
        {
            min=Math.min(min,dp[n-1][i]);
        }
        return min;
    }

     public static int minFallingaPathSumDPSpaceOptimized(int matrix[][])
    {
        int n=matrix.length;
        int top[]=new int[n];
        for(int i=0;i<n;i++)
        {
            top[i]=matrix[0][i];
        }
        for(int i=1;i<n;i++)
        {
            int curr[]=new int[n];
            for(int j=0;j<n;j++)
            {
               int dl=Integer.MAX_VALUE;
                int dr=Integer.MAX_VALUE;
                int d=0;
                 if(j-1>=0)
                {
                     dl=matrix[i][j]+top[j-1];
                }
                d=matrix[i][j]+top[j];
                if(j+1<n)
                {
                    dr=matrix[i][j]+top[j+1];
                }
                 curr[j]=Math.min(dl,Math.min(dr,d));
            }
            top=curr;
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
        {
            min=Math.min(min,top[i]);
        }
        return min;
    }
}