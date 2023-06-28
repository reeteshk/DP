
/*
72. Edit Distance
Medium
12.9K
150
Companies
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
Accepted
666.5K
Submissions
1.2M
Acceptance Rate
54.8%
 */
class Solution {
    public int minDistance(String word1, String word2) {
        
       // return minDistanceRecursion(word1,word2,word1.length()-1,word2.length()-1);
        // int dp[][]=new int[word1.length()][word2.length()];
        // for(int row[]:dp)
        // {
        //     Arrays.fill(row,-1);
        // }
        // return minDistanceMemoization(word1,word2,word1.length()-1,word2.length()-1,dp);

       // return minDistanceDP(word1,word2);
       return minDistaceDPOptimized(word1,word2);
    }
    public static int minDistanceRecursion(String word1,String word2,int i,int j)
    {
        if(i<0)
        {
            return j+1;
        }
        if(j<0)
        {
            return i+1;
        }

        if(word1.charAt(i)==word2.charAt(j))
        {
            return 0+minDistanceRecursion(word1,word2,i-1,j-1);
        }
        else
        {
            int delete=minDistanceRecursion(word1,word2,i-1,j);
            int insert=minDistanceRecursion(word1,word2,i,j-1);
            int replace=minDistanceRecursion(word1,word2,i-1,j-1);
            return 1+Math.min(delete,Math.min(insert,replace));
        }
    }
     public static int minDistanceMemoization(String word1,String word2,int i,int j,int dp[][])
    {
        if(i<0)
        {
             return j+1;
        }
        if(j<0)
        {
             return i+1;
        }
        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }
        if(word1.charAt(i)==word2.charAt(j))
        {
            return 0+minDistanceMemoization(word1,word2,i-1,j-1,dp);
        }
        else
        {
            int delete=minDistanceMemoization(word1,word2,i-1,j,dp);
            int insert=minDistanceMemoization(word1,word2,i,j-1,dp);
            int replace=minDistanceMemoization(word1,word2,i-1,j-1,dp);
            return dp[i][j]=1+Math.min(delete,Math.min(insert,replace));
        }
    }

    public static int minDistanceDP(String word1,String word2)
    {
        int dp[][]=new int[word1.length()+1][word2.length()+1];

        int m=word1.length();
        int n=word2.length();

        for(int i=0;i<=m;i++)
        {
            dp[i][0]=i;
        }

        for(int i=0;i<=n;i++)
        {
            dp[0][i]=i;
        }

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {

                if(word1.charAt(i-1)==word2.charAt(j-1))
                {
                    dp[i][j] =0+dp[i-1][j-1];
                }
                else
                {
                    int delete=dp[i-1][j];
                    int insert=dp[i][j-1];
                    int replace=dp[i-1][j-1];
                     dp[i][j]=1+Math.min(delete,Math.min(insert,replace));
                }

            }
        }
        return dp[m][n];
    }
    public static int minDistaceDPOptimized(String word1,String word2)
    {
        int prev[]=new int[word2.length()+1];

        int m=word1.length();
        int n=word2.length();

        

        for(int i=0;i<=n;i++)
        {
            prev[i]=i;
        }

        for(int i=1;i<=m;i++)
        {
            int curr[]=new int[word2.length()+1];
            curr[0]=i;
            for(int j=1;j<=n;j++)
            {

                if(word1.charAt(i-1)==word2.charAt(j-1))
                {
                    curr[j] =0+prev[j-1];
                }
                else
                {
                    int delete=prev[j];
                    int insert=curr[j-1];
                    int replace=prev[j-1];
                     curr[j]=1+Math.min(delete,Math.min(insert,replace));
                }

            }
            prev=curr;
        }
        return prev[n];
    }
}