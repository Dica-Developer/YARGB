package com.yarpg.result;

public class Result<T> {

	public static <T> Result<T> of(T value) {
		return new Success<>(value);

	}

	static final class Success<T> extends Result<T> {
		private final T _value;

		private Success(T value) {
			_value = value;
		}
	}
}
