package com.sun.sort;

public class QuickSort {
	
	static int[] arr = {4,5,2};
	/**
	 *  快速排序
     *  是一种分而治之的思想，以某一个基准元素为标准，将集合中比该基准元素小的都放到其左侧，反之放到其右侧。
     *  这样我们就能够找到该基准元素在该集合中所处的位置。
     *  同理，我们就能够找到每一个元素在集合中恰当的位置。
     *  有点类似于二分查找。
     *  一般这个基准元素就是第一个元素。
     *  设置两个指针，一个设置在开头，一个设置末尾，从末尾开始找，找到一个比基本元素小的便停下来，然后
     *  从左侧开始找，直到找到一个比基准元素大的元素，停下来，二者元素进行交换，直到两个指针碰头，本次循环结束
     *  指针指向的位置就是该基准元素在集合中应该所处的位置
	 * @param arr 数组
	 * @param left 数组最低位指针
	 * @param right 数组最高位指针
	 */
	private static int partition(int[] arr, int left, int right) {
		int p=left;
		for(int i=left+1;i<=right;i++){
			if(arr[i]<arr[left]){
				p++;
				swap(arr,p,i);//5-2(index:1-2)
			}
		}
		swap(arr,left,p);
		return p;
	}
	
	static void sort(int[] arr,int left ,int right){
		if(left>=right){
			return;
		}
		int p = partition(arr,left,right);
		sort(arr,left,p-1);
		sort(arr,p+1,right);
	}

	private static void swap(int[] arr, int i, int j) {
		int k = arr[i];
		arr[i]=arr[j];
		arr[j]=k;
	}

	
	public static void main(String[] args) {
		sort(arr,0,arr.length-1);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	//	System.out.println(calSum(arr,arr.length));
	}
}
