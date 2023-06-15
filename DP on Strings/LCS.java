LCS 

1143. Longest Common Subsequence
Medium
10K
117
Companies
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Recursion

public int longestCommonSubsequenceshfiting(String text1,int ind1, String text2,int ind2)
    {
        if(ind1==0 || ind2==0)
        {
            return 0;
        }
        if(text1.charAt(ind1-1) == text2.charAt(ind2-1))
            
        {
            return 1+longestCommonSubsequenceshfiting(text1,ind1-1,text2,ind2-1,dp);
        }
        return Math.max(longestCommonSubsequenceshfiting(text1,ind1,text2,ind2-1),longestCommonSubsequenceshfiting(text1,ind1-1,text2,ind2));
    }
	
	


 public int longestCommonSubsequence(String text1, String text2) {
        
        int n=text1.length();
    int m=text2.length();

    int dp[][]=new int[n+1][m+1];
    for(int rows[]: dp)
    Arrays.fill(rows,-1);
        
       // return longestCommonSubsequence(text1,text1.length()-1,text2,text2.length()-1,dp);
       // return longestCommonSubsequenceshfiting(text1,text1.length(),text2,text2.length(),dp);
        return longestCommonSubsequencesDP(text1,text2);
        
    }
	

Memoization

 public int longestCommonSubsequence(String text1,int ind1, String text2,int ind2,int dp[][])
    {
        if(ind1<0 || ind2<0)
        {
            return 0;
        }
        if(dp[ind1][ind2]!=-1)
        {
            return dp[ind1][ind2];
        }
        if(text1.charAt(ind1) == text2.charAt(ind2))
            
        {
            return 1+longestCommonSubsequence(text1,ind1-1,text2,ind2-1,dp);
        }
        return dp[ind1][ind2]=Math.max(longestCommonSubsequence(text1,ind1,text2,ind2-1,dp),longestCommonSubsequence(text1,ind1-1,text2,ind2,dp));
    }
	
	
DP

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
