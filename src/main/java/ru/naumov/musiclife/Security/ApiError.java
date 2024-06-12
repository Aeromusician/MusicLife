package ru.naumov.musiclife.Security;

import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class ApiError implements Serializable {

    private Map<String, String> items;

    public ApiError(String errorKey, String extraInfo) {
        this();
        items.put(errorKey, extraInfo);
    }

    public ApiError(Map<String, String> items) {
        this.items = items;
    }

    public ApiError() {
        this(new HashMap<>());
    }

    public String findFirstError() {
        return items.keySet().stream()
                .findFirst()
                .orElse(null);
    }

    public void addItem(String errorKey, String extraInfo) {
        items.put(errorKey, extraInfo);
    }

    public ApiError append(ApiError otherError) {
        if (otherError != null) {
            items.putAll(otherError.getItems());
        }
        return this;
    }

    public boolean isEmpty() {
        return MapUtils.isEmpty(items);
    }

    public boolean contains(String error) {
        return items.containsKey(error);
    }

    public boolean containsValue(String error) {
        return items.containsValue(error);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return StringUtils.EMPTY;
        }
        return "errors: " + Arrays.toString(items.entrySet().toArray());
    }

}
