// LCS 

// 1143. Longest Common Subsequence
// Medium
// 10K
// 117
// Companies
// Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

// A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

// For example, "ace" is a subsequence of "abcde".
// A common subsequence of two strings is a subsequence that is common to both strings.

 

// Example 1:

// Input: text1 = "abcde", text2 = "ace" 
// Output: 3  
// Explanation: The longest common subsequence is "ace" and its length is 3.
// Example 2:

// Input: text1 = "abc", text2 = "abc"
// Output: 3
// Explanation: The longest common subsequence is "abc" and its length is 3.
// Example 3:

// Input: text1 = "abc", text2 = "def"
// Output: 0
// Explanation: There is no such common subsequence, so the result is 0.


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        int dp[][]=new int[text1.length()][text2.length()];
        for(int row[]:dp)
        {
            Arrays.fill(row,-1);
        }
       //return lcsRecursion(text1,text2,text1.length()-1,text2.length()-1);
      // return lcsMemoization(text1,text2,text1.length()-1,text2.length()-1,dp);
     // return lcsDP(text1,text2);
      return lcsDPOptimized(text1,text2);
    }

    //Recursive Approach
    public static int lcsRecursion(String text1, String text2,int ind1,int ind2)
    {
        if(ind1<0 || ind2<0)
        {
            return 0;
        }
        if(text1.charAt(ind1)==text2.charAt(ind2))
        {
            return 1+lcsRecursion(text1,text2,ind1-1,ind2-1);
        }
        return Math.max(lcsRecursion(text1,text2,ind1,ind2-1),lcsRecursion(text1,text2,ind1-1,ind2));
    }


    //Memoization Approach
    public static int lcsMemoization(String text1, String text2,int ind1,int ind2,int dp[][])
    {
        if(ind1<0 || ind2<0)
        {
            return 0;
        }
        if(dp[ind1][ind2]!=-1)
        {
            return dp[ind1][ind2];
        }
        if(text1.charAt(ind1)==text2.charAt(ind2))
        {
            return 1+lcsMemoization(text1,text2,ind1-1,ind2-1,dp);
        }
        return dp[ind1][ind2]=Math.max(lcsMemoization(text1,text2,ind1,ind2-1,dp),lcsMemoization(text1,text2,ind1-1,ind2,dp));
    }


    //DP Approach
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
          for(int ind1=1;ind1<=text1.length();ind1++)
          {
              for(int ind2=1;ind2<=text2.length();ind2++)
              {

                if(text1.charAt(ind1-1)==text2.charAt(ind2-1))
                {
                    dp[ind1][ind2]=1+dp[ind1-1][ind2-1];
                }
                else
                {
                    dp[ind1][ind2]=Math.max(dp[ind1][ind2-1],dp[ind1-1][ind2]); 
                }
                    
              }
          }
          return dp[m][n];
    }


    //DP Approach Optimized
     public static int lcsDPOptimized(String text1,String text2)
    {
          int dp[]=new int[text2.length()+1];

            int m=text1.length();
            int n=text2.length();
        
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
                }
                else
                {
                    curr[ind2]=Math.max(curr[ind2-1],dp[ind2]); 
                }
                    
              }
              dp=curr;
          }
          return dp[n];
    }
    
}