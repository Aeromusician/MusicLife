package ru.naumov.musiclife.Security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class Response<R> implements Serializable {

    public static final Response<String> OK = new Response<>("ok");

    @JsonProperty("content")
    private final R data;

    private final ApiError error;

    public Response() {
        this(null, null);
    }

    public Response(R data) {
        this(data, null);
    }

    public Response(ApiError error) {
        this(null, error);
    }

    public Response(R data, ApiError error) {
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> data(T data) {
        return (data != null ? new Response<>(data) : Response.empty());
    }

    public static <T> Response<T> error(ApiError error) {
        return new Response<>(error);
    }

    public static <T> Response<T> empty() {
        return new Response<>();
    }

    public boolean isError() {
        return error != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(data, response.data) && Objects.equals(error, response.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, error);
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                ", error=" + error +
                '}';
    }

}
