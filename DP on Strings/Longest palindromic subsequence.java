// Longest Palindromic Subsequence - LeetCodeclass 

// Input: s = "bbbab"
// Output: 4
// Explanation: One possible longest palindromic subsequence is "bbbb".

// Input: s = "cbbd"
// Output: 2
// Explanation: One possible longest palindromic subsequence is "bb".


class Solution {
    public int longestPalindromeSubseq(String s) {
        
        String s2="";
        for(int i=s.length()-1;i>=0;i--)
        {
            s2=s2+s.charAt(i);
        }
        int dp[][]=new int [s.length()][s2.length()];
        for(int arr[]:dp)
        {
            Arrays.fill(arr,-1);
        }

        //return longestPalindromeSubseqRecursion(s,s2,s.length()-1,s2.length()-1);
        //return longestPalindromeSubseqMemoization(s,s2,s.length()-1,s2.length()-1,dp);
        //return longestPalindromeSubseqDP(s,s2);
        return longestPalindromeSubseqDPOptimized(s,s2);
    }
    public static int longestPalindromeSubseqRecursion(String s,String s2,int i,int j)
    {
        if(i<0 || j<0)
        {
            return 0;
        }
        if(s.charAt(i)==s2.charAt(j))
        {
            return 1+longestPalindromeSubseqRecursion(s,s2,i-1,j-1);
        }
        return Math.max(longestPalindromeSubseqRecursion(s,s2,i-1,j),longestPalindromeSubseqRecursion(s,s2,i,j-1));
    }

    public static int longestPalindromeSubseqMemoization(String s,String s2,int i,int j,int dp[][])
    {
        if(i<0 || j<0)
        {
            return 0;
        }
        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }
        if(s.charAt(i)==s2.charAt(j))
        {
            return 1+longestPalindromeSubseqMemoization(s,s2,i-1,j-1,dp);
        }
        return dp[i][j]=Math.max(longestPalindromeSubseqMemoization(s,s2,i-1,j,dp),longestPalindromeSubseqMemoization(s,s2,i,j-1,dp));
    }
    public static int longestPalindromeSubseqDP(String s,String s2)
    {
        int dp[][]=new int[s.length()+1][s2.length()+1];

        for(int i=0;i<s.length()+1;i++)
        {
            dp[i][0]=0;
        }
        for(int i=0;i<s2.length()+1;i++)
        {
            dp[0][i]=0;
        }
        for(int i=1;i<=s.length();i++)
        {
            for(int j=1;j<=s2.length();j++)
            {
                if(s.charAt(i-1)==s2.charAt(j-1))
                {
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else
                {
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
                
            }
        }
        return dp[s.length()][s2.length()];
    }

     public static int longestPalindromeSubseqDPOptimized(String s,String s2)
    {
        int dp[]=new int[s2.length()+1];

        for(int i=0;i<s2.length()+1;i++)
        {
            dp[i]=0;
        }
        for(int i=1;i<=s.length();i++)
        {
            int curr[]=new int[s2.length()+1];
            for(int j=1;j<=s2.length();j++)
            {
                if(s.charAt(i-1)==s2.charAt(j-1))
                {
                    curr[j]=1+dp[j-1];
                }
                else
                {
                    curr[j]=Math.max(dp[j], curr[j-1]);
                }
                
            }
            dp=curr;
        }
        return dp[s2.length()];
    }
}