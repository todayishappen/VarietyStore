package com.example.demo.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class LeeCode {

    //二维数组的查找，给定一个值和一个二维数据
    public boolean find(int target, int [][] array){
        int rows = array.length;
        if(rows == 0) return false;
        int cols = array[0].length;
        if(cols == 0) return false;
        int row = rows - 1;
        int col = 0;
        while(row >=0 && col <=cols-1){  //对角，二分法
            if(array[row][col] > target){
                row--;
            }
            if(array[row][col] < target){
                col++;
            }
            else{
                return true;
            }
        }
        return false;
    }

    //字符串的替换 给定一个字符串，替换空格为%20
    public String tihuan(String s){
        if(s == null || "".equals(s)){
            return s;
        }
        return s.replaceAll(" ","%20");
    }

    public String tihuan2(String s){
        StringBuilder sb = new StringBuilder();
        if(s == null || "".equals(s)){
            return s;
        }
        String[] strs = s.split("");
        for(String str : strs){
            if(str == " "){
                sb.append("%20");
            }
            else{
                sb.append(str);
            }
        }
        return sb.toString();
    }


    //打印链表到数组集合中，从尾到头返回ArrayList,给定一个链表ListNode
    public ArrayList<Integer> printForArr(ListNode listnode){
        ArrayList<Integer> list = new ArrayList<>();
        ListNode tmp = listnode;
        while(tmp != null){
            list.add(0, tmp.val);
            tmp = tmp.next;
        }
        return list;
    }

    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printForArr2(ListNode listnode){
        if(listnode != null){
            printForArr2(listnode.next);
            list.add(listnode.val);
        }
        return list;
    }

    //根据前序遍历和中序遍历返回二叉树
    public TreeNode chongzu(int pre[], int in[]){
        TreeNode root = new TreeNode(pre[0]);
        if(pre.length == 0 || in.length == 0){
            return null;
        }
            for(int i = 0; i < in.length; i++) {
             if(pre[0] == in[i]){
                 root.left = chongzu(Arrays.copyOfRange(pre,1, i+1),Arrays.copyOfRange(in, 0, i));
                 root.right = chongzu(Arrays.copyOfRange(pre,i+1, pre.length),Arrays.copyOfRange(in, i+1, in.length));
             }
            }
        return root;
    }

    //俩个栈实现队列（先进先出）
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node){
        stack1.push(node);
    }
    public int pop(){
        if(stack2.size() <= 0){
            while(stack1.size() != 0) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    //斐波那契
    public int fibonacii(int n) {
        if (n <= 1)
            return n;
        //return fibonacii(n-1) + fibonacii(n-2);
        int sum = 1;
        int one = 0;
        for (int i = 2; i <= n; i++) {
            sum = sum + one;
            one = sum - one;
        }
        return sum;
    }

     //寻找 旋转数组的最小数
    public int foundStateArray(int[] stateArray){
        if(stateArray.length<=0){
            return 0;
        }
        int low =0;
        int high = stateArray.length -1;
        int mid =0;
        while(low < high){
            if(stateArray[low] < stateArray[high]){
                return stateArray[low];
            }
            mid = low + (high - low)/2;
            if(stateArray[low] > stateArray[mid]){
                low = mid + 1;   //重要
            }
            else if(stateArray[high] > stateArray[mid]){
                high = mid;
            }
            else
                low ++;
        }
        return stateArray[low];
    }


}

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }

}


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}