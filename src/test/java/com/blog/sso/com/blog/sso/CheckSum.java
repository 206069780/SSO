package com.blog.sso.com.blog.sso;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class

CheckSum {

    @Test
    public void main() {
        String ans = "27 00 00 00 5c aa 97 00";
        System.out.println(getCheckSum(ans));
    }

    /**
     * @param ans 十六进制数据
     * @return checkSum
     */
    public String getCheckSum(String ans) {
        List<Integer> decimals = getDecimalList(ans);
        int res = 0;
        for (Integer binary : decimals) {
            res += binary;
        }
        int n = ~res;
//        addBinary(Integer.toBinaryString(n).substring(23, 32), "1");
//        return  addBinary(Integer.toBinaryString(n).substring(24, 32), "1");
        return Integer.toBinaryString(n).substring(24, 32);
    }

    /**
     * 二进制相加
     *
     * @param a
     * @param b
     * @return
     */
    private String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int index1 = a.length() - 1, index2 = b.length() - 1;
        int carry = 0;
        while (index1 >= 0 || index2 >= 0 || carry != 0) {
            int digit1 = index1 >= 0 ? a.charAt(index1) - '0' : 0;
            int digit2 = index2 >= 0 ? b.charAt(index2) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            sb.append(sum % 2);
            carry = sum / 2;
            index1--;
            index2--;
        }
        return sb.reverse().toString();
    }

    /**
     * 十六进制字符串 转 十进制
     *
     * @param ans 十六进制数据
     * @return 十进制结果
     */
    private List<Integer> getDecimalList(String ans) {
        List<Integer> res = new LinkedList<>();
        Arrays.stream(ans.split(" ")).forEach(elem -> {
            int decimal = Integer.parseInt(elem, 16);
            res.add(decimal);
        });
        return res;
    }

    int crc16Modbus(int data, int length) {
        int i;
        int crc = 0xffff;
        while (length-- > 0) {
            crc ^= data;
            data++;
            for (i = 0; i < 8; ++i) {
                if ((crc & 1) != 0) crc = (crc >> 1) ^ 0xA001;        // 0xA001 = reverse 0x8005
                else crc = (crc >> 1);
            }
        }
        return crc;
    }


    @Test
    public void maind() {
        StringBuffer s = new StringBuffer("Hello");
        if ((s.length() > 5) && (s.append("there").equals("False"))) ;
        System.out.println("value is " + s);
    }

    @Test
    public void method() {
        int a = 101;
        if ((a >> 1) << 1 == a) {
            System.out.println("2");
        } else {
            System.out.println("1");
        }
    }

    @Test
    public void method1() {
        int a = 3, b = 1, c = 2;

        List<Integer> tmp = new ArrayList<>() {{
            add(a);
            add(b);
            add(c);
        }};
        System.out.println(tmp.stream().sorted().collect(Collectors.toList()));
    }

    int find(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i : a) {
            if (map.containsKey(i)) {
                res = i;
            } else {
                map.put(i, 1);
            }
        }
        return res;
    }


    @Test
    public void method2() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int target = 10;
        System.out.println(getIndex(nums, target));
    }

    int getIndex(int[] nums, int target) {
        if (target > nums[nums.length - 1] || target < nums[0]) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return -1;
    }

}