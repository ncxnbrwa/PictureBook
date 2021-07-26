package test.paixu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by ncx on 2021/3/22
 */
public class PaixuTest {
    private static int[] arrays = new int[]{1, 3, 56, 32, 33, 5, 6, 87, 10, 100};

    public static void main(String[] args) {
        //调用希尔排序
//        List<Integer> sequence = shellSequence();
//        for (Integer step : sequence) {
//            shellByStep(step);
//        }
        MergeSort(arrays);
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + ",");
        }
    }

    /**
     * 归并排序
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(MergeSort(left), MergeSort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    private static void shell() {
        int len = arrays.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arrays[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arrays[preIndex] > temp) {
                    arrays[preIndex + gap] = arrays[preIndex];
                    preIndex -= gap;
                }
                arrays[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    private static void shellByStep(int step) {
        int cur = 0;
        int j = 0;
        // 第一次 step =4
        for (int i = step; i < arrays.length; i++) {
            cur = arrays[i]; // 这里的cur = 5；
            for (j = i - step; j >= 0; j -= step) {
                int a = arrays[j];
                if (cur < a) { //  如果拿出来对比的数据 比已经存在的某个数据小
                    arrays[j + step] = arrays[j];
                } else {  //直接跳出循环
                    break;
                }
            }
            arrays[j + step] = cur;
        }
    }

    private static List<Integer> shellSequence() {
        List<Integer> sequence = new ArrayList<>();
        int step = arrays.length;
        while ((step = step >> 1) > 0) {
            sequence.add(step);
        }
        return sequence;
    }

    private static void charu() {
        int cur;
        for (int i = 0; i < arrays.length - 1; i++) {
            cur = arrays[i + 1];
            int pre = i;
            while (pre >= 0 && cur < arrays[pre]) {
                arrays[pre + 1] = arrays[pre];
                pre--;
            }
            arrays[pre + 1] = cur;
        }
    }

    private static void xuanze() {
        for (int i = arrays.length - 1; i > 0; i--) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arrays[max] <= arrays[j + 1]) {
                    max = j + 1;
                }
            }
            int tmp = arrays[max];
            arrays[max] = arrays[i];
            arrays[i] = tmp;
        }
    }

    private static void maopao() {
        for (int i = arrays.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    int tmp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = tmp;
                }
            }
        }
//        for (int i = 1; i < arrays.length; i++) {
//            for (int j = 0; j < arrays.length - i; j++) {
//                if (arrays[j] > arrays[j + 1]) {
//                    int tmp = arrays[j];
//                    arrays[j] = arrays[j + 1];
//                    arrays[j + 1] = tmp;
//                }
//            }
//        }
    }

    //冒泡优化写法,加入该数据中有部分数据是顺序摆放的,则可以在下次循环时跳过,减少一定的比较次数
    private static void maopao2() {
//        private static int[] arrays = new int[]{1, 3, 56, 32, 33, 5, 6, 87, 10, 100};
        for (int i = arrays.length - 1; i > 0; i--) {
            int changeIndex = 0;

            for (int j = 0; j < i; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    int temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;
                    changeIndex = j + 1;
                }
            }
            i = changeIndex;
        }
    }
}
