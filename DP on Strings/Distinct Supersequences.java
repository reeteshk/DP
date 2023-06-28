/*

 115. Distinct Subsequences
Hard
5.6K
209
Companies
Given two strings s and t, return the number of distinct 
subsequences
 of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.

 */




class Solution {
    public int numDistinct(String s, String t) {
        
        int dp[][]=new int[s.length()][t.length()];
       // return numDistinctRecursion(s,t,s.length()-1,t.length()-1);
    //    for(int row[]:dp)
    //    {
    //        Arrays.fill(row,-1);
    //    }
    //     return numDistinctMemoization(s,t,s.length()-1,t.length()-1,dp);
     //return numDistinctDP(s,t);
     return numDistinctDPOptimized(s,t);
    }
    public static int numDistinctRecursion(String s, String t,int i,int j)
    {
         if(j<0)
        {
            return 1;
        }
        if(i<0)
        {
            return 0;
        }
       
        if(s.charAt(i)==t.charAt(j))
        {
            return numDistinctRecursion(s,t,i-1,j)+numDistinctRecursion(s,t,i-1,j-1);
        }
        return numDistinctRecursion(s,t,i-1,j);
    }
     public static int numDistinctMemoization(String s, String t,int i,int j,int dp[][])
    {
         if(j<0)
        {
            return 1;
        }
        if(i<0)
        {
            return 0;
        }
        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }       
        if(s.charAt(i)==t.charAt(j))
        {
            return dp[i][j]=numDistinctMemoization(s,t,i-1,j,dp)+numDistinctMemoization(s,t,i-1,j-1,dp);
        }
        return dp[i][j]=numDistinctMemoization(s,t,i-1,j,dp);
    }

    public static int numDistinctDP(String s,String t)
    {
        int dp[][]=new int[s.length()+1][t.length()+1];
        for(int i=0;i<=s.length();i++)
        {
            dp[i][0]=1;
        }
        //  for(int i=1;i<=t.length();i++)
        // {
        //     dp[0][i]=0;
        // }
        for(int i=1;i<=s.length();i++)
        {
            for(int j=1;j<=t.length();j++)
            {
                 if(s.charAt(i-1)==t.charAt(j-1))
             {
                 dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
             }
             else
             {
                     dp[i][j]=dp[i-1][j];
             }
            }
        }
        return dp[s.length()][t.length()];
    }
      public static int numDistinctDPOptimized(String s,String t)
    {
            int[] prev = new int[t.length() + 1];
            int[] current = new int[t.length() + 1];

            prev[0] = 1;

            for (int i = 1; i <= s.length(); i++) {
                current[0] = 1;
                for (int j = 1; j <= t.length(); j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        current[j] = prev[j - 1] + prev[j];
                    } else {
                        current[j] = prev[j];
                    }
                }
                int[] temp = prev;
                prev = current;
                current = temp;
            }

            return prev[t.length()];
    }
}