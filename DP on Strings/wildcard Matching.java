/*
4. Wildcard Matching
Hard
7.2K
298
Companies
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.

*/

class Solution {
    public boolean isMatch(String s, String p) {
        
        // return isMatchRecursion(s,p,s.length()-1,p.length()-1);
        // int dp[][]=new int[s.length()][p.length()];
        // for(int row[]:dp)
        // {
        //     Arrays.fill(row,-1);
        // }
        // return isMatchMemoization(s,p,s.length()-1,p.length()-1,dp);
       // return isMatchDP(s,p);
       return isMatchDPSpaceOptimization(s,p);
    }
    public static boolean isMatchRecursion(String s,String p,int i,int j)
    {
        if(i<0 && j<0) return true;
        if(i>=0 && j<0) return false;
        if(i<0 && j>=0)
        {
            while(j>=0)
            {
                if(p.charAt(j)!='*')
                {
                    return false;
                }
                j--;
            }
            return true;
        }
        if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')
        {
            return isMatchRecursion(s,p,i-1,j-1);
        }
        if(p.charAt(j)=='*')
        {
            return isMatchRecursion(s,p,i-1,j) || isMatchRecursion(s,p,i,j-1);
        }
        return false;
    }

     public static boolean isMatchMemoization(String s,String p,int i,int j,int dp[][])
    {
        if(i<0 && j<0) return true;
        if(i>=0 && j<0) return false;
        if(i<0 && j>=0)
        {
            while(j>=0)
            {
                if(p.charAt(j)!='*')
                {
                    return false;
                }
                j--;
            }
            return true;
        }
        if(dp[i][j]!=-1)
        {
            if(dp[i][j]==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')
        {
            boolean val=isMatchMemoization(s,p,i-1,j-1,dp);
              if(val==true)
            {
                dp[i][j]=1;
            }
            else
            {
                dp[i][j]=0;
            }
            return val;
        }
        if(p.charAt(j)=='*')
        {
            boolean val= isMatchMemoization(s,p,i-1,j,dp) || isMatchMemoization(s,p,i,j-1,dp);
            if(val==true)
            {
                dp[i][j]=1;
            }
            else
            {
                dp[i][j]=0;
            }
            return val;
        }
        return false;
    }

    public static boolean isMatchDP(String s,String p)
    {
        int m=s.length();
        int n=p.length();
        boolean dp[][]=new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int j=1;j<=s.length();j++)
        {
            dp[j][0]=false;
        }

        for(int i=0;i<=p.length();i++)
        {
            boolean flag=true;
            for(int a=1;a<=i;a++)
            {
                if(p.charAt(a-1)!='*')
                {
                    flag=false;
                    break;
                }
            }
            dp[0][i]=flag;
        }

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
            if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?')
             {
                 dp[i][j]=dp[i-1][j-1];
            }
             else if(p.charAt(j-1)=='*')
            {
            dp[i][j]= dp[i-1][j] || dp[i][j-1];
            }
            }
        }
        return dp[m][n];
    }

    public static boolean isMatchDPSpaceOptimization(String s,String p)
    {
        int m=s.length();
        int n=p.length();
        boolean dp[]=new boolean[p.length()+1];
        dp[0]=true;
        // for(int j=1;j<=s.length();j++)
        // {
        //     dp[j][0]=false;
        // }

        for(int i=0;i<=p.length();i++)
        {
            boolean flag=true;
            for(int a=1;a<=i;a++)
            {
                if(p.charAt(a-1)!='*')
                {
                    flag=false;
                    break;
                }
            }
            dp[i]=flag;
        }

        for(int i=1;i<=m;i++)
        {
            boolean curr[]=new boolean[n+1];
            for(int j=1;j<=n;j++)
            {
            if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?')
             {
                 curr[j]=dp[j-1];
            }
             else if(p.charAt(j-1)=='*')
            {
            curr[j]= dp[j] || curr[j-1];
            }
            }
            dp=curr;
        }
        return dp[n];

    }
}