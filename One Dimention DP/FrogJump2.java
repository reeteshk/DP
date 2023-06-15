/*
Problem Statement:

This is a follow-up question to “Frog Jump” discussed in the previous article.
 In the previous question, the frog was allowed to jump either one or two steps at a time. 
 In this question, the frog is allowed to jump up to ‘K’ steps at a time. 
If K=4, the frog can jump 1,2,3, or 4 steps at every index.

*/

//recursion TC O((n)xK) SC O(n)+O(N)

static int solveUtil(int ind, int[] height, int k){
    if(ind==0) return 0;
    
    
    int mmSteps = Integer.MAX_VALUE;
        
    for(int j=1;j<=k;j++){
        if(ind-j>=0){
       int jump = solveUtil(ind-j, height, k)+ Math.abs(height[ind]- 
       height[ind-j]);
            mmSteps= Math.min(jump, mmSteps);
        }
    }
    return  mmSteps;
    
}

//---Memoization

static int solveUtil(int ind, int[] height, int[] dp, int k){
    if(ind==0) return 0;
    if(dp[ind]!=-1) return dp[ind];
    
    int mmSteps = Integer.MAX_VALUE;
        
    for(int j=1;j<=k;j++){
        if(ind-j>=0){
       int jump = solveUtil(ind-j, height, dp, k)+ Math.abs(height[ind]- 
       height[ind-j]);
            mmSteps= Math.min(jump, mmSteps);
        }
    }
    return dp[ind]= mmSteps;
    
}



//Dp

static int solveUtil(int n, int[] height, int[] dp, int k){
    dp[0]=0;
    for(int i=1;i<n;i++){
        int mmSteps = Integer.MAX_VALUE;
        
        for(int j=1;j<=k;j++){
            if(i-j>=0){
                int jump = dp[i-j]+ Math.abs(height[i]- height[i-j]);
                mmSteps= Math.min(jump, mmSteps);
            }
        }
        dp[i]= mmSteps;
    }
    return dp[n-1];
    
}

//Optimized Dp is not possible as we will be requiring the k variable in every recursive call.