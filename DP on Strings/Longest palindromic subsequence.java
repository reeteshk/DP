Longest Palindromic Subsequence - LeetCodeclass 

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Solution {
   public int longestPalindromeSubseq(String s) {
        
        String s1="";
        for(int i=s.length()-1;i>=0;i--)
        {
            s1=s1+s.charAt(i);
        }
        
        return longestCommonSubsequence(s,s1);
    }
    public int longestCommonSubsequence(String text1, String text2) {
        
        int n=text1.length();
    int m=text2.length();

    int dp[][]=new int[n+1][m+1];
    for(int rows[]: dp)
    Arrays.fill(rows,-1);
        return longestCommonSubsequencesDP(text1,text2);
        
    }
    
     public int longestCommonSubsequencesDP(String text1,String text2)
    {
        int m=text1.length();
        int n=text2.length();
        int dp[][]=new int[m+1][n+1];
        for(int rows[]: dp)
        Arrays.fill(rows,-1);
        
        for(int i=0;i<=m;i++)
        {
            dp[i][0]=0;
        }
        for(int i=0;i<=n;i++)
        {
            dp[0][i]=0;
        }
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                
                if(text1.charAt(i-1) == text2.charAt(j-1))
            {
               dp[i][j]=1+dp[i-1][j-1];
            }
                else
                {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
         
            }
        }
        return dp[m][n];
        
    }
}