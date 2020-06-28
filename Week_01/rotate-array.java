class Solution {
    public:
    void rotate(vector<int>& nums, int k) {
        int length = nums.size();
        if(length==0||k==0)
            return;
        k = k % length;
        int count = 0;
        for (int start = 0; count < length; start++) {
            int pivot = start;
            int temp=nums[start];
            do {
                int at = (pivot+k)%length;

                int t = temp;
                temp = nums[at];
                nums[at] = t;

                pivot = at;
                count++;
            }while(pivot!=start);
        }
    }
};