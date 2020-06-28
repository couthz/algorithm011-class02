class Solution {
    public int trap(int[] height) {
        if (height == null || height.length < 2) return 0;
        //维护一个单调非递增的栈。
        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // 找到两端之间最小的高度
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // 计算容积
                    water += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }
        return water;
    }
}