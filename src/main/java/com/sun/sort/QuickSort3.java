package com.sun.sort;

public class QuickSort3 {
    // 测试 QuickSort
    public static void main(String[] args) {
        int[] arr ={4,6};
        sort(arr,0,arr.length-1);
        print(arr);
    }

    private static void sort(int[] arr, int leftBound, int rightBound){
        if (leftBound>=rightBound){
            return;
        }
        int mid=partition(arr, leftBound, rightBound);
        sort(arr,leftBound,mid-1);
        sort(arr,mid+1,rightBound);
    }

    private static int  partition(int[] arr, int leftBound, int rightBound){
        //取右边界当轴
        int pivot=arr[rightBound];
        int left=leftBound;
        int right=rightBound-1;
        while(left<=right){
            while (left<=right&&arr[left]<=pivot) left++;
            while (left<=right&&arr[right]>pivot) right--;
            if (left<right)  swap(arr,left,right);
        }
        //把轴放到该放的正确位置上去 (轴一定比左边的小 不然left++)
        swap(arr,left,rightBound);
        return left; //返回轴的位置
    }

    static void print(int[] arr){
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}