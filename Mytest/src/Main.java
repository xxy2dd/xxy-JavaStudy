import java.util.*;

public class Main {


    public static void main(String[] args) {

    }
    public static int binarySearch(int[] nums,int left,int target){
        if(nums==null||nums.length==0){
            return -1;
        }
        int right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return right;
    }

}
