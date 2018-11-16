package sscf.hadoop.mr.test;/**
 * Created by lenovo on 2018/11/9.
 */

/**
 * @ClassName Test
 * @Description
 * @Author lenovo
 * @Date 2018/11/9 10:02
 **/
public class Test {
    public static void main(String[] args) {
        int i = twoFen(4);
        System.out.println(i);
    }

    //将字符串转化为数组
    public static void strToArray(){
        String str = "helloworld";
        char[] data = str.toCharArray();// 将字符串转为数组
        for (int x = 0; x < data.length; x++) {
            System.out.print(data[x] + "  ");
            data[x] -= 32;
            System.out.print(data[x] + "  ");
        }
        System.out.println(new String(data));
    }

    //实现倒序
    public static int[] reverse(int[] arr){
        int[] result = new int[arr.length];
        for (int i = 0,j=result.length-1; i < arr.length; i++,j--) {
            result[j] = arr[i];
        }
        return result;
    }


    public static int twoFen(int a ){
        int arr[]={1,3,4,5,6,7,8};
        int start=0;
        int end=arr.length-1;
        while (start<=end){
            int middle=(start+end)/2;
            if(a==arr[middle]){
                return middle;
            }else if (a<arr[middle]){
                end=middle-1;
            }else {
                start=middle+1;
            }
        }
        return  -1;
    }
    public static void mao(){
        int arr[] = {26,15,29,66,99,88,36,77,111,1,6,8,8};
        for (int i=0;i<arr.length-1;i++){//排序次数
            for (int j=0;j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp= arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }

        for(int a = 0; a < arr.length;a++) {
            System.out.println(arr[a] + "\t");
        }
    }

    //冒泡排序
    public static void maopao(){
        int arr[] = {26,15,29,66,99,88,36,77,111,1,6,8,8};
        for(int i=0;i < arr.length-1;i++) {//外层循环控制排序趟数
            for(int j=0; j< arr.length-i-1;j++) {
                //内层循环控制每一趟排序多少次
                // 把小的值交换到前面
                if (arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.print("第"+(i+1)+"次排序结果：");
            //列举每次排序的数据
            for(int a=0;a<arr.length;a++) {
                System.out.print(arr[a] + "\t");
            }
            System.out.println("");
        }
        System.out.println("最终排序结果：");
        for(int a = 0; a < arr.length;a++) {
            System.out.println(arr[a] + "\t");
        }
    }
}
