package com.hpt.demo;

public class DateBitwise {
    private final static int DAY_MASK = 31, MONTH_MASK = 15;
    private int date;

    /*
     * demo ngày 16 tháng 5 năm 2022
     * 16 => 10000
     * 5 => 101
     * 2022 => 111 1110 0110
     * 0000 0000 0000 0000 0000 0000 0000 0000 (int mặc định)
     * -------------------------------------------------------------------------------------------------------------
     * 0000 0000 0000 0000 0000 0000 0001 0000 (date |= day) ==> int date = 16
     * 0000 0000 0000 0000 0000 0000 1011 0000 (month << 5 sau đó date | month) ==> date = 176
     * 0000 0000 0000 1111 1100 1100 1011 0000 (year << 9 sau đó date | year) ==> date = 1035440
     * date lúc này sẽ lấy giá trị sau cùng là 1035440
     */
    public DateBitwise(int day, int month, int year) {
        date |= day;
        date |= month << 5;
        date |= year << 9;
    }

    /*
     * 0000 0000 0000 1111 1100 1100 1011 0000 (date)
     * 1111 1111 1111 1111 1111 1111 1110 0000 (~DAY_MASK)
     * 0000 0000 0000 1111 1100 1100 1010 0000 (date & ~DAY_MASK)
     * 0000 0000 0000 0000 0000 0000 0001 0000 (day)
     * ---------------------------------------------------
     * 0000 0000 0000 1111 1100 1100 1011 0000
     */
    public void setDay(int day) {
        date &= ~DAY_MASK;
        date |= day;
    }

    /*
     * 0000 0000 0000 1111 1100 1100 1011 0000 (date)
     * 0000 0000 0000 0000 0000 0001 1110 0000 (MONTH_MASK << 5)
     * 1111 1111 1111 1111 1111 1110 0001 1111 (~(MONTH_MASK << 5))
     * 0000 0000 0000 1111 1100 1100 0001 0000 (date&(~(MONTH_MASK << 5)))
     * 0000 0000 0000 0000 0000 0000 0100 0000 (month << 5)
     * ---------------------------------------------------
     * 0000 0000 0000 1111 1100 1100 0101 0000
     */
    public void setMonth(int month) {
        date &= ~(MONTH_MASK << 5);
        date |= month << 5;
    }

    public void setYear(int year) {
        date = (date << 23) >> 23;
        date |= year << 9;
    }

    /*
     * 0000 0000 0000 1111 1100 1100 1011 0000 (date)
     * 0000 0000 0000 0000 0000 0000 0001 1111 (DAY_MASK)
     * ---------------------------------------------------
     * 0000 0000 0000 0000 0000 0000 0001 0000
     * Lưu vào là | thì lúc lấy ra phải là &
     */
    public int getDay() {
        return date & DAY_MASK;
    }

    /*
     * 0000 0000 0000 1111 1100 1100 1011 0000 (date)
     * 0000 0000 0000 0000 0111 1110 0110 0101 (date >> 5)
     * 0000 0000 0000 0000 0000 0000 0000 1111 (MONTH_MASK)
     * ---------------------------------------------------
     * 0000 0000 0000 0000 0000 0000 0000 0101
     */
    public int getMonth() {
        return (date >> 5) & MONTH_MASK;
    }

    /*
     * 0000 0000 0000 1111 1100 1100 1011 0000 (date)
     * 0000 0000 0000 0000 0000 0111 1110 0110 (date >> 9)
     * ---------------------------------------------------
     */
    public int getYear() {
        return date >> 9;
    }

    public int getDate() {
        return date;
    }

    public String toString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    public static void main(String[] args) {
        DateBitwise date = new DateBitwise(16, 5, 2022);
//        date.setDay(3);
//        date.setMonth(2);
//        date.setYear(2024);
        System.out.println(date);
    }
}
