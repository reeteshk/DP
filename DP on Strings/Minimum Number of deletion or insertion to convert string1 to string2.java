// Minimum number of deletions and insertions.
// EasyAccuracy: 65.29%Submissions: 32K+Points: 2
// Lamp
// Looking for a Way to Crack the Best Product Companies?! Try THIS GFG Course !

// Given two strings str1 and str2. The task is to remove or insert the minimum number of characters from/in str1 so as to transform it into str2. It could be possible that the same character needs to be removed/deleted from one point of str1 and inserted to some another point.

// Example 1:

// Input: str1 = "heap", str2 = "pea"
// Output: 3
// Explanation: 2 deletions and 1 insertion
// p and h deleted from heap. Then, p is 
// inserted at the beginning One thing to 
// note, though p was required yet it was 
// removed/deleted first from its position 
// and then it is inserted to some other 
// position. Thus, p contributes one to the 
// deletion_count and one to the 
// insertion_count.
// Example 2:

// Input : str1 = "geeksforgeeks"
// str2 = "geeks"
// Output: 8
// Explanation: 8 deletions
 

// Your Task:
// You don't need to read or print anything. Your task is to complete the function minOperations() which takes both strings as input parameter and returns the minimum number of operation required.

// Expected Time Complexity: O(|str1|*|str2|)
// Expected Space Complexity: O(|str1|*|str2|)

// Constraints:
// 1 ≤ |str1|, |str2| ≤ 1000
// All the characters are lower case English alphabets




  
// } Driver Code Ends


//User function Template for Java

class Solution {
    public int minDistance(String word1, String word2) {
        int dp[][]=new int[word1.length()][word2.length()];
        for(int row[]:dp)
        {
            Arrays.fill(row,-1);
        }
       //return word1.length()+word2.length()-2*lcsRecursion(word1,word2,word1.length()-1,word2.length()-1);
    //return word1.length()+word2.length()-2*lcsMemoization(word1,word2,word1.length()-1,word2.length()-1,dp);
      //return word1.length()+word2.length()-2*lcsDP(word1,word2);
      return word1.length()+word2.length()-2*lcsDPOptimized(word1,word2);
    }
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