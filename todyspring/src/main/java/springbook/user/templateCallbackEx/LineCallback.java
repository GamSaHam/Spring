package springbook.user.templateCallbackEx;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
