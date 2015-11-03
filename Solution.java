import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Solution {
    //--------------------
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length ==0 || matrix[0].length == 0) return 0;
        int[][] upleft = new int[matrix.length + 1][matrix[0].length + 1];
        int[][] opt = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                upleft[i + 1][j + 1] = upleft[i + 1][j] + upleft[i][j + 1] - upleft[i][j];
                if (matrix[i][j] == '1') {
                    upleft[i + 1][j + 1]++;
                }
            }
        }
        int maxans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    for (int s = i; s >=0; s--) {
                        for (int t = j; t >= 0; t--) {
                            if (upleft[i + 1][j + 1] - upleft[s][j + 1] - upleft[i + 1][t] + upleft[s][t]
                                == (i - s + 1) * (j - t + 1)) {
                                opt[i][j] = max((i - s + 1) * (j - t + 1), opt[i][j]);
                            } else {
                                break;
                            }
                        }
                    }
                }
                maxans = max(maxans, opt[i][j]);
            }
        }
        return maxans;
    }
    //--------------------
    public int minDistance(String word1, String word2) {
        int[][] opt = new int[word1.length() + 1][word2.length() + 1];
        for (int j = 0; j < word2.length(); j++) {
            opt[0][j + 1] = j + 1;
        }
        for (int j = 0; j < word1.length(); j++) {
            opt[j + 1][0] = j + 1;
        }
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                //delete
                opt[i + 1][j + 1] = opt[i][j + 1] + 1;
                //add
                opt[i + 1][j + 1] = min (opt[i + 1][j] + 1, opt[i + 1][j + 1]);
                //replace
                opt[i + 1][j + 1] = min (opt[i][j] + 1, opt[i + 1][j + 1]);
                if (word1.charAt(i) == word2.charAt(j)) {
                    opt[i + 1][j + 1] = min (opt[i][j], opt[i + 1][j + 1]);
                }
            }
        }
        return opt[word1.length()][word2.length()];
    }
	//--------------------
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] opt = new int[n + 1];
        opt[0] = 1; opt[1] = 1;
        for (int i = 2; i <=n; i++) {
            opt[i] = opt[i - 1] + opt[i - 2];
        }
        return opt[n];
    }
	//--------------------
	public int minPathSum(int[][] grid) {
        int[][] opt = new int[grid.length + 1][grid[0].length + 1];
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid[0].length; j++) {
                if (i != 1 && j != 1) {
                    opt[i][j] = min(opt[i - 1][j], opt[i][j - 1]) + grid[i - 1][j - 1];
                }
                else
                {
                    opt[i][j] = opt[i - 1][j] + opt[i][j - 1] + grid[i - 1][j - 1];
                }
            }
        }
        return opt[grid.length][grid[0].length];
    }
	//--------------------
    public int numTrees(int n) {
        int[] opt = new int[n + 1];
        opt[1] = 1; 
        opt[0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                opt[i] += opt[j - 1] * opt[i - j];
            }
        }
        return opt[n];
    }
	//--------------------
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] opt = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        opt[1][0] = 1;
        for (int i = 1; i <= obstacleGrid.length; i++) {
            for (int j = 1; j <= obstacleGrid[0].length; j++) {
                if (obstacleGrid[i - 1][j - 1] != 1) {
                    opt[i][j] = opt[i - 1][j] + opt[i][j - 1];
                }
            }
        }
        return opt[obstacleGrid.length][obstacleGrid[0].length];
    }
	//--------------------
    public int uniquePaths(int m, int n) {
        if (n == 0 || m == 0) return 0;
        int[][] opt = new int[m + 1][n + 1];
        opt[1][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                opt[i][j] = opt[i - 1][j] + opt[i][j - 1];
            }
        }
        return opt[m][n];
    }
	//--------------------
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) 
            return 0;
        int[] opt = new int[nums.length + 1];
        int maxans = nums[0];
        for (int i = 0; i < nums.length; i++) {
            opt[i + 1] = max(opt[i], 0) + nums[i];
            maxans = max(maxans, opt[i + 1]);
        }
        return maxans;
    }
	//--------------------
	public boolean isMatch(String s, String p) {
        if (s.length() == 0) {
            if (p.length() == 0) return true;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        boolean[] opt = new boolean[s.length() + 1];
        int count = 0;
        opt[0] = true;
        for ( int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                for (int j = count; j < s.length(); j++) {
                    if (!opt[j + 1]) {
                        opt[j + 1] = opt[j];
                    }
                }
            } else if (p.charAt(i) == '?') {
                for (int j = s.length(); j > count; j--) {
                    opt[j] = opt[j - 1];
                }
                if (s.length() <= count) {
                    return false;
                }
                count++;
            } else {
                for (int j = s.length(); j > count; j--) {
                    if (s.charAt(j - 1) == p.charAt(i)) {
                        opt[j] = opt[j - 1];
                    }
                    else {
                        opt[j] = false;
                    }
                }
                if (s.length() <= count) {
                    return false;
                }
                count++;
            }
        }
        return opt[s.length()];
    }
	//--------------------
	 public int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] opt = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && i - opt[i-1] - 1 >= 0 && s.charAt(i - opt[i-1] - 1) == '(') {
                opt[i] = opt[i-1] + 2;
                if (i - opt[i-1] - 1 > 0) {
                    opt[i] += opt[i - opt[i-1] - 2];
                }           
            }
        }
        int maxnum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (maxnum < opt[i]) {
                maxnum = opt[i];
            }
        }
        return maxnum;
    }
    //--------------------
    public int removeElement(int[] nums, int val) {
        int count = 1, non = 0;
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[non] = nums[i];
                non++;
            }
        }
        return non;
    }
    //--------------------
    public int removeDuplicates(int[] nums) {
        int count = 1, non = 1;;
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[non] = nums[i];
                count++;
                non++;
            }else {count ++;}
        }
        return non;
    }
    //--------------------
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ans = null, tail = null, p1 = null, p2 = null, p3 = null;
        int count = 1;
        tail = head;
        while (count < k){
            if (tail !=null && tail.next != null) {
                tail = tail.next;
            } else {
                break;
            }
            count++;
        }
        if (count != k || head == null || k == 1) {
            return head;
        }
        ans = tail;
        p1 = head;
        p2 = head;
        head = head.next;
        while (head != tail) {
            p3 = head.next;
            head.next = p2;
            p2 = head;
            head =  p3;
        }
        p3 = head.next;
        head.next = p2;
        head = p3;
        while (true) {
            count = 1;
            tail = head;
            while (count < k){
                if (tail !=null && tail.next != null) {
                    tail = tail.next;
                } else {
                    break;
                }
                count++;
            }
            if (count != k ) {
                p1.next = head;
                break;
            }
            //reverse
            
            System.out.println(tail.val);
            p1.next = tail;
            p1 = head;
            p2 = head;
            head = head.next;
            while (head != tail) {
                p3 = head.next;
                head.next = p2;
                p2 = head;
                head =  p3;
            }
            p3 = head.next;
            head.next = p2;
            head = p3;
        }
        return ans;
    }
    //--------------------
    public ListNode swapPairs(ListNode head) {
        ListNode p1,p2,pointer=null;
        p1 = head;
        if (p1 != null && p1.next !=null){
            p2 = p1.next;
            ListNode tmp = p2.next;
            p2.next = p1;
            p1.next = tmp;
            head = p2;
            pointer = p1;
            p1 = p1.next;
        }
        while (p1 != null && p1.next !=null){
            p2 = p1.next;
            pointer.next = p2;
            ListNode tmp = p2.next;
            p2.next = p1;
            p1.next = tmp;
            pointer = p1;
            p1 = p1.next;
        }
        return head;
    }
    //--------------------
    public ListNode mergeKLists(ListNode[] lists) {
        //build Min Heap
        ListNode[] heap = new ListNode[lists.length + 1];
        int heapSize = 0;
        for (int i = 0; i< lists.length; i++) {
            if (lists[i] != null) {
                insertMinHeap(heap, heapSize + 1, heapSize + 1, lists[i]);
                heapSize ++;
            }
        }
        //buid list
        ListNode ans = null, pointer = null;
        System.out.println(heapSize);
        if (heapSize > 0) {
            ans = heap[1];
            pointer = heap[1];
            if (heap[1].next != null) {
                insertMinHeap(heap, 1, heapSize, heap[1].next);
            } else {
                insertMinHeap(heap, 1, heapSize - 1, heap[heapSize]);
                heapSize--;
            }
            
        } else {
            return null;
        }
        while (heapSize > 0) {
            pointer.next = heap[1];
            pointer = pointer.next;
            if (heap[1].next != null) {
                insertMinHeap(heap, 1, heapSize, heap[1].next);
            } else {
                insertMinHeap(heap, 1, heapSize - 1, heap[heapSize]);
                heapSize--;
            }
        }
        return ans;
    }
    private void insertMinHeap(ListNode[] minHeap, int node, int nodeNum, ListNode val) {
        minHeap[node] = val;
        upMinHeap(minHeap, node);
        
        downMinHeap(minHeap, node, nodeNum);
    }
    private void upMinHeap(ListNode[] minHeap, int node){
        if (node == 1) {
            return;
        }
        if (minHeap[node / 2].val <= minHeap[node].val) {
            return;
        }
        ListNode temp = minHeap[node];
        minHeap[node] = minHeap[node / 2];
        minHeap[node / 2] = temp;
        upMinHeap(minHeap, node/2);
    }
    private void downMinHeap(ListNode[] minHeap, int node, int nodeNum) {
        if (node * 2 > nodeNum) {
            return;
        }
        int temp = node * 2;
        if (node *2 + 1 <= nodeNum && minHeap[node * 2 + 1].val < minHeap[temp].val) {
            temp = node * 2 + 1;
        }
        if (minHeap[temp].val < minHeap[node].val){
            ListNode tmp = minHeap[temp];
            minHeap[temp] = minHeap[node];
            minHeap[node] = tmp;
            downMinHeap(minHeap, temp, nodeNum);
        }
    }
    //---------------------
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        parenthesisGenerator(ans, "", 0, n);
        return ans;
    }
    private void parenthesisGenerator(List<String> l, String s, int inStack, int outStack) {
        if (outStack == 0 && inStack ==0){
            l.add(s);
        }
        if (outStack != 0 ) {
            parenthesisGenerator(l, s + "(", inStack + 1, outStack - 1);
        }
        if (inStack != 0 ){
            parenthesisGenerator(l, s + ")", inStack - 1, outStack);
        }
        return;
    }
    //--------------
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pointer = null,header = null ,waver = null;
        if (l1 != null) {
                header = l1;
        }
        else if(l2 != null){
            header = l2;
        }
        if (l2 != null && l2.val < header.val)
        {
            header = l2;
        }
        if (header == null ) return null;
        if (l1 == header && l1 != null) {
                l1 = l1.next;
            }
            else {
                l2 = l2.next;
            }
        waver = header;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                pointer = l1;
            }
            else if(l2 != null){
                pointer = l2;
            }
            if (l2 != null && l2.val < pointer.val)
            {
                pointer = l2;
            }
            if (l1 == pointer && l1 != null) {
                l1 = l1.next;
            }
            else {
                l2 = l2.next;
            }
            waver.next = pointer;
            waver = waver.next;
        }
        return header;
    }
    //------------
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
    //-----------
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
    //-----------
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
    //-----------
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<String>();
        String anstmp = "";
        LetterCombinationsDFS(digits, 0, ans, anstmp);
        return ans;
    }
    //-----------
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
    //-----------
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
    //-----------
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
    //-----------
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
    //-----------
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
	//---------------------
    private int max(int a, int b ) {
        if (a > b ) return a;
        return b;
    }
	private int min(int a, int b ) {
        if (a < b ) return a;
        return b;
    }
}
