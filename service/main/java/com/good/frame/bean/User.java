package com.good.frame.bean;

import java.util.Arrays;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 1, 67, 45, 87, 15, 66, 37, 67, 9};
        sort(a);
    }

    private static void sort(int[] numbers){
        int size = numbers.length;
        int temp;
        for (int i = 0; i < size; i++) {
            temp = numbers[i];
            int j;
            for (j = i; j > 0; j--) {
                if (numbers[j - 1] > temp) {
                    numbers[j] = numbers[j - 1];
                }else {
                    break;
                }
            }
            numbers[j] = temp;
        }
        System.out.println(Arrays.toString(numbers));
    }
}
