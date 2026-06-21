# Bước 1: Build ứng dụng bằng chính Java 24 kết hợp Maven Wrapper có sẵn của dự án
FROM amazoncorretto:24-alpine-jdk AS build
WORKDIR /app

# Sao chép các file cấu hình Maven Wrapper trước để tối ưu hóa cache lớp (layer cache)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Cấp quyền thực thi cho file script mvnw và tải trước các thư viện phụ thuộc
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Sao chép mã nguồn và build dự án
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Bước 2: Chạy ứng dụng với môi trường Java 24 tinh gọn
FROM amazoncorretto:24-alpine-jdk
WORKDIR /app

# Sao chép file jar đã build thành công từ bước 1
COPY --from=build /app/target/ToDoListApp-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]