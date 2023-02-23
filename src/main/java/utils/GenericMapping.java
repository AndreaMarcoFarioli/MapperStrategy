package utils;

public interface GenericMapping<S,T> {
	T sourceToT(S source);
	S sourceToS(T source);
}
