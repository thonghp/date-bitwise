# Bài toán lưu ngày tháng năm vào trong giá trị int

## Cách thực hiện

- B1: Phân tích bài toán
    - 1 **`int`** sẽ có độ dài là 32 bit
    - 1 ngày tối đa sẽ là 31 ngày (5 bit)
    - 1 tháng tối đa sẽ là 12 tháng (4 bit)
    - 1 năm tối đa sẽ là 9999 (14 bit)
    - Tối đa để lưu vào 1 **`int`** là 23 bit
    - Ta sẽ lưu ngày vào trước sau đó tới tháng và cuối cùng là năm
- B2: Tạo 1 biến kiểu int để lưu ngày tháng năm
    - Nếu sử dụng **`&`** sẽ gặp trường hợp là khi **`1 & 0`** hay **`0 & 1 = 0`** => sẽ bị mất số 1 (ko đúng con số
      thực tế)
    - Nếu sử dụng **`^`** sẽ gặp trường hợp là **`1 ^ 1 = 0`** => sẽ bị mất số 1 (ko đúng con số thực tế)
    - Do đó chọn **`|`** là hợp lý nhất trong trường hợp này
- B3: Tiến hành lưu vào
    - Khi lưu ngày vào sẽ chiếm 5 bit đầu tiên
    - Khi lưu tháng nối tiếp sau ngày **`==>`** phải dịch vô 5 bit mới lưu được tháng do 5 bit đầu là ngày
    - Khi lưu năm nối tiếp sau tháng **`==>`** phải dịch vô 9 bit (ngày + tháng)
- Tại sao lại chọn DAY_MASK = 31, MONTH_MASK = 15
    - Vì 31 = 11111 (5 bit) và 15 = 1111 (4 bit)
    - Khi dùng **`&`** sẽ lấy ra đúng số ban đầu