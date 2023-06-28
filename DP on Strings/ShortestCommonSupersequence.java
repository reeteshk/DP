/*

1092. Shortest Common Supersequence
Hard
4K
59
Companies
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"
 

Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.

*/

class Solution {
  public String shortestCommonSupersequence(String str1, String str2) {
      
      int n=str1.length();
      int m=str2.length();
      int dp[][]=new int[n+1][m+1];
      for(int i=0;i<=n;i++)
      {
        dp[i][0]=0;
      }
      for(int i=0;i<=m;i++)
      {
        dp[0][i]=0;
      }

      for(int i=1;i<=n;i++)
      {
        for(int j=1;j<=m;j++)
        {
          if(str1.charAt(i-1)==str2.charAt(j-1))
          {
            dp[i][j]=1+dp[i-1][j-1];
          }
          else
          {
            dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
          }
        }
      }

      int i=n;
      int j=m;
      String ans="";
      while(i>0 && j>0)
      {
        if(str1.charAt(i-1)==str2.charAt(j-1))
        {
          ans+=str1.charAt(i-1);
          i--;
          j--;
        }
        else if(dp[i-1][j] > dp[i][j-1])
        {
          ans+=str1.charAt(i-1);
          i--;
        }
        else
        {
          ans+=str2.charAt(j-1);
          j--;
        }
      }

      while(i>0)
      {
        ans+=str1.charAt(i-1);
        i--;
      }
       while(j>0)
      {
        ans+=str2.charAt(j-1);
        j--;
      }

      String f="";
      for(int q=ans.length()-1;q>=0;q--)
      {
        f=f+ans.charAt(q);
      }
      return f;
  }
}