
// A substring of a string is a subsequence in which all the characters are consecutive. Given two strings, we need to find the longest common substring.

// Example:

//  "abcjklp"       "acjkp"
 
//  common- "cjk"
 
 class Solution{
    static int max;
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // code here
        max=0;
         int dp[][]=new int[S1.length()+1][S2.length()+1];
        for(int row[]:dp)
        {
            Arrays.fill(row,-1);
        }
         return lcs(S1,S2,S1.length(),S2.length(),0);
       
        // return max;
       //return lcsMemoization(S1,S2,S1.length(),S2.length(),0,dp);
      //return lcsDP(S1,S2);
      //return lcsDPOptimized(S1,S2);
    }
    
    
    
    
   public static int lcs(String X, String Y, int i, int j, int count) {
        // Base case: if either string is empty, return the count
        
         if (i == 0 || j == 0) 
        {
            return count;
        }

        if (X.charAt(i - 1) 
            == Y.charAt(j - 1)) 
        {
            count = lcs(X,Y,i - 1, j - 1, count + 1);
        }
        count = Math.max(count, 
                         Math.max(lcs(X,Y,i, j - 1, 0),
                                  lcs(X,Y,i - 1, j, 0)));
        return count;
       
    }

    
  
    
    

    public static int lcsDP(String text1,String text2)
    {
          int dp[][]=new int[text1.length()+1][text2.length()+1];

            int m=text1.length();
            int n=text2.length();
          for(int i=0;i<=m;i++)
          {
              dp[i][0]=0;
          }
          for(int i=0;i<=n;i++)
          {
              dp[0][i]=0;
          }
          int ans=0;
          for(int ind1=1;ind1<=text1.length();ind1++)
          {
              for(int ind2=1;ind2<=text2.length();ind2++)
              {

                if(text1.charAt(ind1-1)==text2.charAt(ind2-1))
                {
                    int val=1+dp[ind1-1][ind2-1];
                    dp[ind1][ind2]=val;
                    ans=Math.max(ans,val);
                }
                else
                {
                    dp[ind1][ind2]=0; 
                }
                    
              }
          }
          return ans;
    }

     public static int lcsDPOptimized(String text1,String text2)
    {
          int dp[]=new int[text2.length()+1];

            int m=text1.length();
            int n=text2.length(); 
        int ans=0;
          for(int i=0;i<=n;i++)
          {
              dp[i]=0;
          }
          for(int ind1=1;ind1<=text1.length();ind1++)
          {
              int curr[]=new int[n+1];
              for(int ind2=1;ind2<=text2.length();ind2++)
              {

                if(text1.charAt(ind1-1)==text2.charAt(ind2-1))
                {
                    curr[ind2]=1+dp[ind2-1];
                    ans=Math.max(curr[ind2],ans);
                }
                else
                {
                    curr[ind2]=0; 
                }
                    
              }
              dp=curr;
          }
          return ans;
    }
}