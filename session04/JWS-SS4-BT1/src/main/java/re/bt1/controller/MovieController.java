package re.bt1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
//Phần 1 – Phân tích logic
//
//Client nhận về chuỗi dạng [Movie@3a1b2c3d, Movie@4f2e1a0b] thay vì JSON vì trong phương thức getMovies(),
// chương trình đang trả về movies.toString().
//
//Biến movies là một List<Movie>. Khi gọi toString() trên một danh sách, Java sẽ tiếp tục gọi toString() của từng
// object Movie bên trong danh sách đó. Tuy nhiên, class Movie trong đoạn code không override phương thức toString(),
// nên Java sẽ sử dụng phương thức mặc định của class Object.
//
//Phương thức mặc định này chỉ trả về tên class kèm mã hash của object theo dạng:
//
//TênClass@MãHash
//
//Ví dụ:
//
//Movie@3a1b2c3d
//
//Vì vậy toàn bộ danh sách sẽ hiển thị thành:
//
//[Movie@3a1b2c3d, Movie@4f2e1a0b]
//
//Đây chỉ là chuỗi text thông thường chứ không phải dữ liệu JSON hợp lệ.
//
//Nguyên nhân gốc rễ nằm ở việc phương thức getMovies() trả về kiểu String và ép danh sách phim thành chuỗi bằng movies.toString().
// Khi đó Spring Boot hiểu rằng server chỉ đang trả về văn bản thuần (plain text) nên không thực hiện cơ chế chuyển đổi object sang JSON.
//
//Trong Spring Boot, nếu trả về trực tiếp List<Movie> hoặc object Java, framework sẽ tự động sử dụng thư viện Jackson để serialize
// dữ liệu thành JSON hợp lệ cho client. Nhưng do đoạn code đã chuyển dữ liệu thành String trước khi trả về nên frontend không thể parse
// và hiển thị dữ liệu phim được.
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    static class Movie {

        private String movieId;
        private String title;
        private String genre;
        private double rating;

        public Movie(String movieId, String title, String genre, double rating) {
            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.rating = rating;
        }

        public String getMovieId() {
            return movieId;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
        }

        public double getRating() {
            return rating;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }

    @GetMapping
    public List<Movie> getMovies() {

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("M001", "Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("M002", "Parasite", "Drama", 8.6));

        return movies;
    }
}
