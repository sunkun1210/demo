package com.sun.sort;

public class QuickSort3 {
    // 测试 QuickSort
    public static void main(String[] args) {
        int[] arr = {7,3,2,8,1,9,5,4,6};
        sort(arr,0,arr.length-1);
        for (Object i : arr) {
            System.out.print(i+" ");
        }
    }

    private static void sort(int[] arr, int l, int r){
        if (l>=r){
            return;
        }
        partition(arr, l, r);
    }

    private static void partition(int[] arr, int leftBound, int rightBound){
        //取右边界当轴
        int pivot=arr[rightBound];
        int left=leftBound;
        int right=rightBound-1;
        while(left<right){
            while (arr[left]<=pivot){
                left++;
            }
            while (arr[right]>=pivot){
                right--;
            }
            if (left<right){
                swap(arr,left,right);
            }
        }
        //把轴放到该放的正确位置上去
        swap(arr,left,rightBound);
    }



    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}