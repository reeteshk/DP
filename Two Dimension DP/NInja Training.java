
/*
https://www.codingninjas.com/studio/problems/ninja-s-training_3621003?leftPanelTab=0
 */
import java.util.Arrays;
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {

        // Write your code here..
        int dp[][]=new int[n][4];
        for(int arr[]:dp)
        {
            Arrays.fill(arr,-1);
        }

        //return ninjaTrainingRecursion(n-1,3, points);
       // return ninjaTrainingMemoization(n-1,3, points,dp);
       //return ninjaTrainingDP(n,points);
       return ninjaTrainingDPOptimized(n,points);
    }
    public static int ninjaTrainingRecursion(int day,int last,int points[][])
    {
        if(day==0)
        {
            int maxi=0;
            for(int task=0;task<3;task++)
            {
                if(task!=last)
                {
                    maxi=Math.max(maxi,points[0][task]);
                }
            }
             return maxi;
        }

        int maxi=0;
        for(int task=0;task<3;task++)
        {
            if(task!=last)
            {
                    int point=points[day][task]+ninjaTrainingRecursion(day-1,task,points);
                    maxi=Math.max(maxi,point);
            }
            
        }
        return maxi;
       
    }

    public static int ninjaTrainingMemoization(int day,int last,int points[][],int dp[][])
    {
        if(day==0)
        {
            int maxi=0;
            for(int task=0;task<3;task++)
            {
                if(task!=last)
                {
                    maxi=Math.max(maxi,points[0][task]);
                }
            }
             return maxi;
        }

        if(dp[day][last]!=-1) return dp[day][last];

        int maxi=0;
        for(int task=0;task<3;task++)
        {
            if(task!=last)
            {
                    int point=points[day][task]+ninjaTrainingMemoization(day-1,task,points,dp);
                    maxi=Math.max(maxi,point);
            }
            
        }
        return dp[day][last]=maxi;
       
    }

    public static int ninjaTrainingDP(int n,int points[][])
    {

        int dp[][]=new int[n][4];
            dp[0][0]=Math.max(points[0][1],points[0][2]);
            dp[0][1]=Math.max(points[0][0],points[0][2]);
            dp[0][2]=Math.max(points[0][0],points[0][1]);
            dp[0][3]=Math.max(points[0][1],Math.max(points[0][2],points[0][0]));
        for(int day=1;day<n;day++)
        {
            for(int last=0;last<4;last++)
            {
            int maxi=0;
            dp[day][last]=0;
            for(int task=0;task<3;task++)
            {
                if(task!=last)
                {
                        int point=points[day][task]+dp[day-1][task];
                        maxi=Math.max(maxi,point);
                }
            }
          dp[day][last]=maxi;
            }
        }
        return dp[n-1][3];
    }

     public static int ninjaTrainingDPOptimized(int n,int points[][])
    {

            int dp[]=new int[4];
           
            dp[0]=Math.max(points[0][1],points[0][2]);
            dp[1]=Math.max(points[0][0],points[0][2]);
            dp[2]=Math.max(points[0][0],points[0][1]);
            dp[3]=Math.max(points[0][1],Math.max(points[0][2],points[0][0]));
        for(int day=1;day<n;day++)
        { 
            int curr[]=new int[4];
            for(int last=0;last<4;last++)
            {
            int maxi=0;
            curr[last]=0;
            for(int task=0;task<3;task++)
            {
                if(task!=last)
                {
                        int point=points[day][task]+dp[task];
                        curr[last]=Math.max(curr[last],point);
                }
            }
            }
            dp=curr;
        }
        return dp[3];
    }

}