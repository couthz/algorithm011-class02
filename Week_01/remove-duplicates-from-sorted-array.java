class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0)
            return 0;
        int pivot = nums[0];
        int index = 0;
        int res = 1;
        for(int i=1; i<nums.length; i++) {
            if(nums[i]!= pivot) {
                res++;
                nums[++index] = nums[i];
                pivot = nums[i];
            }
        }
        return res;
    }
}