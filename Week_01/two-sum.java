class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            Integer pivot = map.get(target-nums[i]);
            if(pivot!=null) {
                return new int[]{i, pivot};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
