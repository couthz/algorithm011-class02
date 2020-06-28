public class Solution {
    public void moveZeroes(int[] nums) {
        int zero = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != 0) {
                nums[zero] = nums[i];
                if(zero!=i)
                    nums[i] = 0;
                zero ++;
            }
        }
    }
}
