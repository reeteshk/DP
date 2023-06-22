class Solution {
    public int cherryPickup(int[][] grid) {

     //return recursivecherrypickup(grid,0,0,grid[0].length-1,grid.length,grid[0].length);
     int n=grid.length;
     int m=grid[0].length;
     int dp[][][]=new int[n][m][m];

        for (int row1[][]: dp) {
            for (int row2[]: row1) {
             Arrays.fill(row2, -1);
            }
        }

   // return memoizationcherrypickup(grid,0,0,grid[0].length-1,grid.length,grid[0].length,dp);
   return DPcherrypickup(grid);
    }
    public static int recursivecherrypickup(int [][]grid,int i,int j1, int j2,int n,int m)
    {
        if(j1<0 || j2<0 || j1>=m || j2>=m)
        {
            return (int)Math.pow(-10,9);
        }
        if(i==n-1)
        {
            if(j1==j2) return grid[i][j1];
            else return grid[i][j1]+grid[i][j2];
        }
        //explore all path 
        int maxi=Integer.MIN_VALUE;
        for(int dj1=-1;dj1<=+1;dj1++)
        {
            for(int dj2=-1;dj2<=+1;dj2++)
            {
                int value=0;
                if(j1==j2) value =grid[i][j1]+recursivecherrypickup(grid,i+1,j1+dj1,j2+dj2,n,m);
                else
                {
                    value=grid[i][j1]+grid[i][j2]+recursivecherrypickup(grid,i+1,j1+dj1,j2+dj2,n,m);
                }
                maxi=Math.max(maxi,value);
            }
        }
        return maxi;
    }
    public static int memoizationcherrypickup(int [][]grid,int i,int j1, int j2,int n,int m,int dp[][][])
    {
        if(j1<0 || j2<0 || j1>=m || j2>=m)
        {
            return (int)Math.pow(-10,9);
        }
        if(i==n-1)
        {
            if(j1==j2) return grid[i][j1];
            else return grid[i][j1]+grid[i][j2];
        }
        if(dp[i][j1][j2]!=-1)
        {
            return dp[i][j1][j2];
        }
        //explore all path 
        int maxi=Integer.MIN_VALUE;
        for(int dj1=-1;dj1<=+1;dj1++)
        {
            for(int dj2=-1;dj2<=+1;dj2++)
            {
                int value=0;
                if(j1==j2) value =grid[i][j1]+memoizationcherrypickup(grid,i+1,j1+dj1,j2+dj2,n,m,dp);
                else
                {
                    value=grid[i][j1]+grid[i][j2]+memoizationcherrypickup(grid,i+1,j1+dj1,j2+dj2,n,m,dp);
                }
                maxi=Math.max(maxi,value);
            }
        }
        return  dp[i][j1][j2]=maxi;
    }

    public static int DPcherrypickup(int [][]grid)
    {
        int n=grid.length;
        int m=grid[0].length;
        int dp[][][]=new int[n][m][m];

        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(i==j)
                {
                    dp[n-1][i][j]=grid[n-1][j];
                }
                else
                {
                    dp[n-1][i][j]=grid[n-1][i]+grid[n-1][j];
                }
            }
        }
       
         for (int i = n - 2; i >= 0; i--) {
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {

          int maxi = Integer.MIN_VALUE;

          //Inner nested loops to try out 9 options
          for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {

              int ans;

              if (j1 == j2)
                ans = grid[i][j1];
              else
                ans = grid[i][j1] + grid[i][j2];

              if ((j1 + di < 0 || j1 + di >= m) ||
                (j2 + dj < 0 || j2 + dj >= m))

                ans += (int) Math.pow(-10, 9);
              else
                ans += dp[i + 1][j1 + di][j2 + dj];

              maxi = Math.max(ans, maxi);
            }
          }
          dp[i][j1][j2] = maxi;
        }
      }
    }
        return dp[0][0][m-1];
    }
}