class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        boolean existed = false;
        for (int i = 0; i < nums.length; i++) {
            int chosen = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int sum = chosen + nums[j];
                if (target == sum) {
                    ans[0] = i;
                    ans[1] = j;
                    existed = true;
                    break;
                }
            }
            if (existed) break;
        }
        return ans;
    }
}