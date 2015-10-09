import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Solution {
    public boolean isValid(String s) {
        char[] ch = new char[s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                ch[count] = s.charAt(i);
                count++;
            }
            else {
                if (count < 1) return false;
                if (s.charAt(i) - ch[count - 1] > 2) {
                    return false;
                }
                count--;
            }
        }
        if (count != 0) return false;
        return true;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        quickSort(0, nums.length - 1, nums);
        for (int i = 0 ; i < nums.length - 3; i++ ) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> tmp = threeSum2(nums, target, target - nums[i], i);
            for (int j = 0; j < tmp.size(); j++) {
                tmp.get(j).add(0, nums[i]);
            }
            ans.addAll(tmp);
        }
        return ans;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode point = head;
        if (point.next == null ){
            return null;
        }
        int count = 1;
        while (point.next != null) {
            point = point.next;
            count ++;
        }
        if (n > count) {
            return head;
        }
        if (n == count) {
            return head.next;
        }
        point = head;
        int i = 1;
        while (i < count - n) {
            point = point.next;
            i++;
        }
        point.next = point.next.next;
        return head;
    }
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<String>();
        String anstmp = "";
        LetterCombinationsDFS(digits, 0, ans, anstmp);
        return ans;
    }
    public void LetterCombinationsDFS(String digits, int index, List<String> ans, String anstmp) {
        if (index == digits.length() ) {
            if (anstmp != "") {
                ans.add(anstmp);
            }
            return;
        }
        int tmp = digits.charAt(index) - '2';
        if (tmp == -2) {
            LetterCombinationsDFS(digits, index + 1, ans, anstmp + " ");
            return;
        }
        int start = tmp * 3;
        if (tmp > 7) {
            start++;
        }
        int end = 3;
        if (tmp == 7 || tmp == 9) {
            end = 4;
        }
        for (int i = 0; i < end; i++) {
            LetterCombinationsDFS(digits, index + 1, ans, anstmp + (char) ('a' + (start + i)));
        }
    }
    public int threeSumClosest(int[] nums, int target) {
        quickSort(0, nums.length - 1, nums);
        int ans = nums[nums.length-1]+nums[nums.length-2]+ nums[nums.length-3];
        for (int i = 0; i< nums.length -2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                
                if (Math.abs(nums[i] + nums[j] + nums[k] - target) < Math.abs(ans - target)) {
                    ans = nums[i] + nums[j] + nums[k];
                }
                if (nums[i] + nums[j]+ nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        quickSort(0, nums.length - 1, nums);
        for (int i = 0 ; i < nums.length - 2; i++ ) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> tmp = twoSum2(nums, nums[i], i);
            ans.addAll(tmp);
        }
        return ans;
    }
    public List<List<Integer>> threeSum2(int[] nums, int target, int key) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        quickSort(0, nums.length - 1, nums);
        for (int i = key + 1 ; i < nums.length - 2; i++ ) {
            if (i > key + 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> tmp = twoSum2(nums, target, target - nums[i], i);
            //for (int j = 0; j < tmp.size(); j++) {
            //    tmp.get(j).add(0, nums[i]);
            //}
            ans.addAll(tmp);
        }
        return ans;
    }
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int[] keys = new int[nums.length];
        int[] ans = new int[2];
        for (int k = 0; k < nums.length; k++) {
            keys[k] = k + 1;
        }
        quickSort2Keys(0,nums.length - 1, nums, keys);
        while (i < j){
            if (nums[i] + nums[j] == target) {
                if (keys[i] < keys[j]) {
                    ans[0] = keys[i];
                    ans[1] = keys[j];
                } else {
                    ans[0] = keys[j];
                    ans[1] = keys[i];
                }
                return ans;
            } else if (nums[i] +nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }
    public List<List<Integer>> twoSum2(int[] nums, int target, int key) {
        int i = key + 1, j = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        while (i < j){
            if (i > key + 1 && nums[i] ==nums[i - 1]) {
                i++;
                continue;
            }
            if (nums[i] + nums[j] == target) {
                List list = new ArrayList<Integer>();
                list.add(nums[key]);
                list.add(nums[i]);
                list.add(nums[j]);
                ans.add(list);
                i++;
            } else if (nums[i] +nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }
    public List<List<Integer>> twoSum2(int[] nums, int target, int key) {
        int i = key + 1, j = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        while (i < j){
            if (i > key + 1 && nums[i] ==nums[i - 1]) {
                i++;
                continue;
            }
            if (nums[i] + nums[j] == target) {
                List list = new ArrayList<Integer>();
                list.add(nums[key]);
                list.add(nums[i]);
                list.add(nums[j]);
                ans.add(list);
                i++;
            } else if (nums[i] +nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }
    public void quickSort(int l, int r, int[] key1) {
        int i = l, j = r, mid, tmp;
        if (l < r) {
            mid = key1[(l + r) / 2];
        }
        else {
            return;
        }
        while (i < j) {
            while (key1[j] > mid) j--;
            while (key1[i] < mid) i++;
            if (i <=j) {
                tmp = key1[i]; key1[i] = key1[j]; key1[j] = tmp;
                i++; j--;
            }
        }
        if (i < r) quickSort(i, r, key1);
        if (j > l) quickSort(l, j, key1);
    }
    public void quickSort2Keys(int l, int r, int[] key1, int key2[]) {
        int i = l, j = r, mid =key1[(l+r)/2], tmp;
        while (i < j) {
            while (key1[j] > mid) j--;
            while (key1[i] < mid) i++;
            if (i <=j) {
                tmp = key1[i]; key1[i] = key1[j]; key1[j] = tmp;
                tmp = key2[i]; key2[i] = key2[j]; key2[j] = tmp;
                i++; j--;
            }
        }
        if (i < r) quickSort2Keys(i, r, key1, key2);
        if (j > l) quickSort2Keys(l, j, key1, key2);
    }
}
