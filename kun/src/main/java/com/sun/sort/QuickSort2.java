package com.sun.sort;

public class QuickSort2 {
    static int[] arr = {9,8,7,6,9,4,13};

    /***
     * @param arr 对arr[l...r]部分进行partition操作
     * @return 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     */
    private static int partition(int[] arr, int l, int r){
    	/***
    	 *  arr[l+1...p] < valueP ; arr[p+1...i) >valueP  一开始p=l 这两个区间都不存在 
    	 *  这样整个程序在初始的情况下 都满足这样一个条件 一直到该方法结束我们数组一直保持这个性质
    	 */
        int p = l;
        int valueP=arr[p];
        for( int i = l + 1 ; i <= r ; i ++ ){
            if( arr[i]< valueP ){//如果右边的比左边小——->把右边的移动到左边去          
                swap(arr, p+1, i);//arr[l+1...p]最后一个元素和i(正在考察的这个元素、)
                p++;//这时p就要++(arr[l+1...p]的范围扩充了一个)之后再i++...
            }
        }

        swap(arr, l, p);//这样:把valueP移到p位置上
        return p;
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(int[] arr, int l, int r){
        if( l >= r ){
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p-1 );
        sort(arr, p+1, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    // 测试 QuickSort
    public static void main(String[] args) {
        sort(arr,0,arr.length-1);
        for (Object i : arr) {
            System.out.print(i+" ");
        }
    }
}