class Solution {
    public int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            if (height[left] <= height[right]) {

                // Update maximum height on the left
                leftMax = Math.max(leftMax, height[left]);

                // Water trapped at left
                water += leftMax - height[left];

                left++;

            } else {

                // Update maximum height on the right
                rightMax = Math.max(rightMax, height[right]);

                // Water trapped at right
                water += rightMax - height[right];

                right--;
            }
        }

        return water;
    }
}