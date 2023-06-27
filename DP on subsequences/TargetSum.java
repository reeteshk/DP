class Solution {
    static int mod =(int)(Math.pow(10,9)+7);
    public int findTargetSumWays(int[] nums, int target) {
        int total=0;
        for(int i:nums)
        {
            total=total+i;
        }
        if(total-target<0) return 0;
        if((total-target)%2==1) return 0;

        int s2=(total-target)/2;
        int n=nums.length;
        int dp[][]=new int[n][s2+1];
        for(int row[]:dp)
        {
            Arrays.fill(row,-1);
        }
        //return TargetSumRecursion(n-1,s2,nums);
       // return TargetSumMemoization(n-1,s2,nums,dp);
       //return TargetSumDP(nums,s2);
       return TargetSumDPOptimized(nums,s2);
    }

    //Recursive Approach
    public static int TargetSumRecursion(int ind, int target, int[] arr)
    {
         if(ind == 0){
            if(target==0 && arr[0]==0)
                return 2;
            if(target==0 || target == arr[0])
                return 1;
            return 0;
        }
            
        int notTaken = TargetSumRecursion(ind-1,target,arr);
        
        int taken = 0;
        if(arr[ind]<=target)
            taken = TargetSumRecursion(ind-1,target-arr[ind],arr);
            
        return (notTaken + taken);
    }

    //Memoization Approach
        public static int TargetSumMemoization(int ind, int target, int[] arr,int[][] dp){

        if(ind == 0){
            if(target==0 && arr[0]==0)
                return 2;
            if(target==0 || target == arr[0])
                return 1;
            return 0;
        }
        
        if(dp[ind][target]!=-1)
            return dp[ind][target];
            
        int notTaken = TargetSumMemoization(ind-1,target,arr,dp);
        
        int taken = 0;
        if(arr[ind]<=target)
            taken = TargetSumMemoization(ind-1,target-arr[ind],arr,dp);
            
        return dp[ind][target]= (notTaken + taken);
    }
     
     
    //DP Approach
         public  static int TargetSumDP(int []num, int tar){
            int n = num.length;

            int[][] dp=new int[n][tar+1];
            
            if(num[0] == 0) dp[0][0] =2;  // 2 cases -pick and not pick
            else dp[0][0] = 1;  // 1 case - not pick
            
            if(num[0]!=0 && num[0]<=tar) dp[0][num[0]] = 1;  // 1 case -pick
            
            for(int ind = 1; ind<n; ind++){
                for(int target= 0; target<=tar; target++){
                    
                    int notTaken = dp[ind-1][target];
            
                    int taken = 0;
                        if(num[ind]<=target)
                            taken = dp[ind-1][target-num[ind]];
                
                    dp[ind][target]= (notTaken + taken)%mod;
                }
            }
            return dp[n-1][tar];
        }


        //DP Optimized Approach
        public  static int TargetSumDPOptimized(int []num, int tar){
            int n = num.length;

            int[] dp=new int[tar+1];
            
            if(num[0] == 0) dp[0] =2;  // 2 cases -pick and not pick
            else dp[0] = 1;  // 1 case - not pick
            
            if(num[0]!=0 && num[0]<=tar) dp[num[0]] = 1;  // 1 case -pick
            
            for(int ind = 1; ind<n; ind++){
                int curr[]=new int[tar+1];
                for(int target= 0; target<=tar; target++){
                    
                    int notTaken = dp[target];
            
                    int taken = 0;
                        if(num[ind]<=target)
                            taken = dp[target-num[ind]];
                
                    curr[target]= (notTaken + taken)%mod;
                }
                dp=curr;
            }
            return dp[tar];
        }
}