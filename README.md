# Hung Hypebeast – Hệ thống Backend E‑commerce (Phase 1)

Tài liệu này hướng dẫn cài đặt, chạy và sử dụng API backend phục vụ website bán hàng thời trang của Hung Hypebeast (chỉ cung cấp API – Headless). Nội dung đã được Việt hóa hoàn toàn để thuận tiện triển khai nhanh trong 2 tuần.

## Công nghệ sử dụng
- Java 21, Spring Boot 3.x
- PostgreSQL 15
- Flyway (migration + seed dữ liệu)
- Spring Security (HTTP Basic cho nhóm Admin)
- Spring Mail (có thể cấu hình SMTP thật; mặc định có thể để NOOP/log)
- OpenAPI/Swagger cho tài liệu API

## Thông tin mặc định (Local)
- API base URL: http://localhost:8080
- PostgreSQL: localhost:5432
  - Database: `assignment_2`
  - Username/Password: cấu hình trong `src\main\resources\application.properties`

Lưu ý bảo mật: Không commit thông tin nhạy cảm (SMTP password, tài khoản DB thật) vào repo public. Khuyến nghị sử dụng biến môi trường hoặc `application-local.properties` ở máy dev.

## Hướng dẫn cài đặt nhanh (Quick start)
1) Cài PostgreSQL và tạo database `assignment_2`.
2) Cập nhật cấu hình DB, SMTP trong `src\main\resources\application.properties` (hoặc tạo profile riêng).
3) Chạy ứng dụng:
```bash
mvn spring-boot:run
```
Sau khi chạy thành công, truy cập Swagger tại: `http://localhost:8080/swagger-ui.html`

## Migration & Seed dữ liệu
- Dùng Flyway để quản lý schema và seed cơ bản.
- Ở môi trường local, ứng dụng sẽ tự chạy migration khi khởi động (tuỳ cấu hình `spring.jpa.hibernate.ddl-auto`).
- Nếu cần làm sạch dữ liệu trong quá trình dev, có thể drop DB và tạo lại.

## Danh sách API công khai (Public APIs)

### Catalog (Danh mục/Sản phẩm)
- `GET /api/public/products?page=1&size=20&category=ao-thun&minPrice=100000&maxPrice=500000&keyword=rong`
  - Mô tả: Lấy danh sách sản phẩm, hỗ trợ phân trang + lọc theo danh mục, khoảng giá, từ khóa.
- `GET /api/public/products/{slug}`
  - Mô tả: Lấy chi tiết sản phẩm theo `slug`.

### Cart (Giỏ hàng)
- `POST /api/public/carts`
  - Mô tả: Tạo mới một giỏ hàng, trả về `cartToken` để thao tác về sau.
- `GET /api/public/carts/{cartToken}`
  - Mô tả: Xem chi tiết giỏ hàng theo `cartToken`.
- `POST /api/public/carts/{cartToken}/items`
  - Body: `{ "variantId": "...", "quantity": 1 }`
  - Mô tả: Thêm biến thể sản phẩm (SKU) vào giỏ.
- `PUT /api/public/carts/{cartToken}/items/{variantId}`
  - Body: `{ "quantity": 2 }`
  - Mô tả: Cập nhật số lượng một item trong giỏ.
- `DELETE /api/public/carts/{cartToken}/items/{variantId}`
  - Mô tả: Xóa một item khỏi giỏ.

### Giữ tồn kho (Reservation 10–15 phút)
- `POST /api/public/checkout/reserve`
  - Body: `{ "cartToken": "...", "holdMinutes": 15 }`
  - Kết quả: Trả về `reservationToken` và `expiresAt`.
- `POST /api/public/checkout/reserve/{reservationToken}/cancel`
  - Mô tả: Hủy giữ hàng (nhả tồn) trước khi hết hạn.

### Tạo đơn hàng (Checkout)
- `POST /api/public/checkout/orders`
  - Body mẫu:
```json
{
  "reservationToken": "uuid",
  "email": "customer@example.com",
  "fullName": "Nguyen Van A",
  "phone": "0900000000",
  "addressLine1": "12 Nguyen Trai",
  "addressLine2": "",
  "city": "HCM",
  "paymentMethod": "COD"
}
```
  - Response mẫu:
```json
{ "orderCode": "HHB-YYYYMMDD-XXXXXX", "trackingToken": "uuid" }
```

### Theo dõi đơn hàng (không cần đăng nhập)
- `GET /api/public/orders/track/{trackingToken}`
  - Mô tả: Xem trạng thái đơn qua link chứa `trackingToken`.

### Webhook SePay (Phase 1: stub)
- `POST /api/public/payments/sepay/webhook`
  - Body mẫu:
```json
{ "orderCode": "HHB-..." }
```
  - Hành vi: Đổi trạng thái đơn sang `PAID` (giả lập ở Phase 1).

## API dành cho Admin (yêu cầu HTTP Basic Auth)
- `GET /api/admin/orders?page=1&size=20`
  - Mô tả: Danh sách đơn hàng có phân trang.
- `PUT /api/admin/orders/{orderId}/status`
  - Body: `{ "status": "SHIPPING" }`
  - Mô tả: Cập nhật trạng thái đơn (ví dụ: `PAID` -> `SHIPPING`, hoặc `CANCELLED`).

## Ghi chú quan trọng về Tồn kho (Inventory Correctness)
- Khi thực hiện giữ hàng (reserve), hệ thống sử dụng khoá bi quan (Pessimistic Lock) ở bảng `product_variants` để tránh tình trạng bán vượt tồn (đặc biệt là trường hợp “last item”).
- Reservation hết hạn sẽ được job scheduler tự động giải phóng (mặc định chạy mỗi 30 giây – có thể cấu hình).

## Bộ sưu tập API (Postman/Swagger)
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`
- Có thể xuất file Postman từ Swagger hoặc dùng trực tiếp Swagger UI để thử API.

## Cấu trúc dự án (rút gọn)
- `src/main/java/.../controller`: Lớp controller định nghĩa REST endpoints
- `src/main/java/.../service`: Lớp service nghiệp vụ
- `src/main/java/.../repository`: JPA repository giao tiếp DB
- `src/main/java/.../entities`: Entity (catalog, cart, order, inventory, ...)
- `src/main/resources/db`: Migration/seed (Flyway)

## Phạm vi Phase 1 (theo yêu cầu khách hàng)
- Catalog hiển thị nhanh, phân trang, lọc cơ bản (giá, loại, từ khóa).
- Giỏ hàng: thêm/xóa/sửa số lượng; kiểm tra tồn sơ bộ khi thêm.
- Reservation tồn kho 10–15 phút và giải phóng tự động khi hết hạn.
- Checkout: tạo đơn, hỗ trợ phương thức COD; SePay webhook giả lập.
- Tracking đơn qua link, không cần tài khoản/đăng nhập.
- Admin: xem danh sách đơn, cập nhật trạng thái đơn.

## Cách đóng góp/Phát triển tiếp
- Tạo branch mới từ `main` cho mỗi tính năng/bugfix.
- Viết mô tả ngắn gọn trong PR, đính kèm hình ảnh/Swagger link nếu cần.
- Thảo luận scope/ưu tiên trước khi merge các thay đổi lớn.
